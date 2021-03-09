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
@RequestMapping("/v1/palletlabels")
@AllArgsConstructor
public class PalletLabelController {

    @Autowired
    private final PalletLabelService palletLabelService;


    @GetMapping()
    public List<PalletLabel> getAllPalletLabels(){
        return palletLabelService.getAll();
    }

    @GetMapping("/{palletLabelId}")
    public ResponseEntity<PalletLabel> getPalletLabel(@PathVariable("palletLabelId") Long palletLabelId){
        return palletLabelService.getById(palletLabelId)
                .map(palletLabel -> ResponseEntity.ok(palletLabel))
                .orElseThrow(() ->
                        new ResourceNotFoundException("PalletLabel", "Id", palletLabelId)
                );
    }

    @PostMapping(
//            consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}
    )
    public ResponseEntity createPalletLabel(@RequestBody CreatePalletLabelRequest newPalletLabel){
        return palletLabelService.create(newPalletLabel)
                .map(palletLabel -> ResponseEntity.ok(palletLabel))
                .orElseThrow(() ->
                        new PalletLabelException("Couldn't", "Something went wrong during creating ", newPalletLabel)
                );
    }
}
