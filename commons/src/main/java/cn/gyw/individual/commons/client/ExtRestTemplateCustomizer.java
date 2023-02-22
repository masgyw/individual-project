package cn.gyw.individual.commons.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

/**
 * @desc With this approach, we can create an application-wide, additive customization
 * @createdTime 2022/2/14 22:50
 */
public class ExtRestTemplateCustomizer implements RestTemplateCustomizer {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExtRestTemplateCustomizer.class);

    @Override
    public void customize(RestTemplate restTemplate) {
        restTemplate.getInterceptors().add(new LogClientHttpRequestInterceptor());
    }

    /**
     * HttpClient日志拦截器
     */
    public static class LogClientHttpRequestInterceptor implements ClientHttpRequestInterceptor {

        @Override
        public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
            logRequestDetails(request);
            return execution.execute(request, body);
        }

        private void logRequestDetails(HttpRequest request) {
            LOGGER.info("Headers: {}", request.getHeaders());
            LOGGER.info("Request Method: {}", request.getMethod());
            LOGGER.info("Request URI: {}", request.getURI());
        }
    }
}
