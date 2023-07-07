package com.polaris.lesscode.util;

import com.polaris.lesscode.util.BeanUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author roamer
 * @version v1.0
 * @date 2020-09-25 17:13
 */
public class BeanUtilTest {

    public static class User {
        Address address;

        public Address getAddress() {
            return address;
        }

        public void setAddress(Address address) {
            this.address = address;
        }
    }

    public static class Address {
        String region;
        List<String> as;

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public List<String> getAs() {
            return as;
        }

        public void setAs(List<String> as) {
            this.as = as;
        }
    }


    @Test
    public void testBeanCopy() {
        User u = new User();
        u.address = new Address();
        u.address.as = new ArrayList<>();
        u.address.region = "上海";
        u.address.as.add("111");
        User u1 = BeanUtil.toBean(u, User.class);
        Assert.assertEquals(u.address, u1.address);

        u1.address.region = "河南";
        Assert.assertEquals(u.address.region, u1.address.region);

        u1.address.as.add("222");
        Assert.assertEquals(u.address.as, u.address.as);

    }
}
