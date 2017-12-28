package org.gollum.note;

import org.springframework.context.annotation.Scope;

import java.lang.annotation.*;

/**
 * @author wurenhai
 * @date 2017/12/28
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Scope("singleton")
public @interface Singleton {
}
