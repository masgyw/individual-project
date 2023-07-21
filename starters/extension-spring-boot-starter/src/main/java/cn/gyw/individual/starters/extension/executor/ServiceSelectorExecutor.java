package cn.gyw.individual.starters.extension.executor;

import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @date 2023/7/21
 */
@Component
public class ServiceSelectorExecutor extends AbstractServiceSelectorExecutor
        implements ApplicationContextAware, SmartInitializingSingleton {

    private ApplicationContext applicationContext;

    private Map<String, Object> extBeans = new ConcurrentHashMap<>();

    @SuppressWarnings("unchecked")
    @Override
    protected <S> S selectService(BizScene bizScene, Class<S> clazz) {
        if (extBeans.containsKey(bizScene.getBizId())) {
            throw new RuntimeException("服务未注册");
        }
        return (S) extBeans.get(bizScene.getBizId());
    }

    @Override
    public void afterSingletonsInstantiated() {
        Map<String, Object> extensionBeans = this.applicationContext.getBeansWithAnnotation(Extension.class);
        extensionBeans.entrySet().stream().forEach(entry -> {
            Class<?> extClass = entry.getValue().getClass();
            if (AopUtils.isAopProxy(entry.getValue())) {
                extClass = ClassUtils.getUserClass(entry.getValue());
            }
            Extension extensionPlus = AnnotationUtils.findAnnotation(extClass, Extension.class);
            if (extBeans.containsKey(extensionPlus.bizId())) {
                throw new RuntimeException("bizId已经存在");
            }
            extBeans.put(extensionPlus.bizId(), entry.getValue());
        });
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
