package org.ciklum.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.util.Map;

/**
 * Created by ccc on 18.05.2016.
 */
public class ObjectToString {
    public static String convert(Object obj) {
        StringBuilder builder = new StringBuilder();
        try {
            Map<String, String> describe = BeanUtils.describe(obj);
            describe.forEach((k, v) -> builder.append(k).append(": ").append(v).append("\n"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return builder.toString();
    }
}
