package flowcontrol.article.model.validation.annotation;

import flowcontrol.article.model.validation.CaskExistsValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CaskExistsValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.METHOD })
public @interface CaskExists {

    public String message() default "The cask does not exist";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default{};
}
