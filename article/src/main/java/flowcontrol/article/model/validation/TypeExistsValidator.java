package flowcontrol.article.model.validation;

import flowcontrol.article.model.validation.annotation.TypeExists;
import flowcontrol.article.service.TypeService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TypeExistsValidator implements ConstraintValidator<TypeExists, String> {

    @Autowired
    private TypeService typeService;


    @SneakyThrows
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context){
        if(value == null || value.equals("") || value.isBlank() || value.isEmpty()){
            return true;
        }
        if(value.equals("0")) return true;

        return typeService.isAlreadyPresentById(value);
    }
}
