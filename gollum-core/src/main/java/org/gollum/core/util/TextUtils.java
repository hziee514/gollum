package org.gollum.core.util;

/**
 * @author wurenhai
 * @date 2017/12/26
 */
public class TextUtils {

    public static boolean isNullOrEmpty(String str) {
        return (str == null || str.isEmpty());
    }

    public static boolean isNotEmpty(String str) {
        return !isNullOrEmpty(str);
    }

}
