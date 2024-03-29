package cn.gyw.individual.plugin.codegen.processor.updater;

import cn.gyw.individual.plugin.codegen.processor.BaseCodeGenProcessor;
import cn.gyw.individual.plugin.codegen.spi.CodeGenProcessor;
import com.google.auto.service.AutoService;
import com.google.common.base.CaseFormat;
import com.squareup.javapoet.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import java.lang.annotation.Annotation;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

/**
 * 
 * @Date: 2019/11/28 19:33
 * @Description:
 */

@AutoService(value = CodeGenProcessor.class)
public class CgUpdaterProcessor extends BaseCodeGenProcessor {

  public static final String SUFFIX = "Updater";

  @Override
  protected void generateClass(TypeElement typeElement, RoundEnvironment roundEnvironment) {
    Set<VariableElement> fields = findFields(typeElement,
        p -> Objects.isNull(p.getAnnotation(IgnoreUpdater.class)));
    String className = PREFIX + typeElement.getSimpleName() + SUFFIX;
    String sourceClassName = typeElement.getSimpleName() + SUFFIX;
    TypeSpec.Builder typeSpecBuilder = TypeSpec.classBuilder(className)
        .addModifiers(Modifier.PUBLIC)
        .addAnnotation(Schema.class)
        .addAnnotation(Data.class);
    addSetterAndGetterMethod(typeSpecBuilder, fields);
    CodeBlock.Builder builder = CodeBlock.builder();
    for (VariableElement ve : fields) {
      builder.addStatement("$T.ofNullable($L()).ifPresent(v -> param.$L(v))", Optional.class,
          "get" + CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, ve.getSimpleName().toString()),
          "set" + CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, ve.getSimpleName().toString()));
    }
    MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder(
            "update" + typeElement.getSimpleName())
        .addModifiers(Modifier.PUBLIC)
        .addParameter(TypeName.get(typeElement.asType()), "param")
        .addCode(builder.build())
        .returns(void.class);
    typeSpecBuilder.addMethod(methodBuilder.build());
    typeSpecBuilder.addField(
        FieldSpec.builder(ClassName.get(Long.class), "id", Modifier.PRIVATE).build());
    addIdSetterAndGetter(typeSpecBuilder);
    typeSpecBuilder.addMethod(MethodSpec.constructorBuilder()
        .addModifiers(Modifier.PROTECTED)
        .build());
    String packageName = generatePackage(typeElement);
    genJavaFile(packageName, typeSpecBuilder);
    genJavaFile(packageName, getSourceType(sourceClassName, packageName, className));
  }

  @Override
  public Class<? extends Annotation> getAnnotation() {
    return CgUpdater.class;
  }

  @Override
  public String generatePackage(TypeElement typeElement) {
    return typeElement.getAnnotation(CgUpdater.class).pkgName();
  }
}