package flowcontrol.article.controller;

import flowcontrol.article.controller.assembler.GroupAssembler;
import flowcontrol.article.controller.assembler.SortTypeAssembler;
import flowcontrol.article.exception.ResourceNotFoundException;
import flowcontrol.article.model.entity.PalletType;
import flowcontrol.article.model.entity.SortType;
import flowcontrol.article.model.response.PalletTypeResponse;
import flowcontrol.article.model.response.SortTypeResponse;
import flowcontrol.article.service.GroupService;
import flowcontrol.article.service.SortTypeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/sorttypes")
public class SortTypeController extends BaseController<SortTypeResponse, SortType>{

    //Assemblers
    private final SortTypeAssembler sortTypeAssembler;

    //Services
    private final SortTypeService sortTypeService;



    //Constructor
    @Autowired
    SortTypeController(SortTypeService sortTypeService, SortTypeAssembler sortTypeAssembler){
        super(sortTypeService, sortTypeAssembler);
        this.sortTypeService = sortTypeService;
        this.sortTypeAssembler = sortTypeAssembler;
    }



    //region CRUD

    //endregion
}
