package flowcontrol.article.model.validation;

import flowcontrol.article.model.validation.annotation.TypeExists;
import flowcontrol.article.service.TypeService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.UUID;

public class TypeExistsValidator implements ConstraintValidator<TypeExists, UUID> {

    @Autowired
    private TypeService typeService;


    @SneakyThrows
    @Override
    public boolean isValid(UUID value, ConstraintValidatorContext context){
        if(value == null || value.equals("")){
            return true;
        }
        if(value.equals("0")) return true;

        return typeService.isAlreadyPresentById(value);
    }
}
