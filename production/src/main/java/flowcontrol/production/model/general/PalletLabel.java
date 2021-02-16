package flowcontrol.production.model.general;

import lombok.Data;

@Data
public class PalletLabel {
    private String id;
    private String generalId;
    private String cropDate;
    private String note;
    private String palletLabelFarmerId;
}

