package flowcontrol.transport.controllers;

import flowcontrol.transport.model.entity.PalletLabel;
import flowcontrol.transport.service.PalletLabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/palletlabels")
public class PalletLabelController {

    @Autowired
    private final PalletLabelService palletLabelService;

    public PalletLabelController(PalletLabelService palletLabelService) {
        this.palletLabelService = palletLabelService;
    }

    @GetMapping()
    public List<PalletLabel> getAllPalletLabels(){
        return palletLabelService.getAll();
    }

    @GetMapping("{palletLabelId}")
    public PalletLabel getPalletLabel(@PathVariable("palletLabelId") String palletLabelId){
        return palletLabelService.getById(palletLabelId);
    }
}
