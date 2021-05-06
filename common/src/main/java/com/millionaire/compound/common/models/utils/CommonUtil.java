package com.millionaire.compound.common.models.utils;

import java.util.List;

public class CommonUtil {

    public static <T> boolean isNotEmpty(List<T> list){
        return list != null && list.size()>0;
    }
}
