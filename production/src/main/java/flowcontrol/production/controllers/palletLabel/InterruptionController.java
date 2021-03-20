package flowcontrol.production.controllers.palletLabel;

import flowcontrol.production.controllers.assembler.InterruptionAssembler;
import flowcontrol.production.exception.InterruptionException;
import flowcontrol.production.model.meta.BasicMetaData;
import flowcontrol.production.model.response.InterruptionResponse;
import flowcontrol.production.service.InterruptionService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/farmers/{farmerId}/palletlabels/{palletLabelId}/tickets/{ticketId}/interruptions")
@AllArgsConstructor
public class InterruptionController {

    @Autowired
    private final InterruptionService interruptionService;

    @Autowired
    private final InterruptionAssembler interruptionAssembler;

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
            @PathVariable Long farmerId,
            @PathVariable Long palletLabelId,
            @PathVariable Long ticketId,
            @RequestParam Long interruptionReasonId,
            @RequestParam Integer usedArticleAmount
            ){
        BasicMetaData metaData = BasicMetaData.builder()
                .farmerId(farmerId)
                .palletLabelId(palletLabelId)
                .build();

        return interruptionService.create(metaData, ticketId, interruptionReasonId, usedArticleAmount)
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
    @PostMapping("/{interruptionId}")
    public ResponseEntity closeInterruption(
            @PathVariable Long farmerId,
            @PathVariable Long palletLabelId,
            @PathVariable Long ticketId,
            @PathVariable Long interruptionId
    ){
        BasicMetaData metaData = BasicMetaData.builder()
                .farmerId(farmerId)
                .palletLabelId(palletLabelId)
                .build();

        return interruptionService.close(metaData, ticketId, interruptionId)
                .map(interruption -> ResponseEntity.ok(interruption))
                .orElseThrow(() -> new InterruptionException("Couldn't close interruption"));
    }


    @GetMapping
    public ResponseEntity<CollectionModel<InterruptionResponse>> findAll(
            @PathVariable Long farmerId,
            @PathVariable Long palletLabelId,
            @PathVariable Long ticketId
    ){
        BasicMetaData metaData = BasicMetaData.builder()
                .farmerId(farmerId)
                .palletLabelId(palletLabelId)
                .build();

        return ResponseEntity.ok(
                interruptionAssembler.toCollectionModel(
                        interruptionService.findAll(metaData, ticketId)
                )
        );
    }

    /**
     * Get interruption by interruption id and ticket id
     * @param palletLabelId
     * @param ticketId
     * @param interruptionId
     * @return
     */
    @GetMapping("/{interruptionId}")
    public ResponseEntity<EntityModel<InterruptionResponse>> findOne(
            @PathVariable Long farmerId,
            @PathVariable Long palletLabelId,
            @PathVariable Long ticketId,
            @PathVariable Long interruptionId
    ){
        BasicMetaData metaData = BasicMetaData.builder()
                .farmerId(farmerId)
                .palletLabelId(palletLabelId)
                .build();

        return interruptionService.findById(metaData, ticketId, interruptionId)
                .map(interruption -> {
                    InterruptionResponse interruptionResponse = interruptionAssembler.toModel(interruption);
                    EntityModel<InterruptionResponse> interruptionEntityModel = EntityModel.of(interruptionResponse);
                    return ResponseEntity.ok(interruptionEntityModel);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
