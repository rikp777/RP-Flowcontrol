package flowcontrol.article.model.request.group;

import com.fasterxml.jackson.annotation.JsonProperty;
import flowcontrol.article.model.request.SequenceFirstOrder;
import flowcontrol.article.model.request.SequenceSecondOrder;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.GroupSequence;


@Data
@Accessors(chain = true)
@GroupSequence({CreateGroupRequest.class, SequenceFirstOrder.class, SequenceSecondOrder.class})
public class CreateGroupRequest {

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;


}
