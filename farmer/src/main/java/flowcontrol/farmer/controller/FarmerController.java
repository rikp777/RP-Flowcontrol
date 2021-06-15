package flowcontrol.farmer.controller;

import flowcontrol.farmer.controller.assembler.FarmerAssembler;
import flowcontrol.farmer.exception.ResourceNotFoundException;
import flowcontrol.farmer.model.entity.Farmer;
import flowcontrol.farmer.model.mapper.FarmerMapper;
import flowcontrol.farmer.model.request.farmer.CreateFarmerRequest;
import flowcontrol.farmer.model.request.farmer.UpdateFarmerRequest;
import flowcontrol.farmer.model.response.FarmerCertificateResponse;
import flowcontrol.farmer.model.response.FarmerResponse;
import flowcontrol.farmer.service.FarmerService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import java.util.Optional;

@RestController
@RequestMapping("/v1/farmers")
public class FarmerController extends BaseController<FarmerResponse, Farmer, CreateFarmerRequest, UpdateFarmerRequest, FarmerMapper>{

    //Assembler
    private final FarmerAssembler farmerAssembler;

    //Service
    private final FarmerService farmerService;

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private PagedResourcesAssembler<Farmer> pagedResourcesAssembler;

    //Constructor
    @Autowired
    FarmerController(FarmerService farmerService, FarmerAssembler farmerAssembler, FarmerMapper farmerMapper, PagedResourcesAssembler<Farmer> pagedResourcesAssembler){
        super(farmerService, farmerAssembler, farmerMapper);
        this.farmerService = farmerService;
        this.farmerAssembler = farmerAssembler;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }


    //region CRUD
    @GetMapping(
            path = "/{id}",
            consumes = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE
            }
    ) //READ BY ID
    @PreAuthorize("hasRole('ADMIN') || hasRole('PLANNING') || hasRole('ICT') || hasRole('USER') ")
    public ResponseEntity<FarmerResponse> getById(
            @PathVariable Long id
    ){
        return farmerService.getById(id)
                .map(entity -> ResponseEntity.ok(farmerAssembler.toModel(entity)))
                .orElseThrow(() ->
                        new ResourceNotFoundException(getName(), "Id", id)
                );
    }


    @GetMapping()//READ ALL
    @PreAuthorize("hasRole('ADMIN') || hasRole('PLANNING') || hasRole('ICT')")
    public CollectionModel<FarmerResponse> getAll(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "3") @Max(7) Integer size,
            @RequestParam(required = false) String[] sort,
            @RequestParam(required = false, defaultValue = "asc") String dir
    ){
        PageRequest pageRequest;
        Sort.Direction direction;
        if(sort == null) {
            pageRequest = PageRequest.of(page, size);
        }
        else {
            if(dir.equalsIgnoreCase("asc")) direction = Sort.Direction.ASC;
            else direction = Sort.Direction.DESC;
            pageRequest = PageRequest.of(page, size, Sort.by(direction, sort));
        }

        Page<Farmer> entities = farmerService.getAll(pageRequest);
        if(! CollectionUtils.isEmpty(entities.getContent())) return pagedResourcesAssembler.toModel(entities, farmerAssembler);
        return null;
    }



    //endregion

    //region Relations
    //none
    //endregion
}

