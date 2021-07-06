package flowcontrol.production.controllers.assembler;


import flowcontrol.production.controllers.palletLabel.InterruptionController;
import flowcontrol.production.controllers.palletLabel.TicketController;
import flowcontrol.production.model.entity.Interruption;
import flowcontrol.production.model.entity.Ticket;
import flowcontrol.production.model.general.PalletLabel;
import flowcontrol.production.model.response.InterruptionResponse;
import flowcontrol.production.repository.impl.PalletLabelRepository;
import flowcontrol.production.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Component
public class InterruptionAssembler implements RepresentationModelAssembler<Interruption, InterruptionResponse> {

    @Autowired
    TicketService ticketService;

    @Autowired
    PalletLabelRepository palletLabelRepository;

    @Override
    public InterruptionResponse toModel(Interruption interruption) {
        Ticket ticket = interruption.getTicket();
        PalletLabel palletLabel = palletLabelRepository.findById(UUID.randomUUID(), ticket.getPalletLabelId()).get(); //todo yet seeder uuid
        UUID farmerId = palletLabel.getFarmer().getId();
        UUID palletLabelId = ticket.getPalletLabelId();
        UUID ticketId = ticket.getId();
        UUID interruptionId = interruption.getId();

        InterruptionResponse interruptionResponse = InterruptionResponse.builder()
                .id(interruption.getId())
                .startAt(interruption.getStartAt())
                .endAt(interruption.getEndAt())
                .ticket(interruption.getTicket())
                .interruptionReason(interruption.getInterruptionReason())
                .build();

        interruptionResponse
                .add(linkTo(methodOn(InterruptionController.class)
                        .findOne(farmerId, palletLabelId, ticketId, interruptionId))
                        .withSelfRel());
        interruptionResponse
                .add(linkTo(methodOn(InterruptionController.class)
                        .findAll(farmerId, palletLabelId, ticketId))
                        .withRel("interruptions"));

        interruptionResponse
                .add(linkTo(methodOn(TicketController.class)
                        .findOne(farmerId, palletLabelId, ticketId))
                        .withRel("ticket"));

        return interruptionResponse;
    }

    @Override
    public CollectionModel<InterruptionResponse> toCollectionModel(Iterable<? extends Interruption> entities) {
        return RepresentationModelAssembler.super.toCollectionModel(entities);
    }
}
