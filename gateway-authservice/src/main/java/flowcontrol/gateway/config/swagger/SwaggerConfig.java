//package flowcontrol.gateway.config.swagger;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.service.Contact;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
////@EnableSwagger2
////@ConditionalOnProperty(prefix = "swagger", value = "enable", matchIfMissing = true)
////@Configuration
////@EnableConfigurationProperties(SwaggerProperties.class)
//public class SwaggerConfig {
//
//    @Autowired
//    SwaggerProperties properties;
//
//    @Bean
//    public Docket api(){
//        return new Docket(DocumentationType.SWAGGER_2)
//                .groupName(properties.getGroup())
//                .apiInfo(apiInfo())
//                .select()
//                .apis(RequestHandlerSelectors.basePackage((properties.getBasePackage())))
//                .paths(PathSelectors.any())
//                .build();
//    }
//
//    private ApiInfo apiInfo(){
//        return new ApiInfoBuilder()
//                .title(properties.getTitle())
//                .description(properties.getTitle())
//                .termsOfServiceUrl(properties.getTermsOfServiceUrl())
//                .contact(
//                        new Contact(
//                                properties.getContactName(),
//                                properties.getContactUrl(),
//                                properties.getContactEmail()
//                        ))
//                .license(properties.getLicenseUrl())
//                .version(properties.getVersion())
//                .build();
//    }
//}
