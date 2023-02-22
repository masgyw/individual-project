package cn.gyw.individual.plugin.codegen.processor.service;

import java.lang.annotation.*;

/**
 * 
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CgService {

  String pkgName();

  String sourcePath() default "src/main/java";

  boolean overrideSource() default false;
}
