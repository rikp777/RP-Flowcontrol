package flowcontrol.article.controller;

import flowcontrol.article.controller.assembler.ArticleAssembler;
import flowcontrol.article.controller.assembler.CaskAssembler;
import flowcontrol.article.controller.assembler.ColorAssembler;
import flowcontrol.article.controller.assembler.InsetAssembler;
import flowcontrol.article.exception.ResourceNotFoundException;
import flowcontrol.article.model.entity.Color;
import flowcontrol.article.model.mapper.ColorMapper;
import flowcontrol.article.model.request.color.CreateColorRequest;
import flowcontrol.article.model.request.color.UpdateColorRequest;
import flowcontrol.article.model.response.ArticleResponse;
import flowcontrol.article.model.response.ColorResponse;
import flowcontrol.article.model.response.InsetResponse;
import flowcontrol.article.service.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/colors")
public class ColorController extends BaseController<ColorResponse, Color, CreateColorRequest, UpdateColorRequest, ColorMapper>{

    //Assemblers
    private final ColorAssembler colorAssembler;
    private final ArticleAssembler articleAssembler;
    private final InsetAssembler insetAssembler;
    private final CaskAssembler caskAssembler;

    //Services
    private final ColorService colorService;



    //Constructor
    @Autowired
    ColorController(ColorService colorService, ColorAssembler colorAssembler, ColorMapper colorMapper, ArticleAssembler articleAssembler, InsetAssembler insetAssembler, CaskAssembler caskAssembler){
        super(colorService, colorAssembler, colorMapper);
        this.colorService = colorService;
        this.colorAssembler = colorAssembler;
        this.articleAssembler = articleAssembler;
        this.insetAssembler = insetAssembler;
        this.caskAssembler = caskAssembler;
    }



    //region CRUD

    //endregion



    //region Relations
    @GetMapping("/{colorId}/articles")//READ ALL
    public ResponseEntity<CollectionModel<ArticleResponse>> getAllBelongingArticles(@PathVariable Long colorId){
        return colorService.getById(colorId)
                .map(color -> ResponseEntity.ok(articleAssembler.toCollectionModel(color.getArticles())))
                .orElseThrow(() ->
                        new ResourceNotFoundException(Color.class.getName(), "Id", colorId)
                );
    }

    @GetMapping("/{colorId}/insets")
    public ResponseEntity<CollectionModel<InsetResponse>> getAllBelongingInsets(@PathVariable Long colorId){
        return colorService.getById(colorId)
                .map(color -> ResponseEntity.ok(insetAssembler.toCollectionModel(color.getInsets())))
                .orElseThrow(() ->
                        new ResourceNotFoundException(Color.class.getName(), "Id", colorId)
                );
    }

    @GetMapping("/{colorId}/casks")
    public ResponseEntity getAllBelongingCasks(@PathVariable Long colorId){
        return colorService.getById(colorId)
                .map(color -> ResponseEntity.ok(caskAssembler.toCollectionModel(color.getCasks())))
                .orElseThrow(() ->
                        new ResourceNotFoundException(Color.class.getName(), "Id", colorId)
                );
    }
    //endregion
}

