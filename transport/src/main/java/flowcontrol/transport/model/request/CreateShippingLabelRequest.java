package flowcontrol.transport.model.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class CreateShippingLabelRequest {

    private String transportDate;
    private String transportDeliveryDate;
    private UUID transportDriverId;
    private UUID truckId;
    private UUID[] palletLabelIds;
}
