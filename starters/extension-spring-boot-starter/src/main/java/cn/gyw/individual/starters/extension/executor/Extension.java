package cn.gyw.individual.starters.extension.executor;

import java.lang.annotation.*;

/**
 * 扩展注解
 *
 * @date 2023/7/21
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface Extension {

    String bizId() default "default";
}
