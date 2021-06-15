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
@Relation(itemRelation = "sort_type", collectionRelation = "sort_types")
public class SortTypeResponse extends RepresentationModel<SortTypeResponse> {
    private String name;
    private String description;
}
