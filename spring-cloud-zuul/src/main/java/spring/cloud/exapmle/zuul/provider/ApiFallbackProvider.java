package spring.cloud.exapmle.zuul.provider;


import com.netflix.hystrix.exception.HystrixTimeoutException;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;


/**
 * Zuul作为服务网关为了保证自己不被服务拖垮，本身已经集成了Hystrix对路由转发进行隔离。
 * 为了方便开发人员对服务短路进行自定义处理，
 * Zuul 提供了 ZuulFallbackProvider 接口，开发人员可以通过实现该接口来完成自定义Hystrix Fallback
 *
 * Spring Cloud Zuul 提供了 FallbackProvider替代了ZuulFallbackProvider接口。因此我们实现FallbackProvider即可
 *
 */
//@Component
public class ApiFallbackProvider implements FallbackProvider {

    private Logger logger = Logger.getLogger(ApiFallbackProvider.class.toString());

    /**
     * 该Provider应用的Route ID，例如：testservice，如果设置为 * ，那就对所有路由生效
     * @return
     */
    @Override
    public String getRoute() {
        return "*";
    }

    /**
     * 快速回退失败/响应，即处理异常并返回对应输出/响应内容。route：发生异常的RouteID，
     * cause：触发快速回退/失败的异常/错误
     * @param route
     * @param cause
     * @return
     */
    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        logger.warning(String.format("route:%s,exceptionType:%s,stackTrace:%s", route, cause.getClass().getName(), cause.getStackTrace()));
        String message = "";
        if (cause instanceof HystrixTimeoutException) {
            message = "Timeout";
        } else {
            message = "Service exception";
        }
        return fallbackResponse(message);
    }

    /**
     * Spring提供的HttpResponse接口。可以通过实现该接口自定义Http status、body、header
     * @param message
     * @return
     */
    public ClientHttpResponse fallbackResponse(String message) {

        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.OK;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return 200;
            }

            @Override
            public String getStatusText() throws IOException {
                return "OK";
            }

            @Override
            public void close() {

            }

            @Override
            public InputStream getBody() throws IOException {
                String bodyText = String.format("{\"code\": 999,\"message\": \"Service unavailable:%s\"}", message);
                return new ByteArrayInputStream(bodyText.getBytes());
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                return headers;
            }
        };

    }

}