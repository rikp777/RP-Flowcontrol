package flowcontrol.production.model.meta;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@Getter
public class BasicMetaData {
    private UUID farmerId;
    private UUID palletLabelId;
}
