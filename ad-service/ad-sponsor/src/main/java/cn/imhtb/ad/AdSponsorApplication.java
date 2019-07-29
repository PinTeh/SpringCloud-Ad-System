package cn.imhtb.ad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author PinTeh
 * @date 2019/7/29
 */
@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
public class AdSponsorApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdSponsorApplication.class,args);
    }

}
