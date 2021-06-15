package flowcontrol.article.controller;

import flowcontrol.article.controller.assembler.SortTypeAssembler;
import flowcontrol.article.model.entity.SortType;
import flowcontrol.article.model.mapper.SortTypeMapper;
import flowcontrol.article.model.request.sortType.CreateSortTypeRequest;
import flowcontrol.article.model.request.sortType.UpdateSortTypeRequest;
import flowcontrol.article.model.response.SortTypeResponse;
import flowcontrol.article.service.SortTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/sorttypes")
public class SortTypeController extends BaseController<SortTypeResponse, SortType, CreateSortTypeRequest, UpdateSortTypeRequest, SortTypeMapper>{

    //Assemblers
    private final SortTypeAssembler sortTypeAssembler;

    //Services
    private final SortTypeService sortTypeService;



    //Constructor
    @Autowired
    SortTypeController(SortTypeService sortTypeService, SortTypeAssembler sortTypeAssembler, SortTypeMapper sortTypeMapper){
        super(sortTypeService, sortTypeAssembler, sortTypeMapper);
        this.sortTypeService = sortTypeService;
        this.sortTypeAssembler = sortTypeAssembler;
    }



    //region CRUD

    //endregion
}
