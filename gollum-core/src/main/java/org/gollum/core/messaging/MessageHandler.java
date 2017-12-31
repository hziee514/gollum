package org.gollum.core.messaging;

/**
 * @author wurenhai
 * @date 2017/12/31
 */
@FunctionalInterface
public interface MessageHandler<T extends Message<?>> {

    /**
     * handle the given message
     *
     * @param message
     * @return
     * @throws Exception
     */
    Object handle(T message) throws Exception;

}
