package com.baltinfo.radius.feed.avito.converter;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Igor Lapenok
 * @since 12.01.2022
 */
public class VAvitoRealEstateConverterTest {
    private static final String MOBILEPHONE_REGEX = "(?:\\+|\\d)[\\d\\-\\(\\) ]{9,}\\d";

    @Test
    public void shouldTrue_whenValidatePhone() {
        assertTrue("+7(977)549-09-96".matches(MOBILEPHONE_REGEX));
        assertTrue("+79775490996".matches(MOBILEPHONE_REGEX));
    }

}