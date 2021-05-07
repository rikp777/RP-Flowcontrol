package flowcontrol.transport.controllers;

import flowcontrol.transport.config.security.JwtConfig;
import flowcontrol.transport.exception.PalletLabelException;
import flowcontrol.transport.exception.ResourceNotFoundException;
import flowcontrol.transport.model.entity.PalletLabel;
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

@RestController
@RequestMapping("/v1/farmers/{farmerId}/palletlabels")
@AllArgsConstructor
public class PalletLabelController {

    private final JwtConfig jwtConfig;

    @Autowired
    private final PalletLabelService palletLabelService;

    @Autowired
    private final ArticleRepository articleRepository;
    @Autowired
    private final FarmerRepository farmerRepository;


    @GetMapping()
    public List<PalletLabel> getAllPalletLabels(
            @PathVariable Long farmerId
    ){
        return palletLabelService.getAll(farmerId);
    }

    @GetMapping("/{palletLabelId}")
    public ResponseEntity<PalletLabelResponse> getPalletLabel(
            @PathVariable Long farmerId,
            @PathVariable("palletLabelId") Long palletLabelId
    ){
        Farmer farmer = farmerRepository.findById(farmerId); //change get it from palletlabel farmer id

        return palletLabelService.getById(farmerId, palletLabelId)
                .map(palletLabel -> ResponseEntity.ok(
                        new PalletLabelResponse().builder()
                                .id(palletLabel.getId())
                                .generalId(palletLabel.getGeneralId())
                                .article(articleRepository.findById(palletLabel.getArticle()))
                                .articleAmount(palletLabel.getArticleAmount())
                                .cropDate(palletLabel.getCropDate())
                                .harvestCycle(palletLabel.getHarvestCycle())
                                .harvestCycleDay(palletLabel.getHarvestCycleDay())
                                .farmer(farmer)
                                .build()
                ))
                .orElseThrow(() ->
                        new ResourceNotFoundException("PalletLabel", "Id", palletLabelId)
                );
    }

    @PostMapping()
    public ResponseEntity createPalletLabel(
            @PathVariable Long farmerId,
            @RequestBody CreatePalletLabelRequest newPalletLabel
    ){
        return palletLabelService.create(farmerId, newPalletLabel)
                .map(palletLabel -> ResponseEntity.ok(palletLabel))
                .orElseThrow(() ->
                        new PalletLabelException("Couldn't", "Something went wrong during creating ", newPalletLabel)
                );
    }
}
