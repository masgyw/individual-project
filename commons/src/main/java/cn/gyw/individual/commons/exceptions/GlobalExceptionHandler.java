package cn.gyw.individual.commons.exceptions;

import cn.gyw.individual.commons.enums.CodeEnum;
import cn.gyw.individual.commons.i18n.I18nMessageSource;
import cn.gyw.individual.commons.model.DataResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * 全局异常处理
 */
@Component
@ConditionalOnWebApplication
@RestControllerAdvice
public class GlobalExceptionHandler {

    private final static Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private final static String ENV_PROD = "prod";

    @Value("${spring.profiles.active:default}")
    private String profile;

    @Autowired
    private I18nMessageSource messageSource;

    /**
     * 自定义业务异常捕获
     */
    @ExceptionHandler(value = BusinessException.class)
    public DataResponse handleBusinessException(BaseException e) {
        return DataResponse.fail(e.getCodeEnum());
    }

    /**
     * 自定义通用异常捕获
     */
    @ExceptionHandler(value = BaseException.class)
    public DataResponse handleBaseException(BaseException e) {
        return DataResponse.fail(e.getCodeEnum());
    }

    /**
     * Controller 上一层异常处理 servletException ==> Controller ==> BaseException ==>
     * Service
     */
    @ExceptionHandler({NoHandlerFoundException.class, HttpRequestMethodNotSupportedException.class,
            HttpMediaTypeNotSupportedException.class, MissingPathVariableException.class,
            MissingServletRequestParameterException.class, TypeMismatchException.class,
            HttpMessageNotReadableException.class, HttpMessageNotWritableException.class,
            HttpMediaTypeNotAcceptableException.class, ServletRequestBindingException.class,
            ConversionNotSupportedException.class, MissingServletRequestPartException.class,
            AsyncRequestTimeoutException.class})
    public DataResponse handleServletException(Exception e) {
        LOGGER.error("Servlet exception :", e);
        if (ENV_PROD.equals(profile)) {
            // 当为生产环境, 不适合把具体的异常信息展示给用户, 比如404.
            return DataResponse.fail(CodeEnum.SystemError);
        }
        return DataResponse.fail(e.getClass().getSimpleName());
    }

    /**
     * 参数绑定异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = BindException.class)
    public DataResponse handleBindException(BindException e) {
        BindingResult bindResult = e.getBindingResult();
        StringBuilder msg = new StringBuilder();
        for (ObjectError error : bindResult.getAllErrors()) {
            msg.append(", ");
            if (error instanceof FieldError) {
                msg.append(((FieldError) error).getField()).append(":");
            }
            msg.append(error.getDefaultMessage() == null ? "" : error.getDefaultMessage());
        }
        return DataResponse.fail(msg.substring(2));
    }

    /**
     * 未定义异常
     */
    @ExceptionHandler(value = Exception.class)
    public DataResponse handleException(Exception e) {
        LOGGER.error("Runtime exception :", e);
        if (ENV_PROD.equals(profile)) {
            // 生产环境异常处理
        }
        return DataResponse.fail(e.getMessage());
    }

    private String getMessage(BaseException e) {
        /*
         * String code = "response." + e.getResponseEnum().toString(); String message =
         * unifiedMessageSource.getMessage(code, e.getArgs()); if (message == null ||
         * message.isEmpty()) { return e.getMessage(); }
         */
        String message = e.getMessage();
        return message;
    }
}
