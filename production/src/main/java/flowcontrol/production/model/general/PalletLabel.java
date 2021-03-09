package flowcontrol.production.model.general;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PalletLabel {
    private Long id;
    private Long generalId;
    private String cropDate;
    private String note;
    private Long palletLabelFarmerId;
    private Integer articleAmount;
}

