package cn.gyw.individual.plugin.codegen.processor;

import cn.gyw.individual.plugin.codegen.context.ProcessingEnvHolder;
import cn.gyw.individual.plugin.codegen.registry.CodeGenProcessorRegistry;
import cn.gyw.individual.plugin.codegen.spi.CodeGenProcessor;
import com.google.auto.service.AutoService;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.ElementFilter;
import javax.tools.Diagnostic.Kind;
import java.util.Set;

@AutoService(Processor.class)
public class MainCodeGenProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        annotations.forEach(an -> {
            Set<? extends Element> typeElements = roundEnv.getElementsAnnotatedWith(an);
            Set<TypeElement> types = ElementFilter.typesIn(typeElements);
            for (TypeElement typeElement : types) {
                CodeGenProcessor codeGenProcessor = CodeGenProcessorRegistry.find(
                        an.getQualifiedName().toString());
                try {
                    codeGenProcessor.generate(typeElement, roundEnv);
                } catch (Exception e) {
                    ProcessingEnvHolder.getEnvironment().getMessager()
                            .printMessage(Kind.ERROR, "代码生成异常:" + e.getMessage());
                }
            }

        });
        return false;
    }

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        ProcessingEnvHolder.setEnvironment(processingEnv);
        CodeGenProcessorRegistry.initProcessors();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return CodeGenProcessorRegistry.getSupportedAnnotations();
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }
}
