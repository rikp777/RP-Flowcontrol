package flowcontrol.article.model.validation;

import flowcontrol.article.model.validation.annotation.ColorExists;
import flowcontrol.article.service.ColorService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.UUID;

public class ColorExistsValidator implements ConstraintValidator<ColorExists, UUID> {

    @Autowired
    private ColorService colorService;

    @SneakyThrows
    @Override
    public boolean isValid(
            UUID value,
            ConstraintValidatorContext context
    ){
        if(value == null ||value.equals("")){
            return true;
        }
        if(value.equals("0")) return true;

        return colorService.isAlreadyPresentById(value);
    }
}