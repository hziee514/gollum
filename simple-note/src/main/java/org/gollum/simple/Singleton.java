package org.gollum.simple;

import org.springframework.context.annotation.Scope;

import java.lang.annotation.*;

/**
 * @author wurenhai
 * @date 2017/12/19
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Scope("singleton")
public @interface Singleton {
}
