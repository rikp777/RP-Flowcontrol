package flowcontrol.article.model.validation;

import flowcontrol.article.model.validation.annotation.InsetExists;
import flowcontrol.article.service.InsetService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.UUID;

public class InsetExistsValidator implements ConstraintValidator<InsetExists, UUID> {

    @Autowired
    private InsetService insetService;

    @SneakyThrows
    @Override
    public boolean isValid(UUID value, ConstraintValidatorContext context){
        if(value == null || value.equals("")){
            return true;
        }

        if(value.equals("0")) return true;

        return insetService.isAlreadyPresentById(value);
    }
}
