package cn.gyw.backend.infrastructure.aop;

import org.aspectj.lang.annotation.Pointcut;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.interceptor.CustomizableTraceInterceptor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yuewu.guan
 * @date 2023/2/14
 */
@Configuration
public class TraceConfig {

    @Pointcut("execution(* cn.gyw.backend.*.*(..))")
    private void pointCut() {
    }

    /**
     * 日志拦截器
     */
    @Bean
    public Advisor traceAdvisor() {
        CustomizableTraceInterceptor traceInterceptor = new CustomizableTraceInterceptor();
        traceInterceptor.setEnterMessage("enter");
        traceInterceptor.setExitMessage("exit");
        traceInterceptor.setExceptionMessage("exception");

        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* cn.gyw.backend..*.*(..))");

        return new DefaultPointcutAdvisor(pointcut, traceInterceptor);
    }
}
