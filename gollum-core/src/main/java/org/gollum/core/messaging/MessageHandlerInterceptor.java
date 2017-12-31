package org.gollum.core.messaging;

/**
 * @author wurenhai
 * @date 2017/12/31
 */
public interface MessageHandlerInterceptor<T extends Message<?>> {

    /**
     * Invoked before a Message is handled
     *
     * @param interceptorChain
     * @return
     * @throws Exception
     */
    Object handle(InterceptorChain interceptorChain) throws Exception;

}
