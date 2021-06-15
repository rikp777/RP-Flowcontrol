package flowcontrol.article.model.validation.annotation;

import flowcontrol.article.model.validation.InsetExistsValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = InsetExistsValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.METHOD })
public @interface InsetExists {

    public String message() default "The group does not exist";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default{};
}
