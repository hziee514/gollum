package org.gollum.core.messaging;

/**
 * @author wurenhai
 * @date 2017/12/31
 */
@Deprecated
public interface InterceptorChain {

    /**
     *
     * @return
     * @throws Exception
     */
    Object proceed() throws Exception;

}
