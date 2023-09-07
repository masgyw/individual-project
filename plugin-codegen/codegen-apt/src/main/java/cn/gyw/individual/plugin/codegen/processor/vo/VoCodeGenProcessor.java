package cn.gyw.individual.plugin.codegen.processor.vo;

import cn.gyw.individual.plugin.codegen.processor.BaseCodeGenProcessor;
import cn.gyw.individual.plugin.codegen.spi.CodeGenProcessor;
import com.google.auto.service.AutoService;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import com.squareup.javapoet.TypeSpec.Builder;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.data.annotation.Transient;

import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import java.lang.annotation.Annotation;
import java.time.Instant;
import java.util.Objects;
import java.util.Set;

/**
 * vo 代码生成器
 */
@AutoService(value = CodeGenProcessor.class)
public class VoCodeGenProcessor extends BaseCodeGenProcessor {

    public static final String SUFFIX = "VO";

    @Override
    public Class<? extends Annotation> getAnnotation() {
        return CgVo.class;
    }

    @Override
    public String generatePackage(TypeElement typeElement) {
        return typeElement.getAnnotation(CgVo.class).pkgName();
    }

    @Override
    protected void generateClass(TypeElement typeElement, RoundEnvironment roundEnvironment) {
        Set<VariableElement> fields = findFields(typeElement,
                ve -> Objects.isNull(ve.getAnnotation(IgnoreVo.class)));
        // 获取父类变量
        Set<VariableElement> parentFields = findParentFields(typeElement.getSuperclass(),
                ve -> Objects.isNull(ve.getAnnotation(IgnoreVo.class)) && Objects.isNull(ve.getAnnotation(Transient.class)));
        // target dir
        String className = PREFIX + typeElement.getSimpleName() + SUFFIX;
        // source dir
        String sourceClassName = typeElement.getSimpleName() + SUFFIX;
        Builder builder = TypeSpec.classBuilder(className)
                .superclass(AbstractBaseVO.class)
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(Schema.class)
                .addAnnotation(Data.class);
        addSetterAndGetterMethod(builder, fields);
        MethodSpec.Builder constructorSpecBuilder = MethodSpec.constructorBuilder()
                .addParameter(TypeName.get(typeElement.asType()), "source")
                .addModifiers(Modifier.PUBLIC);
        fields.stream().forEach(f -> {
            constructorSpecBuilder.addStatement("this.set$L(source.get$L())", getFieldDefaultName(f),
                    getFieldDefaultName(f));
        });

        parentFields.forEach(f -> {
            // 日期类型不处理
            if (!Instant.class.getName().equals(f.asType().toString())) {
                String fieldName = getFieldDefaultName(f);
                constructorSpecBuilder.addStatement("this.set$L(source.get$L())", fieldName, fieldName);
            }
        });
        builder.addMethod(MethodSpec.constructorBuilder()
                .addModifiers(Modifier.PROTECTED)
                .build());
        builder.addMethod(constructorSpecBuilder.build());
        String packageName = generatePackage(typeElement);
        genJavaFile(packageName, builder);
        genJavaFile(packageName, getSourceTypeWithConstruct(typeElement, sourceClassName, packageName, className));
    }
}
