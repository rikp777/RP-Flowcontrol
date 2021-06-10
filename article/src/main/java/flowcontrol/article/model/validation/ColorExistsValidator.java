package flowcontrol.article.model.validation;

import flowcontrol.article.model.validation.annotation.ColorExists;
import flowcontrol.article.model.validation.annotation.NullOrNotBlank;
import flowcontrol.article.service.ColorService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraints.Null;

public class ColorExistsValidator implements ConstraintValidator<ColorExists, String> {

    @Autowired
    private ColorService colorService;

    @SneakyThrows
    @Override
    public boolean isValid(
            String value,
            ConstraintValidatorContext context
    ){
        if(value == "NULL" || value == null || value == "" || value.isBlank() || value.isEmpty()){
            return true;
        }
        if(value.equals("0")) return true;

        return value != null && colorService.isAlreadyPresentById(value);
    }
}