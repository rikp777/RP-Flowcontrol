package flowcontrol.production.controllers.assembler;

import flowcontrol.production.controllers.palletLabel.TicketController;
import flowcontrol.production.model.entity.Ticket;
import flowcontrol.production.model.response.TicketResponse;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.SimpleRepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


@Component
public class TicketAssembler implements RepresentationModelAssembler<Ticket, TicketResponse> {
    @Override
    public TicketResponse toModel(Ticket ticket) {

        TicketResponse ticketResponse = TicketResponse.builder()
                .id(ticket.getId())
                .startAt(ticket.getStartAt())
                .endAt(ticket.getEndAt())
                .articleAmountUsed(ticket.getArticleAmountUsed())
                .refillTrays(ticket.getRefillTrays())
                .interruptions(ticket.getInterruptions())
                .line(ticket.getLine())
                .palletLabelId(1L) //change in further
                .farmerId(1L) //change in further
                .build();

        ticketResponse
                .add(linkTo(methodOn(TicketController.class).findOne(1L, 1L, ticket.getId())).withSelfRel());
        ticketResponse
                .add(linkTo(methodOn(TicketController.class).findAll(1L, 1L)).withRel(
                "tickets"));

        return ticketResponse;
    }

    @Override
    public CollectionModel<TicketResponse> toCollectionModel(Iterable<? extends Ticket> entities) {
        CollectionModel<TicketResponse> ticketResponse = RepresentationModelAssembler.super.toCollectionModel(entities);

        ticketResponse.add(linkTo(methodOn(TicketController.class).findAll(1L, 1L)).withSelfRel());

        return ticketResponse;
    }

//    @Override
//    public void addLinks(EntityModel<TicketResponse> resource){
//        Long farmerId = resource.getContent().getFarmerId();
//        Long palletLabelId = resource.getContent().getPalletLabelId();
//        Long ticketId = resource.getContent().getId();
//
//        resource.add(linkTo(methodOn(TicketController.class).findOne(farmerId, palletLabelId, ticketId)).withSelfRel());
//        resource.add(linkTo(methodOn(TicketController.class).findAll(farmerId, palletLabelId)).withRel("tickets"));
//    }
//
//    @Override
//    public void addLinks(CollectionModel<EntityModel<TicketResponse>> resources) {
//        Long farmerId = resources.getContent().iterator().next().getContent().getFarmerId();
//        Long palletLabelId = resources.getContent().iterator().next().getContent().getPalletLabelId();
//
//        resources.add(linkTo(methodOn(TicketController.class).findAll(farmerId, palletLabelId)).withSelfRel());
//
//    }
}
