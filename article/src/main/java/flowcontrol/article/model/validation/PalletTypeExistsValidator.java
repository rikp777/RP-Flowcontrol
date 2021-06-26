package flowcontrol.article.model.validation;

import flowcontrol.article.model.validation.annotation.PalletTypeExists;
import flowcontrol.article.service.PalletTypeService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.UUID;

public class PalletTypeExistsValidator implements ConstraintValidator<PalletTypeExists, UUID> {

    @Autowired
    private PalletTypeService palletTypeService;

    @SneakyThrows
    @Override
    public boolean isValid(UUID value, ConstraintValidatorContext context){
        if(value == null || value.equals("")){
            return true;
        }

        if(value.equals("0")) return true;

        return palletTypeService.isAlreadyPresentById(value);
    }
}
