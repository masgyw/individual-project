package cn.gyw.individual.plugin.codegen.processor;

import cn.gyw.individual.plugin.codegen.annotations.FieldDesc;
import cn.gyw.individual.plugin.codegen.annotations.TypeConverter;
import cn.gyw.individual.plugin.codegen.context.ProcessingEnvHolder;
import cn.gyw.individual.plugin.codegen.processor.controller.CgController;
import cn.gyw.individual.plugin.codegen.processor.controller.CgControllerProcessor;
import cn.gyw.individual.plugin.codegen.processor.creator.CreatorCodeGenProcessor;
import cn.gyw.individual.plugin.codegen.processor.creator.CgCreator;
import cn.gyw.individual.plugin.codegen.processor.mapper.CgMapper;
import cn.gyw.individual.plugin.codegen.processor.mapper.CgMapperProcessor;
import cn.gyw.individual.plugin.codegen.processor.query.CgQuery;
import cn.gyw.individual.plugin.codegen.processor.query.CgQueryProcessor;
import cn.gyw.individual.plugin.codegen.processor.repository.CgRepository;
import cn.gyw.individual.plugin.codegen.processor.repository.CgRepositoryProcessor;
import cn.gyw.individual.plugin.codegen.processor.service.CgService;
import cn.gyw.individual.plugin.codegen.processor.service.CgServiceImpl;
import cn.gyw.individual.plugin.codegen.processor.service.CgServiceImplProcessor;
import cn.gyw.individual.plugin.codegen.processor.service.CgServiceProcessor;
import cn.gyw.individual.plugin.codegen.processor.updater.CgUpdater;
import cn.gyw.individual.plugin.codegen.processor.updater.CgUpdaterProcessor;
import cn.gyw.individual.plugin.codegen.processor.vo.CgVo;
import cn.gyw.individual.plugin.codegen.processor.vo.VoCodeGenProcessor;
import cn.gyw.individual.plugin.codegen.spi.CodeGenProcessor;
import cn.gyw.individual.plugin.codegen.processor.api.*;
import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.squareup.javapoet.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.annotation.processing.Filer;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.ElementFilter;
import javax.tools.Diagnostic.Kind;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Predicate;

public abstract class BaseCodeGenProcessor implements CodeGenProcessor {

    public static final String PREFIX = "Base";

    @Override
    public void generate(TypeElement typeElement, RoundEnvironment roundEnvironment)
            throws Exception {
        //添加其他逻辑扩展
        generateClass(typeElement, roundEnvironment);
    }

    /**
     * 生成class 类
     *
     * @param typeElement
     * @param roundEnvironment
     * @return
     */
    protected abstract void generateClass(TypeElement typeElement,
                                          RoundEnvironment roundEnvironment);

    /**
     * 过滤属性
     *
     * @param typeElement
     * @param predicate
     * @return
     */
    public Set<VariableElement> findFields(TypeElement typeElement,
                                           Predicate<VariableElement> predicate) {
        List<? extends Element> fieldTypes = typeElement.getEnclosedElements();
        Set<VariableElement> variableElements = new LinkedHashSet<>();
        for (VariableElement e : ElementFilter.fieldsIn(fieldTypes)) {
            if (predicate.test(e)) {
                variableElements.add(e);
            }
        }
        return variableElements;
    }

    /**
     * 获取名称默认上下文
     *
     * @param typeElement
     * @return
     */
    public DefaultNameContext getNameContext(TypeElement typeElement) {
        DefaultNameContext context = new DefaultNameContext();
        String serviceName = CgServiceProcessor.SERVICE_PREFIX + typeElement.getSimpleName()
                + CgServiceProcessor.SERVICE_SUFFIX;
        String implName = typeElement.getSimpleName() + CgServiceImplProcessor.IMPL_SUFFIX;
        String repositoryName = typeElement.getSimpleName() + CgRepositoryProcessor.REPOSITORY_SUFFIX;
        String mapperName = typeElement.getSimpleName() + CgMapperProcessor.SUFFIX;
        String voName = typeElement.getSimpleName() + VoCodeGenProcessor.SUFFIX;
        String queryName = typeElement.getSimpleName() + CgQueryProcessor.QUERY_SUFFIX;
        String creatorName = typeElement.getSimpleName() + CreatorCodeGenProcessor.SUFFIX;
        String updaterName = typeElement.getSimpleName() + CgUpdaterProcessor.SUFFIX;
        String createRequestName =
                typeElement.getSimpleName() + CgCreateRequestProcessor.CREATE_REQUEST_SUFFIX;
        String updateRequestName =
                typeElement.getSimpleName() + CgUpdateRequestProcessor.UPDATE_REQUEST_SUFFIX;
        String queryRequestName =
                typeElement.getSimpleName() + CgQueryRequestProcessor.QUERY_REQUEST_SUFFIX;
        String responseName = typeElement.getSimpleName() + CgResponseProcessor.RESPONSE_SUFFIX;
        String controllerName = typeElement.getSimpleName() + CgControllerProcessor.CONTROLLER_SUFFIX;
        context.setServiceClassName(serviceName);
        context.setRepositoryClassName(repositoryName);
        context.setMapperClassName(mapperName);
        context.setVoClassName(voName);
        context.setQueryClassName(queryName);
        context.setCreatorClassName(creatorName);
        context.setUpdaterClassName(updaterName);
        context.setImplClassName(implName);
        context.setCreateClassName(createRequestName);
        context.setUpdateClassName(updateRequestName);
        context.setQueryRequestClassName(queryRequestName);
        context.setResponseClassName(responseName);
        context.setControllerClassName(controllerName);
        Optional.ofNullable(typeElement.getAnnotation(CgCreator.class)).ifPresent(anno -> {
            context.setCreatorPackageName(anno.pkgName());
        });
        Optional.ofNullable(typeElement.getAnnotation(CgUpdater.class)).ifPresent(anno -> {
            context.setUpdaterPackageName(anno.pkgName());
        });
        Optional.ofNullable(typeElement.getAnnotation(CgQuery.class)).ifPresent(anno -> {
            context.setQueryPackageName(anno.pkgName());
        });
        Optional.ofNullable(typeElement.getAnnotation(CgVo.class)).ifPresent(anno -> {
            context.setVoPackageName(anno.pkgName());
        });
        Optional.ofNullable(typeElement.getAnnotation(CgRepository.class)).ifPresent(anno -> {
            context.setRepositoryPackageName(anno.pkgName());
        });
        Optional.ofNullable(typeElement.getAnnotation(CgMapper.class)).ifPresent(anno -> {
            context.setMapperPackageName(anno.pkgName());
        });
        Optional.ofNullable(typeElement.getAnnotation(CgService.class)).ifPresent(anno -> {
            context.setServicePackageName(anno.pkgName());
        });
        Optional.ofNullable(typeElement.getAnnotation(CgServiceImpl.class)).ifPresent(anno -> {
            context.setImplPackageName(anno.pkgName());
        });
        Optional.ofNullable(typeElement.getAnnotation(CgCreateRequest.class)).ifPresent(anno -> {
            context.setCreatePackageName(anno.pkgName());
        });
        Optional.ofNullable(typeElement.getAnnotation(CgUpdateRequest.class)).ifPresent(anno -> {
            context.setUpdatePackageName(anno.pkgName());
        });
        Optional.ofNullable(typeElement.getAnnotation(CgQueryRequest.class)).ifPresent(anno -> {
            context.setQueryRequestPackageName(anno.pkgName());
        });
        Optional.ofNullable(typeElement.getAnnotation(CgResponse.class)).ifPresent(anno -> {
            context.setResponsePackageName(anno.pkgName());
        });
        Optional.ofNullable(typeElement.getAnnotation(CgController.class)).ifPresent(anno -> {
            context.setControllerPackageName(anno.pkgName());
        });
        return context;
    }

    /**
     * 获取父类
     *
     * @param element
     * @return
     */
    public TypeElement getSuperClass(TypeElement element) {
        TypeMirror parent = element.getSuperclass();
        if (parent instanceof DeclaredType) {
            Element elt = ((DeclaredType) parent).asElement();
            if (elt instanceof TypeElement) {
                return (TypeElement) elt;
            }
        }
        return null;
    }

    public void addSetterAndGetterMethod(TypeSpec.Builder builder,
                                         Set<VariableElement> variableElements) {
        for (VariableElement ve : variableElements) {
            TypeName typeName = TypeName.get(ve.asType());
            FieldSpec.Builder fieldSpec = FieldSpec
                    .builder(typeName, ve.getSimpleName().toString(), Modifier.PRIVATE)
                    .addAnnotation(AnnotationSpec.builder(Schema.class)
                            .addMember("title", "$S", getFieldDesc(ve))
                            .build());
            builder.addField(fieldSpec.build());
            String fieldName = getFieldDefaultName(ve);
            MethodSpec.Builder getMethod = MethodSpec.methodBuilder("get" + fieldName)
                    .returns(typeName)
                    .addModifiers(Modifier.PUBLIC)
                    .addStatement("return $L", ve.getSimpleName().toString());
            MethodSpec.Builder setMethod = MethodSpec.methodBuilder("set" + fieldName)
                    .returns(void.class)
                    .addModifiers(Modifier.PUBLIC)
                    .addParameter(typeName, ve.getSimpleName().toString())
                    .addStatement("this.$L = $L", ve.getSimpleName().toString(),
                            ve.getSimpleName().toString());
            builder.addMethod(getMethod.build());
            builder.addMethod(setMethod.build());
        }
    }

    /**
     * 应用转化器
     *
     * @param builder
     * @param variableElements
     */
    public void addSetterAndGetterMethodWithConverter(TypeSpec.Builder builder,
                                                      Set<VariableElement> variableElements) {
        for (VariableElement ve : variableElements) {
            TypeName typeName;
            if (Objects.nonNull(ve.getAnnotation(TypeConverter.class))) {
                //这里处理下泛型的情况，比如List<String> 这种，TypeConverter FullName 用逗号分隔"java.lang.List
                String fullName = ve.getAnnotation(TypeConverter.class).toTypeFullName();
                Iterable<String> classes = Splitter.on(",").split(fullName);
                int size = Iterables.size(classes);
                if (size > 1) {
                    //泛型生成像这样
                    //ParameterizedTypeName.get(ClassName.get(JsonObject.class), ClassName.get(String.class))
                    typeName = ParameterizedTypeName.get(ClassName.bestGuess(Iterables.get(classes, 0)), ClassName.bestGuess(Iterables.get(classes, 1)));
                } else {
                    typeName = ClassName.bestGuess(ve.getAnnotation(TypeConverter.class).toTypeFullName());
                }
            } else {
                typeName = TypeName.get(ve.asType());
            }
            FieldSpec.Builder fieldSpec = FieldSpec
                    .builder(typeName, ve.getSimpleName().toString(), Modifier.PRIVATE)
                    .addAnnotation(AnnotationSpec.builder(Schema.class)
                            .addMember("title", "$S", getFieldDesc(ve))
                            .build());
            builder.addField(fieldSpec.build());
            String fieldName = getFieldDefaultName(ve);
            MethodSpec.Builder getMethod = MethodSpec.methodBuilder("get" + fieldName)
                    .returns(typeName)
                    .addModifiers(Modifier.PUBLIC)
                    .addStatement("return $L", ve.getSimpleName().toString());
            MethodSpec.Builder setMethod = MethodSpec.methodBuilder("set" + fieldName)
                    .returns(void.class)
                    .addModifiers(Modifier.PUBLIC)
                    .addParameter(typeName, ve.getSimpleName().toString())
                    .addStatement("this.$L = $L", ve.getSimpleName().toString(),
                            ve.getSimpleName().toString());
            builder.addMethod(getMethod.build());
            builder.addMethod(setMethod.build());
        }
    }


    protected void addIdSetterAndGetter(TypeSpec.Builder builder) {
        MethodSpec.Builder getMethod = MethodSpec.methodBuilder("getId")
                .returns(ClassName.get(Long.class))
                .addModifiers(Modifier.PUBLIC)
                .addStatement("return $L", "id");
        MethodSpec.Builder setMethod = MethodSpec.methodBuilder("setId")
                .returns(void.class)
                .addModifiers(Modifier.PUBLIC)
                .addParameter(TypeName.LONG, "id")
                .addStatement("this.$L = $L", "id", "id");
        builder.addMethod(getMethod.build());
        builder.addMethod(setMethod.build());
    }

    protected String getFieldDesc(VariableElement ve) {
        return Optional.ofNullable(ve.getAnnotation(FieldDesc.class))
                .map(s -> s.name()).orElse(ve.getSimpleName().toString());
    }

    protected String getFieldDefaultName(VariableElement ve) {
        return ve.getSimpleName().toString().substring(0, 1).toUpperCase() + ve.getSimpleName()
                .toString().substring(1);
    }


    public void genJavaSourceFile(String packageName, String pathStr, boolean overrideSource,
                                  TypeSpec.Builder typeSpecBuilder) {
        TypeSpec typeSpec = typeSpecBuilder.build();
        JavaFile javaFile = JavaFile
                .builder(packageName, typeSpec)
                .addFileComment("---Auto Generated ---")
                .build();
        String packagePath =
                packageName.replace(".", File.separator) + File.separator + typeSpec.name + ".java";
        try {
            Path path = Paths.get(System.getProperty("user.dir") + File.separator + pathStr);
            File file = new File(path.toFile().getAbsolutePath());
            if (!file.exists()) {
                System.err.println("源码文件不存在：" + file.getAbsolutePath());
                return;
            }
            String sourceFileName = path.toFile().getAbsolutePath() + File.separator + packagePath;
            File sourceFile = new File(sourceFileName);
            if (sourceFile.exists() && !overrideSource) {
                System.out.println("源码文件存在不生成：" + file.getAbsolutePath());
                return;
            }
            // 不存在 或 覆盖，先删除源文件后创建
            Files.deleteIfExists(Paths.get(sourceFileName));
            javaFile.writeTo(file);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public TypeSpec.Builder getSourceType(String sourceName, String packageName,
                                          String superClassName) {
        TypeSpec.Builder sourceBuilder = TypeSpec.classBuilder(sourceName)
                .superclass(ClassName.get(packageName, superClassName))
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(Schema.class)
                .addAnnotation(Data.class);
        return sourceBuilder;
    }

    public TypeSpec.Builder getSourceTypeWithConstruct(TypeElement e, String sourceName,
                                                       String packageName, String superClassName) {
        MethodSpec.Builder constructorSpecBuilder = MethodSpec.constructorBuilder()
                .addParameter(TypeName.get(e.asType()), "source")
                .addModifiers(Modifier.PUBLIC);
        constructorSpecBuilder.addStatement("super(source)");
        TypeSpec.Builder sourceBuilder = TypeSpec.classBuilder(sourceName)
                .superclass(ClassName.get(packageName, superClassName))
                .addModifiers(Modifier.PUBLIC)
                .addMethod(MethodSpec.constructorBuilder()
                        .addModifiers(Modifier.PUBLIC)
                        .build())
                .addMethod(constructorSpecBuilder.build())
                .addAnnotation(Schema.class)
                .addAnnotation(Data.class);
        return sourceBuilder;
    }


    protected void genJavaFile(String packageName, TypeSpec.Builder typeSpecBuilder) {
        TypeSpec typeSpec = typeSpecBuilder.build();
        JavaFile javaFile = JavaFile.builder(packageName, typeSpec)
                .addFileComment("---Auto Generated ---").build();
        Filer filer = ProcessingEnvHolder.getEnvironment().getFiler();
        try {
            javaFile.writeTo(filer);
        } catch (IOException e) {
            ProcessingEnvHolder.getEnvironment().getMessager()
                    .printMessage(Kind.NOTE, e.getMessage());
        }
    }

}
