package flowcontrol.article.model.validation;

import flowcontrol.article.model.validation.annotation.ColorExists;
import flowcontrol.article.service.ColorService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ColorExistsValidator implements ConstraintValidator<ColorExists, String> {

    @Autowired
    private ColorService colorService;

    @SneakyThrows
    @Override
    public boolean isValid(
            String value,
            ConstraintValidatorContext context
    ){
        if(value == null ||value.equals("") || value.isBlank() || value.isEmpty()){
            return true;
        }
        if(value.equals("0")) return true;

        return colorService.isAlreadyPresentById(value);
    }
}