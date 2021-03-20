//package flowcontrol.production.config;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import com.google.common.base.Predicate;
//
//import org.springframework.context.annotation.Primary;
//import org.springframework.plugin.core.SimplePluginRegistry;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.service.Contact;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import static springfox.documentation.builders.PathSelectors.regex;
//import static com.google.common.base.Predicates.or;
//
//@Configuration
//@EnableSwagger2
////@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
//public class SpringFoxConfig {
//

////// Fix for Hateoas
////    @Primary
////    @Bean
////    public LinkDiscoverers discoverers() {
////        List<LinkDiscoverer> plugins = new ArrayList<>();
////        plugins.add(new CollectionJsonLinkDiscoverer());
////        return new LinkDiscoverers(SimplePluginRegistry.create(plugins));
////    }
//
////    @Bean
////    public LinkDiscoverers discoverers(){
////        return new LinkDiscoverers(SimplePluginRegistry.create(Arrays.asList(new CollectionJsonLinkDiscoverer())));
////    }
//
//    @Bean
//    public Docket postsApi() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .groupName("{ApplicationName}")
//                .apiInfo(buildApiInfo())
//                .select()
//                .apis(RequestHandlerSelectors.any())
//                .paths(PathSelectors.regex("/.*"))
//                .build();
//    }
//
//    private ApiInfo buildApiInfo() {
//        Contact contact = new Contact("CompanyName", "https://company-domain.com", "mail@company.com");
//        return new ApiInfoBuilder()
//                .title("test")
//                .description("API Description")
//                .license("license")
//                .version("1.0")
//                .contact(contact)
//                .licenseUrl("licenseURl")
//                .build();
//    }
//}
