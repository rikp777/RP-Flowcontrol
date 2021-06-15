package flowcontrol.article.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.HateoasPageableHandlerMethodArgumentResolver;
import org.springframework.data.web.HateoasSortHandlerMethodArgumentResolver;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.data.web.PagedResourcesAssemblerArgumentResolver;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.messaging.handler.invocation.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

//@Configuration
//@ComponentScan
//public class WebMvcConfig extends WebMvcConfigurationSupport {
////    @Bean
////    public HateoasPageableHandlerMethodArgumentResolver pageableResolver() {
////        return new HateoasPageableHandlerMethodArgumentResolver(sortResolver());
////    }
////
////    @Bean
////    public HateoasSortHandlerMethodArgumentResolver sortResolver() {
////        return new HateoasSortHandlerMethodArgumentResolver();
////    }
////
////    @Bean
////    public PagedResourcesAssembler<?> pagedResourcesAssembler() {
////        return new PagedResourcesAssembler<>(pageableResolver(), null);
////    }
//}
