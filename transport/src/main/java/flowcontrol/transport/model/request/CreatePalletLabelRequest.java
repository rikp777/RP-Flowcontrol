package flowcontrol.transport.model.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class CreatePalletLabelRequest {
    private String cropDate;
    private String note;
    private Integer articleAmount;

    private Integer harvestCycle;
    private Integer harvestCycleDay;

    private Long cellId;
    private Long articleId;
    private Long farmerId;
    private Long palletTypeId;
}
