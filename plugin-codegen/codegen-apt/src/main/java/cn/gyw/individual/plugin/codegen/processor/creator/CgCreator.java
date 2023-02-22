package cn.gyw.individual.plugin.codegen.processor.creator;


import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CgCreator {

  String pkgName();

  String sourcePath() default "src/main/java";

  boolean overrideSource() default false;
}
