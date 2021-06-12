package flowcontrol.article.model.mapper;


import flowcontrol.article.model.entity.*;
import flowcontrol.article.model.request.article.CreateArticleRequest;
import flowcontrol.article.model.request.article.UpdateArticleRequest;
import flowcontrol.article.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@AllArgsConstructor
@Component
public class ArticleMapper extends BaseMapper<Article> {

    private final ArticleRepository articleRepository;
    private final TypeRepository typeRepository;
    private final InsetRepository insetRepository;
    private final CaskRepository caskRepository;
    private final ColorRepository colorRepository;
    private final SortTypeRepository sortTypeRepository;
    private final GroupRepository groupRepository;
    private final PalletTypeRepository palletTypeRepository;


    public Article toEntity(CreateArticleRequest updateArticle){
        Article original = new Article();

        if(updateArticle.getExcelCode() != null){
            original.setExcelCode(updateArticle.getExcelCode());
        }
        if(updateArticle.getInsetGram() != null){
            original.setInsetGram(updateArticle.getInsetGram());
        }
        if(updateArticle.getInsetLimit() != null){
            original.setInsetLimit(updateArticle.getInsetLimit());
        }
        if(updateArticle.getPalletLimit() != null){
            original.setPalletLimit(updateArticle.getPalletLimit());
        }
        if(updateArticle.getOrigin() != null){
            original.setOrigin(updateArticle.getOrigin());
        }
        if(updateArticle.getAdditionalInfo() != null){
            original.setAdditionalInfo(updateArticle.getAdditionalInfo());
        }

        // Relationships
        if(updateArticle.getTypeId() != null){
            Type type = new Type();
            type.setId(toLong(updateArticle.getTypeId()));
            if(type.getId() == 0){
                type = null;
            }else{
                type = typeRepository.findById(type.getId()).get();
            }
            original.setType(type);
        }
        if(updateArticle.getInsetId() != null){
            Inset inset = new Inset();
            inset.setId(toLong(updateArticle.getInsetId()));
            if(inset.getId() == 0) {
                inset = null;
            }else{
                inset = insetRepository.findById(inset.getId()).get();
            }
            original.setInset(inset);
        }
        if(updateArticle.getSortTypeId() != null){
            SortType sortType = new SortType();
            sortType.setId(toLong(updateArticle.getSortTypeId()));
            if(sortType.getId() == 0){
                sortType = null;
            }else{
                sortType = sortTypeRepository.findById(sortType.getId()).get();
            }
            original.setSortType(sortType);
        }
        if(updateArticle.getGroupId() != null){
            Group group = new Group();
            group.setId(toLong(updateArticle.getGroupId()));
            if(group.getId() == 0){
                group = null;
            }else{
                group = groupRepository.findById(group.getId()).get();
            }
            original.setGroup(group);
        }
        if(updateArticle.getPalletTypeId() != null){
            PalletType palletType = new PalletType();
            palletType.setId(toLong(updateArticle.getPalletTypeId()));
            if(palletType.getId() == 0){
                palletType = null;
            }else{
                palletType = palletTypeRepository.findById(palletType.getId()).get();
            }
            original.setPalletType(palletType);
        }
        if(updateArticle.getColorId() != null){
            Color color = new Color();
            color.setId(toLong(updateArticle.getColorId()));
            if(color.getId() == 0){
                color = null;
            }else{
                color = colorRepository.findById(color.getId()).get();
            }
            original.setColor(color);
        }
        if(updateArticle.getCaskId() != null){
            Cask cask = new Cask();
            cask.setId(toLong(updateArticle.getCaskId()));
            if(cask.getId() == 0){
                cask = null;
            }else{
                cask = caskRepository.findById(cask.getId()).get();
            }
            original.setCask(cask);
        }
        return original;
    }

    public Article mapUpdatesToOriginal(UpdateArticleRequest updateArticle, Article original){
        Article article = new Article();
        article.setId(original.getId());

        if(Objects.nonNull(updateArticle.getExcelCode())){
            original.setExcelCode(updateArticle.getExcelCode());
        }
        if(Objects.nonNull(updateArticle.getInsetGram())){
            original.setInsetGram(updateArticle.getInsetGram());
        }
        if(Objects.nonNull(updateArticle.getInsetLimit())){
            original.setInsetLimit(updateArticle.getInsetLimit());
        }
        if(Objects.nonNull(updateArticle.getPalletLimit())){
            original.setPalletLimit(updateArticle.getPalletLimit());
        }
        if(Objects.nonNull(updateArticle.getOrigin())){
            original.setOrigin(updateArticle.getOrigin());
        }
        if(Objects.nonNull(updateArticle.getAdditionalInfo())){
            original.setAdditionalInfo(updateArticle.getAdditionalInfo());
        }

        // Relationships
        if(Objects.nonNull(updateArticle.getTypeId())){
            Type type = new Type();
            type.setId(toLong(updateArticle.getTypeId()));
            if(type.getId() == 0){
                type = null;
            }else{
                type = typeRepository.findById(type.getId()).get();
            }
            original.setType(type);
        }
        if(updateArticle.getInsetId() != null){
            Inset inset = new Inset();
            inset.setId(toLong(updateArticle.getInsetId()));
            if(inset.getId() == 0) {
                inset = null;
            }else{
                inset = insetRepository.findById(inset.getId()).get();
            }
            original.setInset(inset);
        }
        if(updateArticle.getSortTypeId() != null){
            SortType sortType = new SortType();
            sortType.setId(toLong(updateArticle.getSortTypeId()));
            if(sortType.getId() == 0){
                sortType = null;
            }else{
                sortType = sortTypeRepository.findById(sortType.getId()).get();
            }
            original.setSortType(sortType);
        }
        if(updateArticle.getGroupId() != null){
            Group group = new Group();
            group.setId(toLong(updateArticle.getGroupId()));
            if(group.getId() == 0){
                group = null;
            }else{
                group = groupRepository.findById(group.getId()).get();
            }
            original.setGroup(group);
        }
        if(updateArticle.getPalletTypeId() != null){
            PalletType palletType = new PalletType();
            palletType.setId(toLong(updateArticle.getPalletTypeId()));
            if(palletType.getId() == 0){
                palletType = null;
            }else{
                palletType = palletTypeRepository.findById(palletType.getId()).get();
            }
            original.setPalletType(palletType);
        }
        if(updateArticle.getColorId() != null){
            Color color = new Color();
            color.setId(toLong(updateArticle.getColorId()));
            if(color.getId() == 0){
                color = null;
            }else{
                color = colorRepository.findById(color.getId()).get();
            }
            original.setColor(color);
        }
        if(updateArticle.getCaskId() != null){
            Cask cask = new Cask();
            cask.setId(toLong(updateArticle.getCaskId()));
            if(cask.getId() == 0){
                cask = null;
            }else{
                cask = caskRepository.findById(cask.getId()).get();
            }
            original.setCask(cask);
        }
        return original;
    }



//    public Article DtoToEntity(UpdateArticleRequest article, String id){
//        Article mappedArticle = new Article();
//        return mappedArticle;
//    }
//    public Article fillEmptyValueWithOriginalData(Article updateValues, Article original){
//        if(updateValues.getExcelCode() != null){
//            original.setExcelCode(updateValues.getExcelCode());
//        }
//        if(updateValues.getInsetGram() != null){
//            original.setInsetGram(updateValues.getInsetGram());
//        }
//        if(updateValues.getInsetLimit() != null){
//            original.setInsetLimit(updateValues.getInsetLimit());
//        }
//        if(updateValues.getPalletLimit() != null){
//            original.setPalletLimit(updateValues.getPalletLimit());
//        }
//        if(updateValues.getOrigin() != null){
//            original.setOrigin(updateValues.getOrigin());
//        }
//        if(updateValues.getAdditionalInfo() != null){
//            original.setAdditionalInfo(updateValues.getAdditionalInfo());
//        }
//        if(updateValues.getType() != null && updateValues.getType().getId() != null){
//            original.setType(updateValues.getType());
//        }
//        if(updateValues.getInset() != null && updateValues.getInset().getId() != null){
//            original.setInset(updateValues.getInset());
//        }
//        if(updateValues.getSortType() != null && updateValues.getSortType().getId() != null){
//            original.setSortType(updateValues.getSortType());
//        }
//        if(updateValues.getGroup() != null && updateValues.getGroup().getId() != null){
//            original.setGroup(updateValues.getGroup());
//        }
//        if(updateValues.getPalletType() != null && updateValues.getPalletType().getId() != null){
//            original.setPalletType(updateValues.getPalletType());
//        }
//        if(updateValues.getColor() != null && updateValues.getColor().getId() != null){
//            original.setColor(updateValues.getColor());
//        }
//        if(updateValues.getColor().getId() == 0){
//            original.setColor(null);
//        }
//        if(updateValues.getCask() != null && updateValues.getCask().getId() != null){
//            original.setCask(updateValues.getCask());
//        }
//
//        return original;
//    }
//
//    public Article toEntity(CreateArticleRequest articleRequest){
//        Type type = new Type();
//        type.setId(toLong(articleRequest.getTypeId()));
//        type = typeRepository.findById(type.getId()).get();
//
//        Inset inset = new Inset();
//        inset.setId(toLong(articleRequest.getInsetId()));
//        inset = insetRepository.findById(inset.getId()).get();
//
//        Cask cask = new Cask();
//        cask.setId(toLong(articleRequest.getCaskId()));
//        cask = caskRepository.findById(cask.getId()).get();
//
//        Color color = new Color();
//        color.setId(toLong(articleRequest.getColorId()));
//        color = colorRepository.findById(color.getId()).get();
//
//        SortType sortType = new SortType();
//        sortType.setId(toLong(articleRequest.getSortTypeId()));
//        sortType = sortTypeRepository.findById(sortType.getId()).get();
//
//        Group group = new Group();
//        group.setId(toLong(articleRequest.getGroupId()));
//        group = groupRepository.findById(group.getId()).get();
//
//        PalletType palletType = new PalletType();
//        palletType.setId(toLong(articleRequest.getPalletTypeId()));
//        palletType = palletTypeRepository.findById(palletType.getId()).get();
//
//
//        Article article = new Article();
//        article.setExcelCode(articleRequest.getExcelCode());
//        article.setInsetGram(articleRequest.getInsetGram());
//        article.setInsetLimit(articleRequest.getInsetLimit());
//        article.setPalletLimit(articleRequest.getPalletLimit());
//        article.setOrigin(articleRequest.getOrigin());
//        article.setBiologic(articleRequest.isBiologic());
//        article.setInactive(articleRequest.isInactive());
//        article.setAdditionalInfo(articleRequest.getAdditionalInfo());
//
//        // Relationships
//        article.setType(type);
//        article.setInset(inset);
//        article.setCask(cask);
//        article.setColor(color);
//        article.setSortType(sortType);
//        article.setGroup(group);
//        article.setPalletType(palletType);
//
//        return article;
//    }
//
//    public Article toEntity(UpdateArticleRequest articleRequest, String articleId){
//        Article article = new Article();
//        article.setId(toLong(articleId));
//
//        if(articleRequest.getExcelCode() != null){
//            article.setExcelCode(articleRequest.getExcelCode());
//        }
//        if(articleRequest.getInsetGram() != null){
//            article.setInsetGram(articleRequest.getInsetGram());
//        }
//        if(articleRequest.getInsetLimit() != null){
//            article.setInsetLimit(articleRequest.getInsetLimit());
//        }
//        if(articleRequest.getPalletLimit() != null){
//            article.setPalletLimit(articleRequest.getPalletLimit());
//        }
//        if(articleRequest.getOrigin() != null){
//            article.setOrigin(articleRequest.getOrigin());
//        }
//        if(articleRequest.getAdditionalInfo() != null){
//            article.setAdditionalInfo(articleRequest.getAdditionalInfo());
//        }
//        article.setBiologic(articleRequest.isBiologic());
//        article.setInactive(articleRequest.isInactive());
//
//        // Relationships
//        if(articleRequest.getTypeId() != null){
//            Type type = new Type();
//            type.setId(toLong(articleRequest.getTypeId()));
//            type = typeRepository.findById(type.getId()).get();
//            article.setType(type);
//        }
//        if(articleRequest.getInsetId() != null){
//            Inset inset = new Inset();
//            inset.setId(toLong(articleRequest.getInsetId()));
//            inset = insetRepository.findById(inset.getId()).get();
//            article.setInset(inset);
//        }
//        if(articleRequest.getSortTypeId() != null){
//            SortType sortType = new SortType();
//            sortType.setId(toLong(articleRequest.getSortTypeId()));
//            sortType = sortTypeRepository.findById(sortType.getId()).get();
//            article.setSortType(sortType);
//        }
//        if(articleRequest.getGroupId() != null){
//            Group group = new Group();
//            group.setId(toLong(articleRequest.getGroupId()));
//            group = groupRepository.findById(group.getId()).get();
//            article.setGroup(group);
//        }
//        if(articleRequest.getPalletTypeId() != null){
//            PalletType palletType = new PalletType();
//            palletType.setId(toLong(articleRequest.getPalletTypeId()));
//            palletType = palletTypeRepository.findById(palletType.getId()).get();
//            article.setPalletType(palletType);
//        }
//        if(articleRequest.getColorId() != null){
//            Color color = new Color();
//            color.setId(toLong(articleRequest.getColorId()));
//            if(color.getId() != 0){
//                color = colorRepository.findById(color.getId()).get();
//            }
//            article.setColor(color);
//        }
//        if(articleRequest.getCaskId() != null){
//            Cask cask = new Cask();
//            cask.setId(toLong(articleRequest.getCaskId()));
//            cask = caskRepository.findById(cask.getId()).get();
//            article.setCask(cask);
//        }
//        return article;
//    }

}
