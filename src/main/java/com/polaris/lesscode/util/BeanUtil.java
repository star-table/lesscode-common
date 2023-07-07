package com.polaris.lesscode.util;

import com.esotericsoftware.reflectasm.ConstructorAccess;
import net.sf.cglib.beans.BeanCopier;
import net.sf.cglib.core.Converter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * bean复制
 * <p>
 * 注意是浅克隆
 *
 * @author roamer
 * @version v1.0
 * @date 2020-09-25 17:05
 */
public class BeanUtil {

    private BeanUtil() {
    }

    private static final Map<String, BeanCopier> BEAN_COPIER_CACHE = new ConcurrentHashMap<>();

    private static final Map<String, ConstructorAccess> CONSTRUCTOR_ACCESS_CACHE = new ConcurrentHashMap<>();

    /**
     * 由原对象，创建出新的对象
     * <p>
     * 注意是浅克隆
     *
     * @param source      源对象
     * @param targetClass 目标实例类型
     * @param <T>         实例类型
     *
     * @return 实例
     */
    public static <T> T toBean(Object source, Class<T> targetClass) {
        return toBean(source, targetClass, null);
    }

    /**
     * 由原对象，创建出新的对象
     * <p>
     * 注意是浅克隆
     *
     * @param source      源对象
     * @param targetClass 目标实例类型
     * @param <T>         实例类型
     *
     * @return 实例
     */
    public static <T> T toBean(Object source, Class<T> targetClass, Converter converter) {
        T t;
        try {
            t = targetClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(
                    String.format("Create new instance of %s failed: %s", targetClass, e.getMessage()));
        }
        copyProperties(source, t, converter);
        return t;
    }


    /**
     * 属性copy
     * <p>
     * 注意是浅克隆
     *
     * @param source 源对象
     * @param target 目标对象
     */
    public static void copyProperties(Object source, Object target, Converter converter) {
        BeanCopier copier = getBeanCopier(source.getClass(), target.getClass());
        copier.copy(source, target, converter);
    }


    /**
     * 由原对象集合，创建出新的对象集合
     * <p>
     * 注意是浅克隆
     *
     * @param sourceList  源对象集合
     * @param targetClass 目标实例类型
     * @param <T>         实例类型
     *
     * @return 实例集合
     */
    public static <T> List<T> copyPropertiesOfList(List<?> sourceList, Class<T> targetClass) {
        return copyPropertiesOfList(sourceList, targetClass, null);
    }


    /**
     * 由原对象，创建出新的对象
     * <p>
     * 注意是浅克隆
     *
     * @param sourceList  源对象集合
     * @param targetClass 目标实例类型
     * @param <T>         实例类型
     *
     * @return 实例集合
     */
    public static <T> List<T> copyPropertiesOfList(List<?> sourceList, Class<T> targetClass, Converter converter) {
        if (sourceList == null || sourceList.isEmpty()) {
            return Collections.emptyList();
        }
        ConstructorAccess<T> constructorAccess = getConstructorAccess(targetClass);
        List<T> resultList = new ArrayList<>(sourceList.size());
        for (Object o : sourceList) {
            T t;
            try {
                t = constructorAccess.newInstance();
                copyProperties(o, t, converter);
                resultList.add(t);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return resultList;
    }


    private static BeanCopier getBeanCopier(Class<?> sourceClass, Class<?> targetClass) {
        String beanKey = generateKey(sourceClass, targetClass);
        BeanCopier copier;
        if (!BEAN_COPIER_CACHE.containsKey(beanKey)) {
            copier = BeanCopier.create(sourceClass, targetClass, false);
            BEAN_COPIER_CACHE.put(beanKey, copier);
        } else {
            copier = BEAN_COPIER_CACHE.get(beanKey);
        }
        return copier;
    }

    /**
     * 两个类的全限定名拼接起来构成Key
     *
     * @param sourceClass
     * @param targetClass
     *
     * @return
     */
    private static String generateKey(Class<?> sourceClass, Class<?> targetClass) {
        return sourceClass.getName() + targetClass.getName();
    }


    private static <T> ConstructorAccess<T> getConstructorAccess(Class<T> targetClass) {
        ConstructorAccess<T> constructorAccess = CONSTRUCTOR_ACCESS_CACHE.get(targetClass.getName());
        if (constructorAccess != null) {
            return constructorAccess;
        }
        try {
            constructorAccess = ConstructorAccess.get(targetClass);
            constructorAccess.newInstance();
            CONSTRUCTOR_ACCESS_CACHE.put(targetClass.toString(), constructorAccess);
        } catch (Exception e) {
            throw new RuntimeException(
                    String.format("Create new instance of %s failed: %s", targetClass, e.getMessage()));
        }
        return constructorAccess;
    }


}
