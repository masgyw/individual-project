package cn.gyw.backend.infrastructure.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.MDC;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller 切面
 *
 * @date 2023/3/29
 */
@Slf4j
@Aspect
@Component
public class ControllerAspect {

    /**
     * 对包下所有的controller结尾的类的所有方法增强
     */
    private final String executeExpr = "execution(* cn.gyw.backend..*Controller.*(..))";

    @Around(value = executeExpr)
    public Object process(ProceedingJoinPoint joinPoint) throws Throwable {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();

        //获取代理地址、请求地址、请求类名、方法名
        String requestURI = request.getRequestURI();
        String methodName = joinPoint.getSignature().getName();
        String clazzName = joinPoint.getTarget().getClass().getSimpleName();
        MDC.put("apiCode", clazzName);
        log.info("requestUri={},methodName={}", requestURI, methodName);
        Object result;
        result = joinPoint.proceed();
        return result;
    }
}
