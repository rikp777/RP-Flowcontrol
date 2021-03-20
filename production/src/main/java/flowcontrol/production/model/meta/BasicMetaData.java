package flowcontrol.production.model.meta;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class BasicMetaData {
    private Long farmerId;
    private Long palletLabelId;
}
