package com.millionaire.compound.common.models.utils;

import java.util.List;

public class CommonUtil {

    private static final double delta = 0.0001;

    public static <T> boolean isNotEmpty(List<T> list){
        return list != null && list.size()>0;
    }

    public static boolean isDoubleZero(double value) {return value-0.0<delta;}
}
