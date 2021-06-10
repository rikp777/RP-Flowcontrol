package flowcontrol.article.service;

import flowcontrol.article.model.entity.Article;
import flowcontrol.article.model.mapper.ArticleMapper;
import flowcontrol.article.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class ArticleService extends BaseService<Article> {

    @Autowired
    ArticleMapper articleMapper;

    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        super(articleRepository);
        this.articleRepository = articleRepository;
    }

    @Async
    @Override
    public Optional<Article> createOrUpdate(Article article){
        return Optional.of(articleRepository.save(article));
        //https://stackoverflow.com/questions/41125384/copy-non-null-properties-from-one-object-to-another-using-beanutils-or-similar
//        Optional<Article> existingArticle = articleRepository.findByExcelCode(article.getExcelCode());
//
//        if(existingArticle.isPresent()){
//            Article existing = articleRepository.findById(article.getId()).get();
//            copyNonNullProperties(article, existing);
//            articleRepository.save(existing);
//
//            return existing;
//        }else{
//            Article existing = articleRepository.findById(article.getId()).get();
//            copyNonNullProperties(article, existing);
//            articleRepository.save(existing);
//
//            return existing;
//        }
//        Article original = articleRepository.findById(articleUpdateData.getId()).get();
//        original = articleMapper.fillEmptyValueWithOriginalData(articleUpdateData, original);
////        copyNonNullProperties(article, existing);

//        articleRepository.save(original);
//
//        return original;
    }
//    public static void copyNonNullProperties(Object src, Object target) {
//        BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
//    }
//
//    public static String[] getNullPropertyNames (Object source) {
//        final BeanWrapper src = new BeanWrapperImpl(source);
//        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();
//
//        Set<String> emptyNames = new HashSet<String>();
//        for(java.beans.PropertyDescriptor pd : pds) {
//            Object srcValue = src.getPropertyValue(pd.getName());
//            if (srcValue == null) emptyNames.add(pd.getName());
//        }
//        String[] result = new String[emptyNames.size()];
//        return emptyNames.toArray(result);
//    }

    @Transactional
    public boolean isAlreadyPresentByExcelCode(String excelCode){
        return articleRepository.findByExcelCode(excelCode).isPresent();
    }

    @Async
    public Optional<Article> getById(String id){
        return articleRepository.findById(Long.parseLong(id));
    }
}
