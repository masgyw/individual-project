package cn.gyw.individual.plugin.codegen.processor.api;

import cn.gyw.individual.plugin.codegen.processor.BaseCodeGenProcessor;
import cn.gyw.individual.plugin.codegen.processor.DefaultNameContext;
import cn.gyw.individual.plugin.codegen.processor.query.QueryItem;
import cn.gyw.individual.plugin.codegen.spi.CodeGenProcessor;
import cn.gyw.individual.commons.model.Request;
import com.google.auto.service.AutoService;
import com.squareup.javapoet.TypeSpec;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import java.lang.annotation.Annotation;
import java.util.Objects;
import java.util.Set;

@AutoService(value = CodeGenProcessor.class)
public class CgQueryRequestProcessor extends BaseCodeGenProcessor {

  public static String QUERY_REQUEST_SUFFIX = "QueryRequest";
  @Override
  protected void generateClass(TypeElement typeElement, RoundEnvironment roundEnvironment) {
    DefaultNameContext nameContext = getNameContext(typeElement);
    Set<VariableElement> fields = findFields(typeElement,
        p -> Objects.nonNull(p.getAnnotation(QueryItem.class)));
    TypeSpec.Builder typeSpecBuilder = TypeSpec.classBuilder(nameContext.getQueryRequestClassName())
        .addModifiers(Modifier.PUBLIC)
        .addSuperinterface(Request.class)
        .addAnnotation(Schema.class);
    addSetterAndGetterMethodWithConverter(typeSpecBuilder, fields);
    genJavaSourceFile(generatePackage(typeElement),
        typeElement.getAnnotation(CgQueryRequest.class).sourcePath(), typeSpecBuilder);
  }

  @Override
  public Class<? extends Annotation> getAnnotation() {
    return CgQueryRequest.class;
  }

  @Override
  public String generatePackage(TypeElement typeElement) {
    return typeElement.getAnnotation(CgQueryRequest.class).pkgName();
  }
}
