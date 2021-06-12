package flowcontrol.article.model.validation;

import flowcontrol.article.model.validation.annotation.GroupExists;
import flowcontrol.article.service.GroupService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class GroupExistsValidator implements ConstraintValidator<GroupExists, String> {

    @Autowired
    private GroupService groupService;

    @SneakyThrows
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context){
        if(value == null || value == "" || value.isBlank() || value.isEmpty()){
            return true;
        }

        if(value.equals("0")) return true;

        return value != null && groupService.isAlreadyPresentById(value);
    }
}