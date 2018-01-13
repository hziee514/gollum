package org.gollum.common.exception;

/**
 * 无法访问默认的构造函数
 *
 * @author wurenhai
 * @date 2018/1/12
 */
public class NoDefaultConstructorException extends GollumException {
    public NoDefaultConstructorException(String message) {
        super(message);
    }
}
