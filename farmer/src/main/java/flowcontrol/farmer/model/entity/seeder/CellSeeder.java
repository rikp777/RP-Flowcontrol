package flowcontrol.farmer.model.entity.seeder;

import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;
import flowcontrol.farmer.config.seeder.SeederConfig;
import flowcontrol.farmer.model.entity.Cell;
import flowcontrol.farmer.repository.CellRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Configuration
@Slf4j
public class CellSeeder {
    private final CellRepository cellRepo;
    private SeederConfig seederConfig;

    private int id = 1;

    public CellSeeder(CellRepository cellRepo, SeederConfig seederConfig) {
        this.cellRepo = cellRepo;
        this.seederConfig = seederConfig;
    }

    private void message(Cell cell){
        if(this.seederConfig.isInDebugMode())
            UtilSeeder.sendMessage("Cell seeder", this.id, cell.getName(), cell.getId());
        this.id++;
    }

    public Set<Cell> run(UtilSeeder util) {
        if(this.seederConfig.isInInsetDataMode()){
            if(cellRepo.findById(UUID.fromString("2950cbec-b8b3-4a9e-8847-b2c92418fc42")).isEmpty()){
                Cell cell = new Cell(UUID.fromString("2950cbec-b8b3-4a9e-8847-b2c92418fc42"));
                cell.setName("1");
                cell.setDescription("Cell One");
                cell.setFarmer(util.findFarmerInSet("Test farmer one"));
                cellRepo.save(cell);

                message(cell);
            }
            if(cellRepo.findById(UUID.fromString("548d23de-11c4-4c06-9268-6558bd4b7e6e")).isEmpty()){
                Cell cell = new Cell(UUID.fromString("548d23de-11c4-4c06-9268-6558bd4b7e6e"));
                cell.setName("2");
                cell.setDescription("Cell Two");
                cell.setFarmer(util.findFarmerInSet("Test farmer two"));
                cellRepo.save(cell);

                message(cell);
            }
            log.info("Cell seeding done, seeded: " +  (this.id - 1) + " cells.");

        } else {
            log.info("Cell seeding not required");
        }

        return Sets.newHashSet(cellRepo.findAll());
    }
}
