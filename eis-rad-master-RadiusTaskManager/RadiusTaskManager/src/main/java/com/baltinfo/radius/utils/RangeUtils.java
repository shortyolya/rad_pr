package com.baltinfo.radius.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * Преобразование списка номеров к строке и обратно.
 * </p>
 *
 * @author Lapenok Igor
 * @since 21.11.2018
 */
public class RangeUtils {

    /**
     * Приводит строку, указывающую диапазоны номеров к списку значений. Строка должна содержать цифры, запяттые или дефиз
     * Пример формата 1,2,4,7-15,25. Пример возврата {1L, 2L, 4L, 7L, 8L, 9L, 10L, 11L, 12L, 13L, 14L, 15L, 25L}
     *
     * @param rangeString строка, указывающую диапазоны номеров
     * @return список номеров
     */
    public static List<Long> parseRange(String rangeString) {
        List<Long> rangeList = new ArrayList<>();
        if (rangeString == null || rangeString.isEmpty()) {
            return rangeList;
        }
        if (!rangeString.matches("^[0-9,-]+$")) {
            throw new IllegalArgumentException("Can't parse string=" + rangeString);
        }
        if (!rangeString.contains(",")) {
            if (!rangeString.contains("-")) {
                rangeList.add(Long.valueOf(rangeString));
            } else {
                rangeList.addAll(parseInterval(rangeString));
            }
        } else {
            for (String range : rangeString.split(",")) {
                if (!range.contains("-")) {
                    rangeList.add(Long.valueOf(range));
                } else {
                    rangeList.addAll(parseInterval(range));
                }
            }
        }
        return rangeList;
    }

    /**
     * Приводит список номеров, отсортированный в порядке возростания, к строке формата, содержащего цифры, запяттые или дефиз
     * Пример списка номеров {1L, 2L, 4L, 7L, 8L, 9L, 10L, 11L, 12L, 13L, 14L, 15L, 25L} Пример формата строки 1,2,4,7-15,25.
     *
     * @param list список номеров
     * @return строка, указывающую диапазоны номеров
     */
    public static String parseList(List<Long> list) {
        Long currentBlockItem = null;
        List<Long> blockList = new ArrayList<>();
        List<String> blocks = new ArrayList<>();
        for (Long item : list) {
            if (currentBlockItem == null || item.equals(currentBlockItem + 1L)) {
                currentBlockItem = item;
                blockList.add(item);
            } else {
                blocks.add(parseBlock(blockList));
                blockList.clear();
                blockList.add(item);
                currentBlockItem = item;
            }
        }
        if (!blockList.isEmpty()) {
            blocks.add(parseBlock(blockList));
        }
        return String.join(",", blocks);
    }

    private static String parseBlock(List<Long> list) {
        if (list == null || list.isEmpty()) {
            return "";
        }
        if (list.size() == 1) {
            return list.get(0).toString();
        }
        return list.size() > 2
                ? list.get(0).toString() + "-" + list.get(list.size() - 1).toString()
                : list.stream()
                .map(Object::toString)
                .collect(Collectors.joining(","));
    }

    private static List<Long> parseInterval(String interval) {
        String[] points = interval.split("-");
        if (points.length != 2) {
            throw new IllegalArgumentException("Can't parse interval=" + interval);
        }
        Long pointB = Long.valueOf(points[0]);
        Long pointE = Long.valueOf(points[1]);
        List<Long> rangeList = new ArrayList<>();
        for (Long i = pointB; i <= pointE; i++) {
            rangeList.add(i);
        }
        return rangeList;
    }

    /**
     * Проверяет наличие значения {@param checkedValue} в строке значений {@param checkedString},
     * разделенных сепаратором {@param separator}
     *
     * @param separator     символ разделенителя
     * @param checkedString строка значений параметра
     * @param checkedValue  значение параметра
     * @return {@code true} - значение содержится в строке, иначе {@code false}
     */
    public static boolean consists(String separator, String checkedString, String checkedValue) {
        String[] listValues = checkedString.split(separator);
        for (String value : listValues) {
            if (checkedValue.equals(value)) {
                return true;
            }
        }
        return false;
    }
}
