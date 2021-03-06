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

import java.util.UUID;

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
            @PathVariable UUID farmerId,
            @PathVariable UUID palletLabelId,
            @PathVariable UUID ticketId,
            @RequestParam("interruption_reason_id") UUID interruptionReasonId,
            @RequestParam("used_article_amount") Integer usedArticleAmount
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
    @PostMapping("/{interruptionId}/close")
    public ResponseEntity closeInterruption(
            @PathVariable UUID farmerId,
            @PathVariable UUID palletLabelId,
            @PathVariable UUID ticketId,
            @PathVariable UUID interruptionId
    ){
        BasicMetaData metaData = BasicMetaData.builder()
                .farmerId(farmerId)
                .palletLabelId(palletLabelId)
                .build();

        return interruptionService.close(metaData, ticketId, interruptionId)
                .map(interruption -> ResponseEntity.ok(interruption))
                .orElseThrow(() -> new InterruptionException("Couldn't close interruption"));
    }


    /**
     * Find all interruptions that belong to farmer, pallet label and ticket
     * @param farmerId
     * @param palletLabelId
     * @param ticketId
     * @return
     */
    @GetMapping
    public ResponseEntity<CollectionModel<InterruptionResponse>> findAll(
            @PathVariable UUID farmerId,
            @PathVariable UUID palletLabelId,
            @PathVariable UUID ticketId
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
     * Find one interruption by id and that belongs to farmer, pallet label and ticket
     * @param palletLabelId
     * @param ticketId
     * @param interruptionId
     * @return
     */
    @GetMapping("/{interruptionId}")
    public ResponseEntity<EntityModel<InterruptionResponse>> findOne(
            @PathVariable UUID farmerId,
            @PathVariable UUID palletLabelId,
            @PathVariable UUID ticketId,
            @PathVariable UUID interruptionId
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
