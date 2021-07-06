package flowcontrol.farmer.controller;

import flowcontrol.farmer.controller.assembler.CellAssembler;
import flowcontrol.farmer.controller.assembler.FarmerAssembler;
import flowcontrol.farmer.exception.ResourceNotFoundException;
import flowcontrol.farmer.model.entity.Cell;
import flowcontrol.farmer.model.entity.Farmer;
import flowcontrol.farmer.model.entity.FarmerCertificate;
import flowcontrol.farmer.model.mapper.CellMapper;
import flowcontrol.farmer.model.mapper.FarmerMapper;
import flowcontrol.farmer.model.request.cell.CreateCellRequest;
import flowcontrol.farmer.model.request.cell.UpdateCellRequest;
import flowcontrol.farmer.model.request.farmer.CreateFarmerRequest;
import flowcontrol.farmer.model.request.farmer.UpdateFarmerRequest;
import flowcontrol.farmer.model.response.CellResponse;
import flowcontrol.farmer.model.response.FarmerCertificateResponse;
import flowcontrol.farmer.model.response.FarmerResponse;
import flowcontrol.farmer.service.CellService;
import flowcontrol.farmer.service.FarmerService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping("/v1/farmers/{farmerId}/cells")
@SecurityRequirement(name = "bearerAuth")
public class CellController extends BaseController<CellResponse, Cell, CreateCellRequest, UpdateCellRequest, CellMapper>{

    //Assembler
    private final CellAssembler cellAssembler;

    //Service
    private final CellService cellService;


    //Constructor
    @Autowired
    CellController(CellService cellService, CellAssembler cellAssembler, CellMapper cellMapper){
        super(cellService, cellAssembler, cellMapper);
        this.cellService = cellService;
        this.cellAssembler = cellAssembler;
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
    public ResponseEntity<CellResponse> getById(
            @PathVariable UUID farmerId,
            @PathVariable UUID id
    ){
        return cellService.getByIdAndFarmerId(id, farmerId)
                .map(entity -> ResponseEntity.ok(cellAssembler.toModel(entity)))
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
    public CollectionModel<CellResponse> getAll(
            @PathVariable UUID farmerId
    ){
        Iterable<Cell> entities = cellService.getAllByFarmerId(farmerId);
        return cellAssembler.toCollectionModel(entities);
    }

    //endregion

    //region Relations
    //none
    //endregion
}