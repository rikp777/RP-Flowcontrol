package flowcontrol.transport.controllers;

import flowcontrol.transport.exception.PalletLabelException;
import flowcontrol.transport.exception.ResourceNotFoundException;
import flowcontrol.transport.model.entity.PalletLabel;
import flowcontrol.transport.model.request.CreatePalletLabelRequest;
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

    @Autowired
    private final PalletLabelService palletLabelService;


    @GetMapping()
    public List<PalletLabel> getAllPalletLabels(
            @PathVariable Long farmerId
    ){
        return palletLabelService.getAll(farmerId);
    }

    @GetMapping("/{palletLabelId}")
    public ResponseEntity<PalletLabel> getPalletLabel(
            @PathVariable Long farmerId,
            @PathVariable("palletLabelId") Long palletLabelId
    ){
        return palletLabelService.getById(farmerId, palletLabelId)
                .map(palletLabel -> ResponseEntity.ok(palletLabel))
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
