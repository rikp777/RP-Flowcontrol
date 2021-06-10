package flowcontrol.production.model.general;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class PalletLabel implements Serializable {
    private Long id;
    private Long generalId;
    private String cropDate;
    private String note;
    private Long palletLabelFarmerId;
    private Integer articleAmount;
    private Article article;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Farmer farmer;
}

