package flowcontrol.article.controller;

import flowcontrol.article.controller.assembler.PalletTypeAssembler;
import flowcontrol.article.model.entity.PalletType;
import flowcontrol.article.model.mapper.PalletTypeMapper;
import flowcontrol.article.model.request.palletType.CreatePalletTypeRequest;
import flowcontrol.article.model.request.palletType.UpdatePalletTypeRequest;
import flowcontrol.article.model.response.PalletTypeResponse;
import flowcontrol.article.service.PalletTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/pallettypes")
public class PalletTypeController extends BaseController<PalletTypeResponse, PalletType, CreatePalletTypeRequest, UpdatePalletTypeRequest, PalletTypeMapper>{

    //Assemblers
    private final PalletTypeAssembler palletTypeAssembler;

    //Services
    private final PalletTypeService palletTypeService;



    //Constructor
    @Autowired
    public PalletTypeController(PalletTypeService palletTypeService, PalletTypeAssembler palletTypeAssembler, PalletTypeMapper palletTypeMapper) {
        super(palletTypeService, palletTypeAssembler, palletTypeMapper);
        this.palletTypeAssembler = palletTypeAssembler;
        this.palletTypeService = palletTypeService;
    }



    //region CRUD

    //endregion

    //region Relations

        //none

    //endregion
}
