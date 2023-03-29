package cn.gyw.backend.infrastructure.aop;

import cn.gyw.individual.commons.log.ApiLog;
import cn.gyw.individual.commons.utils.JwtTokenUtil;
import cn.gyw.individual.commons.utils.SequenceUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

/**
 * API 接口相关配置
 * 默认情况下 Spring Boot
 * 是不支持记录请求体和响应体的，因为请求体和响应体都是以流的方式对外提供调用，
 * 如果在Filter中把请求体和响应体读完了，就会使后续的应用读不到流数据导致异常
 */
@Slf4j
@Configuration
public class ApiConfig {
    @Bean
    public OncePerRequestFilter contentCachingRequestFilter() {
        return new OncePerRequestFilter() {
            protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response,
                                            final FilterChain filterChain) throws ServletException, IOException {
                // 包装HttpServletRequest，把输入流缓存下来
                ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper(request);
                // 包装HttpServletResponse，把输出流缓存下来
                ContentCachingResponseWrapper wrappedResponse = new ContentCachingResponseWrapper(response);
                filterChain.doFilter(wrappedRequest, wrappedResponse);
                ApiLog apiLog = new ApiLog();
                apiLog.setSequenceNum(SequenceUtil.getPipelineNumbers());
                handleRequest(wrappedRequest, apiLog);
                handleResponse(wrappedResponse, apiLog);
                MDC.put("uuid", UUID.randomUUID().toString());
                MDC.put("apiCode", "common");
                log.info("{}", apiLog);
                // 注意这一行代码一定要调用，不然无法返回响应体
                wrappedResponse.copyBodyToResponse();
            }

            private void handleRequest(ContentCachingRequestWrapper request, ApiLog apiLog) {
                String token = request.getHeader("token");
                if (StringUtils.isNotBlank(token)) {
                    String userId = JwtTokenUtil.getUserId(token);
                    apiLog.setLoginAccount(userId);
                }
                apiLog.setActionUrl(request.getRequestURI());
                apiLog.setDesc(request.getRequestURL().toString());
                apiLog.setMethod(request.getMethod());
                apiLog.setGmtCreate(new Date());
                apiLog.setQueryParam(request.getQueryString());
                apiLog.setModule(request.getContextPath());
                apiLog.setHost(request.getServerName());
                apiLog.setPort(String.valueOf(request.getServerPort()));
                apiLog.setReqData(new String(request.getContentAsByteArray()));
            }

            private void handleResponse(ContentCachingResponseWrapper response, ApiLog apiLog) {
                apiLog.setRespData(new String(response.getContentAsByteArray()));
            }
        };

    }
}
