package flowcontrol.farmer.model.entity.seeder;

import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;
import flowcontrol.farmer.model.entity.Cell;
import flowcontrol.farmer.model.entity.Certificate;
import flowcontrol.farmer.repository.CellRepository;
import flowcontrol.farmer.repository.CertificateRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Configuration
@Slf4j
public class CellSeeder {
    @Autowired
    private final CellRepository cellRepo;


    public CellSeeder(CellRepository cellRepo) {
        this.cellRepo = cellRepo;
    }

    public Set<Cell> run(UtilSeeder util) {

        List<Cell> certificates = Arrays.asList(
                Cell.builder()
                        .name("1")
                        .description("Cell One")
                        .farmer(util.findFarmerInSet("Test farmer one"))
                        .build(),
                Cell.builder()
                        .name("2")
                        .description("Cell Two")
                        .farmer(util.findFarmerInSet("Test farmer two"))
                        .build()
        );

        if(Iterables.size(cellRepo.findAll()) == 0){
            log.info("Cells done seeding");
            cellRepo.saveAll(certificates);
        }

        return Sets.newHashSet(cellRepo.findAll());
    }
}
