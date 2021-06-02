package flowcontrol.article.model.validation.annotation;


import flowcontrol.article.model.validation.UniqueExcelCodeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniqueExcelCodeValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.METHOD })
public @interface UniqueExcelCode {

    public String message() default "There is already an article with this excel code!";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default{};
}
