package spring.cloud.exapmle.feign.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;


/**
 * Feign是基于Ribbon封装的HTTP Client工具包，Feign的目标是简化HTTP Client。Feign也确实做到了这一点，
 * 使用Feign发起HTTP请求只需要定义好接口并且配置好相应的注解即可完成对对应接口的绑定。 *
 * Spring Cloud基于Feign封装了Spring Cloud Feign（OpenFeign），同Ribbon一样，
 * 主要是方便结合Eureka、Consul等服务治理框架使用。Feign的主要作用是：
 * 从服务器端拿到对应服务列表后以负载均衡的方式访问对应服务，比Ribbon使用起来会更加方便。
 */
@EnableHystrixDashboard
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class FeignClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeignClientApplication.class,args);
    }
}
