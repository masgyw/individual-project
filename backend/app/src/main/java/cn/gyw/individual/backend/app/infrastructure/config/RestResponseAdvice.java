package cn.gyw.individual.backend.app.infrastructure.config;

import cn.gyw.individual.commons.model.DataResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Objects;


/**
 * 返回值封装
 */
@ControllerAdvice(basePackages = {"cn.gyw"})
public class RestResponseAdvice implements ResponseBodyAdvice<Object> {

    private final Logger LOGGER = LoggerFactory.getLogger(RestResponseAdvice.class);

    @SuppressWarnings("rawtypes")
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        Class<?> controllerClass = returnType.getContainingClass();
        return controllerClass.isAnnotationPresent(RestController.class)
                || controllerClass.isAnnotationPresent(ResponseBody.class)
                || returnType.getMethodAnnotation(ResponseBody.class) != null
                || controllerClass.equals(GlobalExceptionAdvice.class);
    }

    @SuppressWarnings("rawtypes")
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        Object resp = null;
        if (body != null) {
            resp = body;
        }
        if (request instanceof ServletServerHttpRequest) {
            if (Objects.nonNull(resp) && !(resp instanceof DataResponse)) {
                resp = DataResponse.success(body);
            }
        } else {
            LOGGER.debug("request not servlet web");
        }
        return resp;
    }
}