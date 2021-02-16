package flowcontrol.production.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Ticket {

    private String palletLabelId;
    private String startAt;
    private String endAt;
}
