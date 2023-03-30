// package cn.gyw.backend.infrastructure.aop;
//
// import cn.gyw.individual.commons.log.ApiLog;
// import com.alibaba.fastjson.JSON;
// import lombok.extern.slf4j.Slf4j;
// import org.apache.commons.collections.CollectionUtils;
// import org.aspectj.lang.ProceedingJoinPoint;
// import org.aspectj.lang.annotation.Aspect;
// import org.aspectj.lang.annotation.Before;
// import org.aspectj.lang.reflect.MethodSignature;
// import org.slf4j.MDC;
// import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
// import org.springframework.stereotype.Component;
// import org.springframework.web.context.request.RequestAttributes;
// import org.springframework.web.context.request.RequestContextHolder;
// import org.springframework.web.context.request.ServletRequestAttributes;
//
// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;
// import java.lang.reflect.Method;
// import java.util.Arrays;
// import java.util.List;
// import java.util.Map;
// import java.util.Objects;
// import java.util.stream.Collectors;
// import java.util.stream.IntStream;
//
// /**
//  * Controller 切面
//  *
//  * @date 2023/3/29
//  */
// @Slf4j
// @Aspect
// @Component
// public class ControllerAspect {
//
//     /**
//      * 对包下所有的controller结尾的类的所有方法增强
//      */
//     private final String executeExpr = "execution(* cn.gyw.backend..*Controller.*(..))";
//
//     @Before(value = executeExpr)
//     public Object process(ProceedingJoinPoint joinPoint) throws Throwable {
//         RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
//         HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
//         String clazzName = joinPoint.getTarget().getClass().getSimpleName();
//         MDC.put("apiCode", clazzName);
//
//         ApiLog apiLog = new ApiLog();
//         String requestURI = request.getRequestURI();
//         Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
//         String methodName = method.getName();
//         LocalVariableTableParameterNameDiscoverer parameterNameDiscoverer = new LocalVariableTableParameterNameDiscoverer();
//         String[] paramNames = parameterNameDiscoverer.getParameterNames(method);
//         Object[] args = joinPoint.getArgs();
//         List<Object> argList = Arrays.stream(args)
//                 .filter(arg -> !(arg instanceof HttpServletRequest))
//                 .filter(arg -> !(arg instanceof HttpServletResponse))
//                 .collect(Collectors.toList());
//         if (Objects.nonNull(paramNames) && CollectionUtils.isNotEmpty(argList)) {
//             Map<String, Object> reqParam = IntStream.range(0, argList.size()).boxed()
//                     .collect(Collectors.toMap(idx -> paramNames[idx], argList::get));
//             apiLog.setReqData(JSON.toJSONString(reqParam));
//         }
//         apiLog.setMethod(methodName);
//         apiLog.setActionUrl(requestURI);
//         log.info("请求信息：{}", JSON.toJSONString(apiLog));
//
//         long startTime = System.currentTimeMillis();
//         Object result;
//         try {
//             result = joinPoint.proceed(args);
//         } catch (Throwable e) {
//             throw e;
//         }
//         log.info("响应信息：{}，执行耗时:{} ms", JSON.toJSONString(result), (System.currentTimeMillis() - startTime));
//         return result;
//     }
// }
