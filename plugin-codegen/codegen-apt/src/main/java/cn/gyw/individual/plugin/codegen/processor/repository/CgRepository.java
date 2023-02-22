package cn.gyw.individual.plugin.codegen.processor.repository;

import java.lang.annotation.*;

/**
 * 
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CgRepository {

  String pkgName();

  String sourcePath() default "src/main/java";

  boolean overrideSource() default false;
}
