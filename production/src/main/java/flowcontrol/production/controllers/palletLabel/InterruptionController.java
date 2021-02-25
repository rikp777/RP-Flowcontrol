package flowcontrol.production.controllers.palletLabel;

import flowcontrol.production.exception.InterruptionException;
import flowcontrol.production.model.entity.Interruption;
import flowcontrol.production.model.entity.Ticket;
import flowcontrol.production.service.InterruptionService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/palletlabels/{palletLabelId}/tickets/{ticketId}/interruptions")
@AllArgsConstructor
public class InterruptionController {

    @Autowired
    private final InterruptionService interruptionService;


    /**
     * Create interruption for existing ticket
     * @param palletLabelId
     * @param ticketId
     * @param interruptionReasonId
     * @param usedArticleAmount
     * @return
     */
    @PostMapping()
    public ResponseEntity createInterruption(
            @PathVariable Long palletLabelId,
            @PathVariable Long ticketId,
            @RequestParam Long interruptionReasonId,
            @RequestParam Integer usedArticleAmount
    ){
        return interruptionService.create(ticketId, interruptionReasonId, usedArticleAmount)
                .map(interruption -> ResponseEntity.ok(interruption))
                .orElseThrow(() -> new InterruptionException("Couldn't create interruption"));

    }


    /**
     * Close existing interruption
     * @param palletLabelId
     * @param ticketId
     * @param interruptionId
     * @return
     */
    @PostMapping("{interruptionId}")
    public ResponseEntity closeInterruption(
            @PathVariable String palletLabelId,
            @PathVariable String ticketId,
            @PathVariable Long interruptionId
    ){
        return interruptionService.close(interruptionId)
                .map(interruption -> ResponseEntity.ok(interruption))
                .orElseThrow(() -> new InterruptionException("Couldn't close interruption"));
    }


    /**
     * Get all interruptions by ticket id
     * @param palletLabelId
     * @param ticketId
     * @return
     */
    @GetMapping()
    public List<Interruption> getAllInterruptions(
            @PathVariable String palletLabelId,
            @PathVariable String ticketId
    ){
        return interruptionService.getAll();
    }


    /**
     * Get interruption by interruption id and ticket id
     * @param palletLabelId
     * @param ticketId
     * @param interruptionId
     * @return
     */
    @GetMapping("{interruptionId}")
    public ResponseEntity getInterruption(
            @PathVariable String palletLabelId,
            @PathVariable String ticketId,
            @PathVariable("interruptionId") Long interruptionId
    ){
        return interruptionService.getById(interruptionId)
                .map(interruption -> ResponseEntity.ok(interruption))
                .orElseThrow(() -> new InterruptionException("Couldn't get interruption"));
    }
}
