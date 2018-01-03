package org.gollum.core.common;

/**
 * @author wurenhai
 * @date 2018/1/3
 */
public class GollumException extends RuntimeException {

    public GollumException(String message) {
        super(message);
    }

    public GollumException(String message, Throwable cause) {
        super(message, cause);
    }

}
