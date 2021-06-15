package flowcontrol.article.controller;

import flowcontrol.article.controller.assembler.TypeAssembler;
import flowcontrol.article.model.entity.Type;
import flowcontrol.article.model.mapper.TypeMapper;
import flowcontrol.article.model.request.type.CreateTypeRequest;
import flowcontrol.article.model.request.type.UpdateTypeRequest;
import flowcontrol.article.model.response.TypeResponse;
import flowcontrol.article.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/types")
public class TypeController extends BaseController<TypeResponse, Type, CreateTypeRequest, UpdateTypeRequest, TypeMapper>{

    //Assemblers
    private final TypeAssembler typeAssembler;

    //Services
    private final TypeService typeService;



    @Autowired
    TypeController(TypeService typeService, TypeAssembler typeAssembler, TypeMapper typeMapper){
        super(typeService, typeAssembler, typeMapper);
        this.typeService = typeService;
        this.typeAssembler = typeAssembler;
    }

    //region CRUD

    //endregion
}
