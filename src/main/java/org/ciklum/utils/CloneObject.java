package org.ciklum.utils;

import java.lang.reflect.Field;
import static java.util.Arrays.stream;

/**
 * Created by ccc on 18.05.2016.
 */
public class CloneObject {
    public static void clone(Object source, Object destination) {
        Class dest = destination.getClass();
        stream(source.getClass().getDeclaredFields()).forEach(f -> {
            try {
                f.setAccessible(true);
                Field destField = dest.getDeclaredField(f.getName());
                destField.setAccessible(true);
                destField.set(destination, f.get(source));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
