package flowcontrol.article.model.validation.annotation;

import flowcontrol.article.model.validation.SortTypeExistsValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = SortTypeExistsValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.METHOD })
public @interface SortTypeExists {

    public String message() default "The sort type does not exist";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default{};
}
