package com.millionaire.compound.common.models.utils;

import junit.framework.AssertionFailedError;
import org.junit.Assert;
import org.junit.Test;

public class MathUtilTest {

    @Test
    public void testGetEarning(){
        double buy_price = 10.0;
        double sell_price = 11.0;
        Assert.assertEquals(0.1, MathUtil.getEarning(buy_price,sell_price), 0.0001);

        buy_price = 11.0;
        sell_price = 10.0;
        Assert.assertEquals(-0.09, MathUtil.getEarning(buy_price,sell_price), 0.0001);

    }
}
