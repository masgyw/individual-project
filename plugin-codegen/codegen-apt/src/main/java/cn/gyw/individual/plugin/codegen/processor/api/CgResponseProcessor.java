package cn.gyw.individual.plugin.codegen.processor.api;

import cn.gyw.individual.plugin.codegen.processor.BaseCodeGenProcessor;
import cn.gyw.individual.plugin.codegen.processor.DefaultNameContext;
import cn.gyw.individual.plugin.codegen.processor.vo.IgnoreVo;
import cn.gyw.individual.plugin.codegen.spi.CodeGenProcessor;
import cn.gyw.individual.commons.model.AbstractResponse;
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
public class CgResponseProcessor extends BaseCodeGenProcessor {

    public static String RESPONSE_SUFFIX = "Response";

    @Override
    protected void generateClass(TypeElement typeElement, RoundEnvironment roundEnvironment) {
        DefaultNameContext nameContext = getNameContext(typeElement);
        Set<VariableElement> fields = findFields(typeElement,
                p -> Objects.isNull(p.getAnnotation(IgnoreVo.class)));
        TypeSpec.Builder typeSpecBuilder = TypeSpec.classBuilder(nameContext.getResponseClassName())
                .addModifiers(Modifier.PUBLIC)
                .superclass(AbstractResponse.class)
                .addAnnotation(Schema.class);
        addSetterAndGetterMethodWithConverter(typeSpecBuilder, fields);

        CgResponse cgResponse = typeElement.getAnnotation(CgResponse.class);
        genJavaSourceFile(generatePackage(typeElement),
                cgResponse.sourcePath(), cgResponse.overrideSource(), typeSpecBuilder);
    }

    @Override
    public Class<? extends Annotation> getAnnotation() {
        return CgResponse.class;
    }

    @Override
    public String generatePackage(TypeElement typeElement) {
        return typeElement.getAnnotation(CgResponse.class).pkgName();
    }
}
