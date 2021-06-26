package flowcontrol.article.model.validation;

import flowcontrol.article.model.validation.annotation.GroupExists;
import flowcontrol.article.service.GroupService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.UUID;

public class GroupExistsValidator implements ConstraintValidator<GroupExists, UUID> {

    @Autowired
    private GroupService groupService;

    @SneakyThrows
    @Override
    public boolean isValid(UUID value, ConstraintValidatorContext context){
        if(value == null || value.equals("") ){
            return true;
        }

        if(value.equals("0")) return true;

        return groupService.isAlreadyPresentById(value);
    }
}
