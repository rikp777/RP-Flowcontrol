package flowcontrol.article.model.validation;

import flowcontrol.article.model.validation.annotation.SortTypeExists;
import flowcontrol.article.service.SortTypeService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SortTypeExistsValidator implements ConstraintValidator<SortTypeExists, String> {

    @Autowired
    private SortTypeService sortTypeService;

    @SneakyThrows
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context){
        return value != null && sortTypeService.isAlreadyPresentById(value);
    }
}