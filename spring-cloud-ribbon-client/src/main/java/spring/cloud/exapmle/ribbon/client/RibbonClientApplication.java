package spring.cloud.exapmle.ribbon.client;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


/**
 * Ribbon是Netflix开源的实现了负载均衡等功能的RPC客户端。
 * 支持HTTP、TCP、UDP协议，且有一定的容错、缓存等机制
 * Spring Cloud基于Ribbon封装了Spring Cloud Ribbon，方便结合Eureka、Consul等服务治理框架使用。
 * Ribbon的主要作用是：从服务器端拿到对应服务列表后以负载均衡的方式访问对应服务
 */
@EnableHystrix
@EnableDiscoveryClient
@SpringBootApplication
public class RibbonClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(RibbonClientApplication.class,args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
