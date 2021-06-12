package flowcontrol.article.controller;

import com.netflix.discovery.converters.Auto;
import flowcontrol.article.controller.assembler.GroupAssembler;
import flowcontrol.article.controller.assembler.InsetAssembler;
import flowcontrol.article.exception.ResourceNotFoundException;
import flowcontrol.article.model.entity.Group;
import flowcontrol.article.model.entity.Inset;
import flowcontrol.article.model.response.GroupResponse;
import flowcontrol.article.model.response.InsetResponse;
import flowcontrol.article.service.GroupService;
import flowcontrol.article.service.InsetService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/insets")
public class InsetController extends BaseController<InsetResponse, Inset>{

    //Assembler
    private final InsetAssembler insetAssembler;

    //Service
    private final InsetService insetService;



    //Constructor
    @Autowired
    InsetController(InsetService insetService, InsetAssembler insetAssembler){
        super(insetService, insetAssembler);
        this.insetService = insetService;
        this.insetAssembler = insetAssembler;
    }



    //region CRUD

    //endregion

    //region Relations
        //none
    //endregion
}