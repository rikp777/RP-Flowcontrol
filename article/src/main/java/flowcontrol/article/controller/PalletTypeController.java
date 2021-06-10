package flowcontrol.article.controller;

import flowcontrol.article.controller.assembler.BaseAssembler;
import flowcontrol.article.controller.assembler.PalletTypeAssembler;
import flowcontrol.article.exception.ResourceNotFoundException;
import flowcontrol.article.model.entity.Article;
import flowcontrol.article.model.entity.Inset;
import flowcontrol.article.model.entity.PalletType;
import flowcontrol.article.model.response.ArticleResponse;
import flowcontrol.article.model.response.InsetResponse;
import flowcontrol.article.model.response.PalletTypeResponse;
import flowcontrol.article.service.ArticleService;
import flowcontrol.article.service.BaseService;
import flowcontrol.article.service.PalletTypeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/v1/pallettypes")
public class PalletTypeController extends BaseController<PalletTypeResponse, PalletType>{

    //Assemblers
    private final PalletTypeAssembler palletTypeAssembler;

    //Services
    private final PalletTypeService palletTypeService;



    //Constructor
    @Autowired
    public PalletTypeController(PalletTypeService palletTypeService, PalletTypeAssembler palletTypeAssembler) {
        super(palletTypeService, palletTypeAssembler);
        this.palletTypeAssembler = palletTypeAssembler;
        this.palletTypeService = palletTypeService;
    }



    //region CRUD

    //endregion

    //region Relations

        //none

    //endregion
}
