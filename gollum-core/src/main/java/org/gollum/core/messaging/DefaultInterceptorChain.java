package org.gollum.core.messaging;

import java.util.Iterator;

/**
 * @author wurenhai
 * @date 2017/12/31
 */
@Deprecated
public class DefaultInterceptorChain<T extends Message<?>> implements InterceptorChain {

    private final MessageHandler<? super T> handler;
    private final Iterator<? extends MessageHandlerInterceptor<? super T>> chain;

    public DefaultInterceptorChain(Iterable<? extends MessageHandlerInterceptor<? super T>> interceptors,
                                   MessageHandler<? super T> handler) {
        this.handler = handler;
        this.chain = interceptors.iterator();
    }

    @Override
    public Object proceed() throws Exception {
        if (chain.hasNext()) {
            return chain.next().handle(this);
        } else {
            //TODO: handle message
            return handler.handle(null);
        }
    }
}
