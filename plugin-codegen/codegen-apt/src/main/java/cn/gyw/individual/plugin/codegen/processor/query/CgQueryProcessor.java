package cn.gyw.individual.plugin.codegen.processor.query;

import cn.gyw.individual.plugin.codegen.processor.BaseCodeGenProcessor;
import cn.gyw.individual.plugin.codegen.spi.CodeGenProcessor;
import com.google.auto.service.AutoService;
import com.squareup.javapoet.TypeSpec;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import java.lang.annotation.Annotation;
import java.util.Objects;

@AutoService(value = CodeGenProcessor.class)
public class CgQueryProcessor extends BaseCodeGenProcessor {

    public static String QUERY_SUFFIX = "Query";

    @Override
    protected void generateClass(TypeElement typeElement, RoundEnvironment roundEnvironment) {
        String className = PREFIX + typeElement.getSimpleName() + QUERY_SUFFIX;
        String sourceClassName = typeElement.getSimpleName() + QUERY_SUFFIX;
        TypeSpec.Builder builder = TypeSpec.classBuilder(className)
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(Schema.class)
                .addAnnotation(Data.class);
        addSetterAndGetterMethod(builder, findFields(typeElement, ve -> Objects.nonNull(ve.getAnnotation(
                QueryItem.class))));
        String packageName = generatePackage(typeElement);

        // 生成Base基类
        genJavaFile(packageName, builder);
        CgQuery cgQuery = typeElement.getAnnotation(CgQuery.class);
        genJavaSourceFile(packageName, cgQuery.sourcePath(), cgQuery.overrideSource(), getSourceType(sourceClassName, packageName, className));
    }

    @Override
    public Class<? extends Annotation> getAnnotation() {
        return CgQuery.class;
    }

    @Override
    public String generatePackage(TypeElement typeElement) {
        return typeElement.getAnnotation(CgQuery.class).pkgName();
    }
}
