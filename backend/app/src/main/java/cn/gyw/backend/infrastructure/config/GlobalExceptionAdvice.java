package cn.gyw.backend.infrastructure.config;

import cn.gyw.individual.commons.enums.CodeEnum;
import cn.gyw.individual.commons.exceptions.BaseException;
import cn.gyw.individual.commons.exceptions.BusinessException;
import cn.gyw.individual.commons.model.DataResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
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
 * 异常处理切面
 *
 * @date 2023/3/7
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionAdvice {

    /**
     * 自定义业务异常捕获
     */
    @ExceptionHandler(value = BusinessException.class)
    public DataResponse<?> handleBusinessException(BaseException e) {
        return DataResponse.fail(e.getCodeEnum());
    }

    /**
     * 自定义通用异常捕获
     */
    @ExceptionHandler(value = BaseException.class)
    public DataResponse<?> handleBaseException(BaseException e) {
        return DataResponse.fail(e.getCodeEnum());
    }

    /**
     * Controller 上一层异常处理 servletException ==> Controller ==> BaseException ==> Service
     */
    @ExceptionHandler({NoHandlerFoundException.class, HttpRequestMethodNotSupportedException.class,
            HttpMediaTypeNotSupportedException.class, MissingPathVariableException.class,
            MissingServletRequestParameterException.class, TypeMismatchException.class,
            HttpMessageNotReadableException.class, HttpMessageNotWritableException.class,
            HttpMediaTypeNotAcceptableException.class, ServletRequestBindingException.class,
            ConversionNotSupportedException.class, MissingServletRequestPartException.class,
            AsyncRequestTimeoutException.class})
    public DataResponse<?> handleServletException(Exception e) {
        log.error("Servlet exception :", e);
        return DataResponse.fail(CodeEnum.SystemError);
    }

    /**
     * 其他异常
     */
    @ExceptionHandler(value = Exception.class)
    public DataResponse<?> handleException(Exception e) {
        log.error("系统异常:", e);
        return DataResponse.fail(e.getMessage());
    }
}
