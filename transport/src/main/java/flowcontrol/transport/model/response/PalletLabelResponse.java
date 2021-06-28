package flowcontrol.transport.model.response;

import flowcontrol.transport.model.general.Article;
import flowcontrol.transport.model.general.Farmer;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PalletLabelResponse {
    private UUID id;
    private Article article;
    private Long generalId;
    private String cropDate;
    private String note;
    private UUID palletLabelFarmerId;
    private Integer articleAmount;

    private Integer harvestCycle;
    private Integer harvestCycleDay;

    private Farmer farmer;
}
