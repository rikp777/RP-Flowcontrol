package flowcontrol.article.model.response;


import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Relation(itemRelation = "group", collectionRelation = "groups")
public class GroupResponse extends RepresentationModel<GroupResponse> {

    private String name;
    private String description;
}
