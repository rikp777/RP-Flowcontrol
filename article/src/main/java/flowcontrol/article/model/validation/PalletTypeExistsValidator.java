package flowcontrol.article.model.validation;

import flowcontrol.article.model.validation.annotation.PalletTypeExists;
import flowcontrol.article.service.PalletTypeService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PalletTypeExistsValidator implements ConstraintValidator<PalletTypeExists, String> {

    @Autowired
    private PalletTypeService palletTypeService;

    @SneakyThrows
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context){
        return value != null && palletTypeService.isAlreadyPresentById(value);
    }
}
