package com.millionaire.compound.common.models.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ObjectUtil {

    public static void copyObject(Object src, Object dest)
            throws IllegalArgumentException, IllegalAccessException,
            NoSuchFieldException, SecurityException, InvocationTargetException, NoSuchMethodException {

        Method[] methods = src.getClass().getDeclaredMethods();

        for (int i = 0; i < methods.length; i++) {

            if(methods[i].getName().startsWith("get")){
                System.out.println(methods[i].getName());
                Object o = methods[i].invoke(src);
                if(o != null) {
                    System.out.println(o.toString());
                    Method targetMethod = dest.getClass().getDeclaredMethod(methods[i].getName().replace("get", "set"), Object.class);
                    targetMethod.invoke(dest, o);
                }
            }
        }
//        for (Field field : src.getClass().getFields()) {
//            dest.getClass().getField(field.getName()).set(dest, field.get(src));
//        }
    }
}
