package cn.gyw.individual.plugin.codegen.processor.updater;

public @interface CgUpdater {

  String pkgName();

  String sourcePath() default "src/main/java";

  boolean overrideSource() default false;
}
