package flowcontrol.production.controllers.assembler;

import flowcontrol.production.controllers.palletLabel.TicketController;
import flowcontrol.production.model.entity.Ticket;
import flowcontrol.production.model.general.PalletLabel;
import flowcontrol.production.model.response.TicketResponse;
import flowcontrol.production.repository.impl.PalletLabelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;





@Component
public class TicketAssembler implements RepresentationModelAssembler<Ticket, TicketResponse> {

    @Autowired
    PalletLabelRepository palletLabelRepository;


    @Override
    public TicketResponse toModel(Ticket ticket) {


        PalletLabel palletLabel = palletLabelRepository.findById(ticket.getFarmerId(), ticket.getPalletLabelId()).get(); //todo change uuid

        TicketResponse ticketResponse = TicketResponse.builder()
                .id(ticket.getId())
                .startAt(ticket.getStartAt())
                .endAt(ticket.getEndAt())
                .articleAmountUsed(ticket.getArticleAmountUsed())
                .refillTrays(ticket.getRefillTrays())
                .interruptions(ticket.getInterruptions())
                .line(ticket.getLine())
                .palletLabel(palletLabel) //change in further
                .farmer(palletLabel.getFarmer())
                .build();

        ticketResponse
                .add(linkTo(methodOn(TicketController.class)
                        .findOne(ticket.getFarmerId(), ticket.getPalletLabelId(), ticket.getId()))
                        .withSelfRel());
        ticketResponse
                .add(linkTo(methodOn(TicketController.class)
                        .findAll(ticket.getFarmerId(), ticket.getPalletLabelId()))
                        .withRel("tickets"));

        return ticketResponse;
    }

    @Override
    public CollectionModel<TicketResponse> toCollectionModel(Iterable<? extends Ticket> entities) {
        return RepresentationModelAssembler.super.toCollectionModel(entities);
    }
}
