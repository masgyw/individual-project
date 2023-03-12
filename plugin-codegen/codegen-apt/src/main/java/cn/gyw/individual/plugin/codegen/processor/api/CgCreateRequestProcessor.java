package cn.gyw.individual.plugin.codegen.processor.api;

import cn.gyw.individual.plugin.codegen.processor.BaseCodeGenProcessor;
import cn.gyw.individual.plugin.codegen.processor.DefaultNameContext;
import cn.gyw.individual.plugin.codegen.processor.creator.IgnoreCreator;
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
public class CgCreateRequestProcessor extends BaseCodeGenProcessor {

    public static final String CREATE_REQUEST_SUFFIX = "CreateRequest";

    @Override
    protected void generateClass(TypeElement typeElement, RoundEnvironment roundEnvironment) {
        DefaultNameContext nameContext = getNameContext(typeElement);
        Set<VariableElement> fields = findFields(typeElement,
                p -> Objects.isNull(p.getAnnotation(IgnoreCreator.class)));
        TypeSpec.Builder typeSpecBuilder = TypeSpec.classBuilder(nameContext.getCreateClassName())
                .addModifiers(Modifier.PUBLIC)
                .addSuperinterface(Request.class)
                .addAnnotation(Schema.class);
        addSetterAndGetterMethodWithConverter(typeSpecBuilder, fields);

        CgCreateRequest cgCreateRequest = typeElement.getAnnotation(CgCreateRequest.class);
        genJavaSourceFile(generatePackage(typeElement),
                cgCreateRequest.sourcePath(), cgCreateRequest.overrideSource(), typeSpecBuilder);
    }

    @Override
    public Class<? extends Annotation> getAnnotation() {
        return CgCreateRequest.class;
    }

    @Override
    public String generatePackage(TypeElement typeElement) {
        return typeElement.getAnnotation(CgCreateRequest.class).pkgName();
    }
}