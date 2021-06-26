package flowcontrol.article.model.validation;

import flowcontrol.article.model.validation.annotation.SortTypeExists;
import flowcontrol.article.service.SortTypeService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.UUID;

public class SortTypeExistsValidator implements ConstraintValidator<SortTypeExists, UUID> {

    @Autowired
    private SortTypeService sortTypeService;

    @SneakyThrows
    @Override
    public boolean isValid(UUID value, ConstraintValidatorContext context){
        if(value == null || value.equals("") ){
            return true;
        }

        if(value.equals("0")) return true;

        return sortTypeService.isAlreadyPresentById(value);
    }
}