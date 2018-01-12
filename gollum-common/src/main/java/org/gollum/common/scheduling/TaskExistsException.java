package org.gollum.common.scheduling;

import org.gollum.common.exception.GollumException;

/**
 * @author wurenhai
 * @date 2018/1/3
 */
public class TaskExistsException extends GollumException {

    public TaskExistsException(String message) {
        super(message);
    }

}
