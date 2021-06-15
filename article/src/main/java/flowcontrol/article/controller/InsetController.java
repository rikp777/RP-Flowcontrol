package flowcontrol.article.controller;

import flowcontrol.article.controller.assembler.InsetAssembler;
import flowcontrol.article.model.entity.Inset;
import flowcontrol.article.model.mapper.InsetMapper;
import flowcontrol.article.model.request.inset.CreateInsetRequest;
import flowcontrol.article.model.request.inset.UpdateInsetRequest;
import flowcontrol.article.model.response.InsetResponse;
import flowcontrol.article.service.InsetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/insets")
public class InsetController extends BaseController<InsetResponse, Inset, CreateInsetRequest, UpdateInsetRequest, InsetMapper>{

    //Assembler
    private final InsetAssembler insetAssembler;

    //Service
    private final InsetService insetService;



    //Constructor
    @Autowired
    InsetController(InsetService insetService, InsetAssembler insetAssembler, InsetMapper insetMapper){
        super(insetService, insetAssembler, insetMapper);
        this.insetService = insetService;
        this.insetAssembler = insetAssembler;
    }



    //region CRUD

    //endregion

    //region Relations
        //none
    //endregion
}
