package org.gollum.core.scheduling;

import org.gollum.core.common.GollumException;

/**
 * @author wurenhai
 * @date 2018/1/3
 */
public class TaskExistsException extends GollumException {

    public TaskExistsException(String message) {
        super(message);
    }

}
