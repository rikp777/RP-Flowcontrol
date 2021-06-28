package flowcontrol.production.model.entity.seeder;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Slf4j
public class UtilSeeder {
    public static void sendMessage(String seederName, int insertNumber, String identifier, UUID id){
        log.info(seederName + " insert: " + insertNumber + " - " + identifier + " | " +
                "UUID: " + id
        );
    }
}
