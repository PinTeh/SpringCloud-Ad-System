package cn.imhtb.ad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author PinTeh
 * @date 2019/7/26
 */
@EnableEurekaClient
@EnableZuulProxy
@SpringBootApplication
public class AdGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdGatewayApplication.class,args);
    }

}
