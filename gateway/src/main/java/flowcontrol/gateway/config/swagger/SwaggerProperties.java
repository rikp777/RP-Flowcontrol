package flowcontrol.gateway.config.swagger;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties("swagger")
public class SwaggerProperties {
    private boolean enable = true;
    private String group;
    private String title;
    private String description;
    private String version;
    private String contactName;
    private String contactUrl;
    private String contactEmail;
    private String basePackage;
    private String termsOfServiceUrl;
    private String license;
    private String licenseUrl;
}
