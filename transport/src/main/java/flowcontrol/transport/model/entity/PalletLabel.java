package flowcontrol.transport.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PalletLabel {
    private String id;
    private String generalId;
    private String cropDate;
    private String note;
    private String palletLabelFarmerId;

}
