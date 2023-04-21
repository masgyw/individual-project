package cn.gyw.backend.infrastructure.config;

import cn.gyw.individual.commons.log.ApiLog;
import cn.gyw.individual.commons.utils.SequenceUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @date 2023/3/30
 */
@Slf4j
@Component
public class ApiFilter extends OncePerRequestFilter {

    private static final Set<String> EXCLUDE_SUFFIX = Stream.of(".js", ".css", ".ico", ".vue")
            .collect(Collectors.toSet());

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String reqUrl = request.getRequestURL().toString();
        boolean match = EXCLUDE_SUFFIX.stream().anyMatch(reqUrl::endsWith);
        if (match) {
            filterChain.doFilter(request, response);
            return;
        }

        long startTime = System.currentTimeMillis();

        ContentCachingRequestWrapper wrappedRequest;
        // 包装HttpServletRequest，把输入流缓存下来
        if (request instanceof ContentCachingRequestWrapper) {
            wrappedRequest = (ContentCachingRequestWrapper) request;
        } else {
            wrappedRequest = new ContentCachingRequestWrapper(request);
        }
        // 包装HttpServletResponse，把输出流缓存下来
        ContentCachingResponseWrapper wrappedResponse;
        if (response instanceof ContentCachingResponseWrapper) {
            wrappedResponse = (ContentCachingResponseWrapper) response;
        } else {
            wrappedResponse = new ContentCachingResponseWrapper(response);
        }

        filterChain.doFilter(wrappedRequest, wrappedResponse);

        ApiLog apiLog = new ApiLog();
        apiLog.setSequenceNum(SequenceUtil.getPipelineNumbers());
        handleRequest(wrappedRequest, apiLog);
        handleResponse(wrappedResponse, apiLog);
        log.debug("{},cost:{}ms", apiLog, (System.currentTimeMillis() - startTime));

        // 注意这一行代码一定要调用，不然无法返回响应体
        wrappedResponse.copyBodyToResponse();
    }

    private void handleRequest(ContentCachingRequestWrapper request, ApiLog apiLog) {
        String token = request.getHeader("token");
        if (StringUtils.isNotBlank(token)) {
            // 从token 中获取信息
            apiLog.setLoginAccount(token);
        }
        apiLog.setActionUrl(request.getRequestURI());
        apiLog.setDesc(request.getRequestURL().toString());
        apiLog.setMethod(request.getMethod());
        apiLog.setGmtCreate(new Date());
        apiLog.setQueryParam(request.getQueryString());
        apiLog.setModule(request.getContextPath());
        apiLog.setHost(request.getServerName());
        apiLog.setPort(String.valueOf(request.getServerPort()));
        // apiLog.setReqData(new String(request.getContentAsByteArray()));
    }

    private void handleResponse(ContentCachingResponseWrapper response, ApiLog apiLog) {
        apiLog.setRespCode(String.valueOf(response.getStatusCode()));
        // apiLog.setRespData(new String(response.getContentAsByteArray()));
    }
}
