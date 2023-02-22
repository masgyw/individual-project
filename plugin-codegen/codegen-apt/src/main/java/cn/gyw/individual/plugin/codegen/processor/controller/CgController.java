package cn.gyw.individual.plugin.codegen.processor.controller;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CgController {

  String pkgName();

  String sourcePath() default "src/main/java";

  boolean overrideSource() default false;
}
