package cn.gyw.individual.plugin.codegen.context;

import javax.annotation.processing.ProcessingEnvironment;

/**
 * 工具类上下文holder
 */
public final class ProcessingEnvHolder {

    public static final ThreadLocal<ProcessingEnvironment> environment = new ThreadLocal<>();

    public static void setEnvironment(ProcessingEnvironment pe) {
        environment.set(pe);
    }

    public static ProcessingEnvironment getEnvironment() {
        return environment.get();
    }
}
