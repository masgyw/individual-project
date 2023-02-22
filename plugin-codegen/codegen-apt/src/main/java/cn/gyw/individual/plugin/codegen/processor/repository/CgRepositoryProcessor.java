package cn.gyw.individual.plugin.codegen.processor.repository;


import cn.gyw.individual.plugin.codegen.processor.BaseCodeGenProcessor;
import cn.gyw.individual.plugin.codegen.spi.CodeGenProcessor;
import cn.gyw.individual.starters.jpa.support.BaseRepository;
import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeSpec;

import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import java.lang.annotation.Annotation;

@AutoService(value = CodeGenProcessor.class)
public class CgRepositoryProcessor extends BaseCodeGenProcessor {

    public static final String REPOSITORY_SUFFIX = "Repository";

    @Override
    protected void generateClass(TypeElement typeElement, RoundEnvironment roundEnvironment) {
        String className = typeElement.getSimpleName() + REPOSITORY_SUFFIX;
        TypeSpec.Builder typeSpecBuilder = TypeSpec.interfaceBuilder(className)
                .addSuperinterface(ParameterizedTypeName.get(ClassName.get(BaseRepository.class), ClassName.get(typeElement), ClassName.get(Long.class)))
                .addModifiers(Modifier.PUBLIC);
        genJavaSourceFile(generatePackage(typeElement), typeElement.getAnnotation(CgRepository.class).sourcePath(), typeSpecBuilder);
    }

    @Override
    public Class<? extends Annotation> getAnnotation() {
        return CgRepository.class;
    }

    @Override
    public String generatePackage(TypeElement typeElement) {
        return typeElement.getAnnotation(CgRepository.class).pkgName();
    }
}
