package flowcontrol.farmer.model.response;

import lombok.Builder;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Builder
@Data
@Relation(itemRelation = "cell", collectionRelation = "cells")
public class CellResponse extends RepresentationModel<CellResponse> {

    private String name;
    private String description;
}
