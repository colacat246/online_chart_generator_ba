package org.cv.ocb.utils;

public class StringUtils {
    public static boolean isEmpty(String str) {
        return (str == null || str.equals(""));
    }

    public static boolean hasEmpty(String... strs) {
        for (String str : strs) {
            if (str == null || str.equals("")) return true;
        }
        return false;
    }
}
