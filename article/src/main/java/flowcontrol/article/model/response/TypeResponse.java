package flowcontrol.article.model.response;


import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Builder
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Relation(itemRelation = "type", collectionRelation = "types")
public class TypeResponse extends RepresentationModel<TypeResponse> {

    private String name;
}
