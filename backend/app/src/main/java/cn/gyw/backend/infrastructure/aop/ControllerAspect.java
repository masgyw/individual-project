package cn.gyw.backend.infrastructure.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Controller 切面
 *
 * @date 2023/3/29
 */
@Aspect
@Component
public class ControllerAspect {

    /**
     * 对包下所有的controller结尾的类的所有方法增强
     */
    private final String executeExpr = "execution(* cn.gyw.backend..*Controller.*(..))";

    @Around(value = executeExpr)
    public Object process(ProceedingJoinPoint joinPoint) {
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        //获取方法名称
        String methodName = method.getName();
        //获取参数名称
        LocalVariableTableParameterNameDiscoverer paramNames = new LocalVariableTableParameterNameDiscoverer();
        String[] params = paramNames.getParameterNames(method);
        //获取参数
        Object[] args = joinPoint.getArgs();
        //过滤掉request和response,不能序列化
        List<Object> filteredArgs = Arrays.stream(args)
                .filter(arg -> (!(arg instanceof HttpServletRequest) && !(arg instanceof HttpServletResponse)))
                .collect(Collectors.toList());
    }
}
