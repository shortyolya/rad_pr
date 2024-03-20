package com.baltinfo.radius.feed.cian.converter;

import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.*;

/**
 * @author Igor Lapenok
 * @since 12.01.2022
 */
public class CianFreeAppointmentObjectConverterTest {
    @Test
    public void testFloorNumber() {
        assertEquals(BigInteger.valueOf(Long.parseLong("-1")), BigInteger.valueOf(-1L));
    }
}