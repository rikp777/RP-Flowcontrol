package flowcontrol.transport.model.response;

import flowcontrol.transport.model.general.Article;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PalletLabelResponse {
    private Long id;
    private Article article;
    private Long generalId;
    private String cropDate;
    private String note;
    private Long palletLabelFarmerId;
    private Integer articleAmount;

    private Integer harvestCycle;
    private Integer harvestCycleDay;
}
