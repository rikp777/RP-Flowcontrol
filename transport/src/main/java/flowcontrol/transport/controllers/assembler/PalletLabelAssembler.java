package flowcontrol.transport.controllers.assembler;

import flowcontrol.transport.controllers.PalletLabelController;
import flowcontrol.transport.model.entity.PalletLabel;
import flowcontrol.transport.model.general.Article;
import flowcontrol.transport.model.response.PalletLabelResponse;
import flowcontrol.transport.repository.impl.ArticleRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@AllArgsConstructor
@Component
public class PalletLabelAssembler implements RepresentationModelAssembler<PalletLabel, PalletLabelResponse> {
    @Autowired
    private final ArticleRepository articleRepository;

    @Override
    public PalletLabelResponse toModel(PalletLabel entity) {
        Article article = articleRepository.findById(entity.getArticle());
        PalletLabelResponse response = new PalletLabelResponse().builder()
                .generalId(entity.getGeneralId())
                .article(article)
                .articleAmount(entity.getArticleAmount())
                .cropDate(entity.getCropDate())
                .harvestCycle(entity.getHarvestCycle())
                .harvestCycleDay(entity.getHarvestCycleDay())
                .build();

        response
                .add(linkTo(methodOn(PalletLabelController.class)
                        .getPalletLabel(entity.getFarmer(), entity.getId()))
                        .withSelfRel());

        response.add(new Link("/farmer/api/v1/farmers/" + entity.getFarmer()).withRel("farmer"));

        return response;
    }

    @Override
    public CollectionModel toCollectionModel(Iterable entities) {
        return RepresentationModelAssembler.super.toCollectionModel(entities);
    }
}
