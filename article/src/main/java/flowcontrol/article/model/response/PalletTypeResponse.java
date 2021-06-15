package flowcontrol.article.model.response;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Relation(itemRelation = "pallet_type", collectionRelation = "pallet_types")
public class PalletTypeResponse extends RepresentationModel<PalletTypeResponse> {

    private String name;
    private Integer weight;
    private Double price;
}
