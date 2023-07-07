package com.polaris.lesscode.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 更方便的map init
 * 
 * @author nico
 * @date 2020年9月9日
 */
public class Maps<K, V> {

	private Map<K, V> datas;
	
	public Maps() {
		this.datas = new ConcurrentHashMap<K, V>();
	}
	
	public Maps<K, V> put(K k, V v){
		datas.put(k, v);
		return this;
	}
	
	public Map<K, V> toMap(){
		return this.datas;
	}
}
