package flowcontrol.production.controllers;

import flowcontrol.production.model.entity.Interruption;
import flowcontrol.production.service.InterruptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/interruptions")
public class InterruptionController {

    @Autowired
    private final InterruptionService interruptionService;

    public InterruptionController(InterruptionService interruptionService) {
        this.interruptionService = interruptionService;
    }

    @GetMapping()
    public List<Interruption> getAllInterruptions(){
        return interruptionService.getAll();
    }

    @GetMapping("{interruptionId}")
    public Interruption getInterruption(@PathVariable("interruptionId") Long interruptionId){
        return interruptionService.getById(interruptionId);
    }
}
