package cn.gyw.individual.commons.utils;

import cn.gyw.individual.commons.annotations.NeedSetValueField;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class SpringContextUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    private static Environment environment = null;

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static <T> T getBean(Class<T> type) {
        return applicationContext.getBean(type);
    }

    public static <T> T getBean(String beanId, Class<T> type) {
        return applicationContext.getBean(beanId, type);
    }

    public static String getValue(String key, String defaultValue) {
        try {
            return environment.getProperty(key, defaultValue);
        } catch (Exception e) {
            log.error(String.format("spring environment get [%s] error:", key), e);
        }
        return defaultValue;
    }

    /**
     * 设置@NeedSetValueField 注解修饰的值
     *
     * @param obj
     * @throws Exception
     */
    public void doSetNeedValueField(Object obj) throws Exception {
        Map<String, Object> cache = new HashMap<>(); // 缓存
        Class<?> clazz = obj.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            if (!field.isAnnotationPresent(NeedSetValueField.class)) {
                continue;
            }
            field.setAccessible(true);
            NeedSetValueField needSetValueField = field.getAnnotation(NeedSetValueField.class);
            Class<?> beanClass = needSetValueField.beanClass();
            String paramSrc = needSetValueField.param();
            String methodSrc = needSetValueField.method();
            String targetFieldSrc = needSetValueField.targetField();

            Object bean = applicationContext.getBean(beanClass);
            Method method = beanClass.getMethod(methodSrc, clazz.getDeclaredField(paramSrc).getType());
            Field paramField = clazz.getDeclaredField(paramSrc);
            paramField.setAccessible(true);
            Boolean isNeedSetValue = StringUtils.isEmpty(targetFieldSrc);
            if (isNeedSetValue) { // 若无target，不进行设值
                continue;
            }
            String keyPrefix = beanClass + "_" + methodSrc + "_" + targetFieldSrc + "_";
            Object paramValue = paramField.get(obj); // 请求参数
            String key = keyPrefix + paramValue;

            Object value = null;
            if (cache.containsKey(key)) {
                value = cache.get(key);
            } else {
                Object target = method.invoke(bean, paramValue);
                Field targetField = target.getClass().getDeclaredField(targetFieldSrc);
                targetField.setAccessible(true);
                value = targetField.get(target);
                cache.put(key, value);
            }
            // set value on field
            field.set(obj, value);
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtil.applicationContext = applicationContext;
    }
}
