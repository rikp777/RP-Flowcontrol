package flowcontrol.transport.model.entity.seeder;

import flowcontrol.transport.exception.ResourceNotFoundException;
import flowcontrol.transport.model.entity.PalletLabel;
import flowcontrol.transport.model.entity.ShippingLabel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Slf4j
public class UtilSeeder {

    private Set<PalletLabel> palletLabels;
    private Set<ShippingLabel> shippingLabels;


    public static void sendMessage(String seederName, int insertNumber, String identifier, UUID id){
        log.info(seederName + " insert: " + insertNumber + " - " + identifier + " | " +
                "UUID: " + id
        );
    }

    public PalletLabel findPalletLabelInSet(UUID palletLabelId){
        if(this.palletLabels.stream().count() > 0) {
            return this.palletLabels.stream()
                    .filter(item ->
                            item.getId().equals(palletLabelId)).findFirst()
                    .orElseThrow(() -> new ResourceNotFoundException("palletLabel", "Id", palletLabelId));
        }else {
            new Exception("Pallet labels not set yet");
            return null;
        }
    }

    public ShippingLabel findShippingLabelInSet(UUID shippingLabelId){
        if(this.shippingLabels.stream().count() > 0) {
            return this.shippingLabels.stream()
                    .filter(item ->
                            item.getId().equals(shippingLabelId)).findFirst()
                    .orElseThrow(() -> new ResourceNotFoundException("ShippingLabel", "Id", shippingLabelId));
        }else {
            new Exception("Shipping labels not set yet");
            return null;
        }
    }
}
