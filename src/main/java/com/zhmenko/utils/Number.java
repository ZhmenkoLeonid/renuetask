package com.zhmenko.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Number {
    /**
     * Проверка, может ли входная строка str быть преобразовано в число типа Double
     * @param str - проверяемая строка
     * @return true  - если строка может быть преобразована в Double,
     *         false - иначе.
     */
    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
