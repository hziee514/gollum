package org.gollum.core.common;

/**
 * @author wurenhai
 * @date 2017/12/28
 */
public final class Assertion {

    /**
     *
     * @param argument
     * @param name
     * @param <T>
     */
    public static <T> void notNull(T argument, String name) {
        if (argument == null) {
            throw new IllegalArgumentException(name + " is null");
        }
    }

    /**
     *
     * @param argument
     * @param name
     */
    public static void notNullOrEmpty(String argument, String name) {
        if (argument == null || argument.isEmpty()) {
            throw new IllegalArgumentException(name + " is null or empty");
        }
    }

}
