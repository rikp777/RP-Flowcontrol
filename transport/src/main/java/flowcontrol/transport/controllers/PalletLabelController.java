package flowcontrol.transport.controllers;

import flowcontrol.transport.config.security.JwtConfig;
import flowcontrol.transport.controllers.assembler.PalletLabelAssembler;
import flowcontrol.transport.exception.PalletLabelException;
import flowcontrol.transport.exception.ResourceNotFoundException;
import flowcontrol.transport.model.entity.PalletLabel;
import flowcontrol.transport.model.general.Article;
import flowcontrol.transport.model.general.Farmer;
import flowcontrol.transport.model.request.CreatePalletLabelRequest;
import flowcontrol.transport.model.response.PalletLabelResponse;
import flowcontrol.transport.repository.impl.ArticleRepository;
import flowcontrol.transport.repository.impl.FarmerRepository;
import flowcontrol.transport.service.PalletLabelService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/farmers/{farmerId}/palletlabels")
@AllArgsConstructor
public class PalletLabelController {

    private final JwtConfig jwtConfig;

    @Autowired
    private final PalletLabelService palletLabelService;

    @Autowired
    private final PalletLabelAssembler palletLabelAssembler;

    @Autowired
    private final ArticleRepository articleRepository;
    @Autowired
    private final FarmerRepository farmerRepository;


    @GetMapping()
    public List<PalletLabel> getAllPalletLabels(
            @PathVariable UUID farmerId
    ){
        return palletLabelService.getAll(farmerId);
    }

    @GetMapping("/{palletLabelId}")
    public ResponseEntity<PalletLabelResponse> getPalletLabel(
            @PathVariable UUID farmerId,
            @PathVariable("palletLabelId") UUID palletLabelId
    ){
        Farmer farmer = farmerRepository.findById(farmerId); //change get it from palletlabel farmer id

        return palletLabelService.getById(farmerId, palletLabelId)
                .map(palletLabel -> ResponseEntity.ok(palletLabelAssembler.toModel(palletLabel)))
                .orElseThrow(() ->
                        new ResourceNotFoundException("PalletLabel", "Id", palletLabelId)
                );
    }


    @GetMapping("/getbygeneral/{genericPalletLabelId}")
    public ResponseEntity<PalletLabelResponse> getPalletLabelByGenericId(
            @PathVariable UUID farmerId,
            @PathVariable("genericPalletLabelId") Long generalPalletLabelId
    ){
        Farmer farmer = farmerRepository.findById(farmerId); //change get it from palletlabel farmer id

        return palletLabelService.getByGeneralId(farmerId, generalPalletLabelId)
                .map(palletLabel -> ResponseEntity.ok(palletLabelAssembler.toModel(palletLabel)))
                .orElseThrow(() ->
                        new ResourceNotFoundException("PalletLabel", "Id", generalPalletLabelId)
                );
    }



    @PostMapping()
    public ResponseEntity createPalletLabel(
            @PathVariable UUID farmerId,
            @RequestBody CreatePalletLabelRequest newPalletLabel
    ){
        return palletLabelService.create(farmerId, newPalletLabel)
                .map(palletLabel -> ResponseEntity.ok(palletLabel))
                .orElseThrow(() ->
                        new PalletLabelException("Couldn't", "Something went wrong during creating ", newPalletLabel)
                );
    }
}
