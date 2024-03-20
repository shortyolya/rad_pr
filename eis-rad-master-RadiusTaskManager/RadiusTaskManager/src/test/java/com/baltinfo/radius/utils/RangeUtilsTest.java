package com.baltinfo.radius.utils;

import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * </p>
 *
 * @author Lapenok Igor
 * @since 21.11.2018
 */
public class RangeUtilsTest {

    @Test
    @Ignore
    public void should_true_then_parseSrting() {
        String rangeString = "1,2,4,7-15,25";
        Long[] ranges = {1L, 2L, 4L, 7L, 8L, 9L, 10L, 11L, 12L, 13L, 14L, 15L, 25L};
        List<Long> rangeList = RangeUtils.parseRange(rangeString);
        assert Arrays.asList(ranges).equals(rangeList);

    }

    @Test
    @Ignore
    public void should_true_then_parseList() {
        String rangeString = "1,2,4,7-15,25";
        Long[] ranges = {1L, 2L, 4L, 7L, 8L, 9L, 10L, 11L, 12L, 13L, 14L, 15L, 25L};
        String parsedListString = RangeUtils.parseList(Arrays.asList(ranges));
        assert rangeString.equals(parsedListString);
    }

}