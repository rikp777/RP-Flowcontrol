package flowcontrol.article.model.validation;

import flowcontrol.article.model.validation.annotation.UniqueExcelCode;
import flowcontrol.article.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueExcelCodeValidator implements ConstraintValidator<UniqueExcelCode, String> {

    @Autowired
    private ArticleService articleService;


    @Override
    public boolean isValid(String value, ConstraintValidatorContext context){
        return value != null && !articleService.isAlreadyPresentByExcelCode(value);
    }
}
