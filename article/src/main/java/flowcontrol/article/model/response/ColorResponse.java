package flowcontrol.article.model.response;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Relation(itemRelation = "color", collectionRelation = "colors")
public class ColorResponse extends RepresentationModel<ColorResponse> {

    private String name;
}
