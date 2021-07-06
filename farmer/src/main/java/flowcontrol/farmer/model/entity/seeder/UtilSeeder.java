package flowcontrol.farmer.model.entity.seeder;

import flowcontrol.farmer.exception.ResourceNotFoundException;
import flowcontrol.farmer.model.entity.Cell;
import flowcontrol.farmer.model.entity.Certificate;
import flowcontrol.farmer.model.entity.Farmer;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Slf4j
public class UtilSeeder {

    private Set<Cell> cells;
    private Set<Farmer> farmers;
    private Set<Certificate> certificates;

    public static void sendMessage(String seederName, int insertNumber, String identifier, UUID id){
        log.info(seederName + " insert: " + insertNumber + " - " + identifier + " | " +
                "UUID: " + id
        );
    }

    public Cell findCellInSet(String cell){
        if(this.cells.stream().count() > 0) {
            return this.cells.stream()
                    .filter(item ->
                            item.getName().equals(cell)).findFirst()
                    .orElseThrow(() -> new ResourceNotFoundException("Cell", "Name", cell));
        }else {
            new Exception("Cells not set yet");
            return null;
        }
    }

    public Certificate findCertificateInSet(String certificate){
        if(this.certificates.stream().count() > 0) {
            return this.certificates.stream()
                    .filter(item ->
                            item.getName().equals(certificate)).findFirst()
                    .orElseThrow(() -> new ResourceNotFoundException("Certificats", "Name", certificates));
        }else {
            new Exception("Farmers not set yet");
            return null;
        }
    }

    public Farmer findFarmerInSet(String farmer){
        if(this.farmers.stream().count() > 0) {
            return this.farmers.stream()
                    .filter(item ->
                            item.getName().equals(farmer)).findFirst()
                    .orElseThrow(() -> new ResourceNotFoundException("Farmer", "Name", farmer));
        }else {
            new Exception("Farmers not set yet");
            return null;
        }
    }
}
