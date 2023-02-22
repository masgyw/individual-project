package cn.gyw.individual.plugin.codegen.processor.query;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CgQuery {

  String pkgName();

  String sourcePath() default "src/main/java";

  boolean overrideSource() default false;
}
