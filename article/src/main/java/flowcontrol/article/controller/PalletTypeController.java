package flowcontrol.article.controller;

import flowcontrol.article.model.entity.Article;
import flowcontrol.article.model.entity.PalletType;
import flowcontrol.article.service.ArticleService;
import flowcontrol.article.service.PalletTypeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/v1/pallettypes")
@AllArgsConstructor
public class PalletTypeController {

    private final PalletTypeService palletTypeService;


    @GetMapping
    public Iterable<PalletType> getAll(){
        return palletTypeService.getAll();
    }

    @GetMapping("/{palletTypeId}")
    public Optional<PalletType> getById(@PathVariable Long palletTypeId){
        return palletTypeService.getById(palletTypeId);
    }
}
