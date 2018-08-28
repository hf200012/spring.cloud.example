package spring.cloud.exapmle.zuul.config;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Zuul本身就集成了Hystrix，实际上Zuul的路由转发也是用到了Ribbon+Hystrix，
 * 也就意味着我们可以通过Hystrix Dashboard监控Zuul的工作情况
 * 通过这个类启用Hystrix Dashboard
 */
@Configuration
public class HystrixConfiguration {

    @Bean(name = "hystrixRegistrationBean")
    public ServletRegistrationBean servletRegistrationBean() {
        ServletRegistrationBean registration = new ServletRegistrationBean(
                new HystrixMetricsStreamServlet(), "/hystrix.stream");
        registration.setName("hystrixServlet");
        registration.setLoadOnStartup(1);
        return registration;
    }

    @Bean(name = "hystrixForTurbineRegistrationBean")
    public ServletRegistrationBean servletTurbineRegistrationBean() {
        ServletRegistrationBean registration = new ServletRegistrationBean(
                new HystrixMetricsStreamServlet(), "/actuator/hystrix.stream");
        registration.setName("hystrixForTurbineServlet");
        registration.setLoadOnStartup(1);
        return registration;
    }
}
