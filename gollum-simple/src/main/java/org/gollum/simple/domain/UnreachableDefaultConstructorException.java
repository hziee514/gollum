package org.gollum.simple.domain;

import org.gollum.common.exception.GollumException;

/**
 * 无法访问默认的构造函数
 *
 * @author wurenhai
 * @date 2018/1/12
 */
public class UnreachableDefaultConstructorException extends GollumException {
    public UnreachableDefaultConstructorException(String message) {
        super(message);
    }
}
