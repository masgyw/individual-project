package cn.gyw.individual.plugin.codegen.processor.api;

import cn.gyw.individual.plugin.codegen.processor.BaseCodeGenProcessor;
import cn.gyw.individual.plugin.codegen.processor.DefaultNameContext;
import cn.gyw.individual.plugin.codegen.processor.updater.IgnoreUpdater;
import cn.gyw.individual.plugin.codegen.spi.CodeGenProcessor;
import cn.gyw.individual.commons.model.Request;
import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
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
public class CgUpdateRequestProcessor extends BaseCodeGenProcessor {

    public static String UPDATE_REQUEST_SUFFIX = "UpdateRequest";

    @Override
    protected void generateClass(TypeElement typeElement, RoundEnvironment roundEnvironment) {
        DefaultNameContext nameContext = getNameContext(typeElement);
        Set<VariableElement> fields = findFields(typeElement,
                p -> Objects.isNull(p.getAnnotation(IgnoreUpdater.class)));
        TypeSpec.Builder typeSpecBuilder = TypeSpec.classBuilder(nameContext.getUpdateClassName())
                .addModifiers(Modifier.PUBLIC)
                .addSuperinterface(Request.class)
                .addAnnotation(Schema.class);

        addSetterAndGetterMethodWithConverter(typeSpecBuilder, fields);
        typeSpecBuilder.addField(
                FieldSpec.builder(ClassName.get(Long.class), "id", Modifier.PRIVATE).build());
        addIdSetterAndGetter(typeSpecBuilder);

        CgUpdateRequest cgUpdateRequest = typeElement.getAnnotation(CgUpdateRequest.class);
        genJavaSourceFile(generatePackage(typeElement),
                cgUpdateRequest.sourcePath(), cgUpdateRequest.overrideSource(), typeSpecBuilder);
    }

    @Override
    public Class<? extends Annotation> getAnnotation() {
        return CgUpdateRequest.class;
    }

    @Override
    public String generatePackage(TypeElement typeElement) {
        return typeElement.getAnnotation(CgUpdateRequest.class).pkgName();
    }
}
