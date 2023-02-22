package cn.gyw.individual.plugin.codegen.processor.api;

import java.lang.annotation.*;

/**
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CgUpdateRequest {

    String pkgName();

    String sourcePath() default "src/main/java";

    boolean overrideSource() default false;
}
