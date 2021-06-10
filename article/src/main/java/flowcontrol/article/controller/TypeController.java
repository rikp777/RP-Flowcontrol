package flowcontrol.article.controller;


import flowcontrol.article.controller.assembler.GroupAssembler;
import flowcontrol.article.controller.assembler.TypeAssembler;
import flowcontrol.article.exception.ResourceNotFoundException;
import flowcontrol.article.model.entity.SortType;
import flowcontrol.article.model.entity.Type;
import flowcontrol.article.model.response.SortTypeResponse;
import flowcontrol.article.model.response.TypeResponse;
import flowcontrol.article.service.GroupService;
import flowcontrol.article.service.TypeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/types")
public class TypeController extends BaseController<TypeResponse, Type>{

    //Assemblers
    private final TypeAssembler typeAssembler;

    //Services
    private final TypeService typeService;



    @Autowired
    TypeController(TypeService typeService, TypeAssembler typeAssembler){
        super(typeService, typeAssembler);
        this.typeService = typeService;
        this.typeAssembler = typeAssembler;
    }



    //region CRUD

    //endregion
}
