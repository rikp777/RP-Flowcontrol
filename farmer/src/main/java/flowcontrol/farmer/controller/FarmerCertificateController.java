package flowcontrol.farmer.controller;

import flowcontrol.farmer.controller.assembler.CellAssembler;
import flowcontrol.farmer.controller.assembler.FarmerCertificateAssembler;
import flowcontrol.farmer.exception.ResourceNotFoundException;
import flowcontrol.farmer.model.entity.Cell;
import flowcontrol.farmer.model.entity.Farmer;
import flowcontrol.farmer.model.entity.FarmerCertificate;
import flowcontrol.farmer.model.mapper.CellMapper;
import flowcontrol.farmer.model.mapper.FarmerCertificateMapper;
import flowcontrol.farmer.model.request.cell.CreateCellRequest;
import flowcontrol.farmer.model.request.cell.UpdateCellRequest;
import flowcontrol.farmer.model.request.farmerCertificate.FarmerCertificateRequest;
import flowcontrol.farmer.model.response.CellResponse;
import flowcontrol.farmer.model.response.FarmerCertificateResponse;
import flowcontrol.farmer.service.CellService;
import flowcontrol.farmer.service.FarmerCertificateService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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

@RestController
@RequestMapping("/v1/farmers/{farmerId}/certificates")
@SecurityRequirement(name = "bearerAuth")
public class FarmerCertificateController extends BaseController<FarmerCertificateResponse, FarmerCertificate, FarmerCertificateRequest, FarmerCertificateRequest, FarmerCertificateMapper>{

    //Assembler
    private final FarmerCertificateAssembler farmerCertificateAssembler;

    //Service
    private final FarmerCertificateService farmerCertificateService;

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private PagedResourcesAssembler<FarmerCertificate> pagedResourcesAssembler;

    //Constructor
    @Autowired
    FarmerCertificateController(FarmerCertificateService farmerCertificateService, FarmerCertificateAssembler farmerCertificateAssembler, FarmerCertificateMapper farmerCertificateMapper, PagedResourcesAssembler<FarmerCertificate> pagedResourcesAssembler){
        super(farmerCertificateService, farmerCertificateAssembler, farmerCertificateMapper);
        this.farmerCertificateService = farmerCertificateService;
        this.farmerCertificateAssembler = farmerCertificateAssembler;
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
    public ResponseEntity<FarmerCertificateResponse> getById(
            @PathVariable Long farmerId,
            @PathVariable Long id
    ){
        return farmerCertificateService.getByIdAndFarmerId(id, farmerId)
                .map(entity -> ResponseEntity.ok(farmerCertificateAssembler.toModel(entity)))
                .orElseThrow(() ->
                        new ResourceNotFoundException(getName(), "Id", id)
                );
    }

    @GetMapping(
            consumes = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE
            }
    )//READ ALL
    @PreAuthorize("hasRole('ADMIN') || hasRole('PLANNING') || hasRole('ICT')")
    public CollectionModel<FarmerCertificateResponse> getAll(
            @PathVariable Long farmerId
    ){
        Iterable<FarmerCertificate> entities = farmerCertificateService.getAllByFarmerId(farmerId);
        return farmerCertificateAssembler.toCollectionModel(entities);
    }

    //region CRUD

    //endregion

    //region Relations
    //none
    //endregion
}