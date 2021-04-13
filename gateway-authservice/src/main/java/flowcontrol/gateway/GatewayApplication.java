package flowcontrol.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
//import org.springframework.cloud.client.loadbalancer.LoadBalanced;
//import org.springframework.cloud.gateway.route.RouteLocator;
//import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
//import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
//import org.springframework.web.reactive.function.client.WebClient;

//@EnableEurekaClient
@SpringBootApplication
@EntityScan(basePackageClasses = {
        GatewayApplication.class,
        Jsr310JpaConverters.class
})
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

//    @Bean
//    public RouteLocator customRouteLocator(RouteLocatorBuilder builder){
//        return builder.routes().build();
//    }
//
//    @Bean
//    @LoadBalanced
//    public WebClient.Builder loadBalancedWebClientBuilder() {
//        return WebClient.builder();
//    }
}
