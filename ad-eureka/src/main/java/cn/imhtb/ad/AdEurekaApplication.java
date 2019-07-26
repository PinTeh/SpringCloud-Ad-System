package cn.imhtb.ad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author PinTeh
 * @date 2019/7/26
 */
@EnableEurekaServer
@SpringBootApplication
public class AdEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdEurekaApplication.class,args);
    }

}
