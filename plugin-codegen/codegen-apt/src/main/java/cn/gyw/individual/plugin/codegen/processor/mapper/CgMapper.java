package cn.gyw.individual.plugin.codegen.processor.mapper;

public @interface CgMapper {

    String pkgName();

    String sourcePath() default "src/main/java";

    boolean overrideSource() default false;
}
