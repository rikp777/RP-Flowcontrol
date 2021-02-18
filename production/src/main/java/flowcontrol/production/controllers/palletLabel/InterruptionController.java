package flowcontrol.production.controllers.palletLabel;

import flowcontrol.production.model.entity.Interruption;
import flowcontrol.production.model.entity.Ticket;
import flowcontrol.production.service.InterruptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/palletlabels/{palletLabelId}/tickets/{ticketId}/interruptions")
public class InterruptionController {

    @Autowired
    private final InterruptionService interruptionService;

    public InterruptionController(InterruptionService interruptionService) {
        this.interruptionService = interruptionService;
    }

    @PostMapping()
    public Interruption createInterruption(@PathVariable Long palletLabelId, @PathVariable Long ticketId, @RequestParam Long interruptionReasonId, @RequestParam Integer usedArticleAmount){
        Interruption interruption = interruptionService.create(ticketId, interruptionReasonId, usedArticleAmount);

        return interruption;
    }

    @PostMapping("{interruptionId}")
    public Interruption closeInterruption(@PathVariable String palletLabelId, @PathVariable String ticketId, @PathVariable Long interruptionId){
        Interruption interruption = interruptionService.close(interruptionId);

        return interruption;
    }

    @GetMapping()
    public List<Interruption> getAllInterruptions(@PathVariable String palletLabelId, @PathVariable String ticketId){
        return interruptionService.getAll();
    }

    @GetMapping("{interruptionId}")
    public Interruption getInterruption(@PathVariable String palletLabelId, @PathVariable String ticketId, @PathVariable("interruptionId") Long interruptionId){
        return interruptionService.getById(interruptionId);
    }
}
