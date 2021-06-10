package flowcontrol.article.model.validation;

import flowcontrol.article.model.validation.annotation.CaskExists;
import flowcontrol.article.service.CaskService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CaskExistsValidator implements ConstraintValidator<CaskExists, String> {

    @Autowired
    private CaskService caskService;

    @SneakyThrows
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context){
        if(value == null || value == "" || value.isBlank() || value.isEmpty()){
            return true;
        }

        if(value.equals("0")) return true;

        return value != null && caskService.isAlreadyPresentById(value);
    }
}
