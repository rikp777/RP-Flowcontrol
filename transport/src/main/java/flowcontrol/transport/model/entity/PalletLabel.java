package flowcontrol.transport.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class PalletLabel {
    private Long id;
    private Long generalId;
    private LocalDateTime cropDate;
    private String note;
    private Long palletLabelFarmerId;
    private Integer ArticleAmount;
}
