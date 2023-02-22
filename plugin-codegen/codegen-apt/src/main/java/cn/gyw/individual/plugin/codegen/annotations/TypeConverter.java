package cn.gyw.individual.plugin.codegen.annotations;

import java.lang.annotation.*;

/**
 * @date 2022/11/5
 */
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Target(ElementType.FIELD)
public @interface TypeConverter {

    String toTypeFullName() default "java.lang.String";
}
