package flowcontrol.article.service;

import flowcontrol.article.model.entity.Article;
import flowcontrol.article.model.entity.PalletType;
import flowcontrol.article.repository.ArticleRepository;
import flowcontrol.article.repository.PalletTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class PalletTypeService {

    private final PalletTypeRepository palletTypeRepository;


    public Iterable<PalletType> getAll(){
        return palletTypeRepository.findAll();
    }

    public Optional<PalletType> getById(Long id){
        return palletTypeRepository.findById(id);
    }
}
