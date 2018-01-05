package org.gollum.core.domain;

import org.gollum.core.common.GollumException;

/**
 * @author wurenhai
 * @date 2018/1/5
 */
public class ConcurrencyException extends GollumException {
    public ConcurrencyException(String message) {
        super(message);
    }
}
