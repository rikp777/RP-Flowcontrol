package flowcontrol.production.model.entity.seeder;

import flowcontrol.production.config.seeder.SeederConfig;
import flowcontrol.production.model.entity.InterruptionReason;
import flowcontrol.production.repository.InterruptionReasonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Configuration
@Slf4j
public class InterruptionReasonSeeder {

    private final InterruptionReasonRepository interruptionReasonRepository;
    private SeederConfig seederConfig;

    private int id = 1;

    public InterruptionReasonSeeder(InterruptionReasonRepository interruptionReasonRepository, SeederConfig seederConfig) {
        this.interruptionReasonRepository = interruptionReasonRepository;
        this.seederConfig = seederConfig;
    }

    private void message(InterruptionReason interruptionReason){
        if(this.seederConfig.isInDebugMode())
            UtilSeeder.sendMessage("InterruptionReason seeder", this.id, interruptionReason.getName(), interruptionReason.getId());
        this.id++;
    }

    public void run(){
        if(this.seederConfig.isInInsetDataMode()){
            if(interruptionReasonRepository.findByName("Break").isEmpty()){
                InterruptionReason interruptionReason = new InterruptionReason(UUID.fromString("833c0bf0-b070-4125-b1f8-fe30816645d5"));
                interruptionReason.setName("Break");
                interruptionReason.setDescription("Employees will take breaks");
                interruptionReason.setStopProcess(false);
                interruptionReasonRepository.save(interruptionReason);

                this.message(interruptionReason);
            }

            if(interruptionReasonRepository.findByName("New Foil").isEmpty()){
                InterruptionReason interruptionReason = new InterruptionReason(UUID.fromString("29a3a649-2319-4bec-85c7-b25529bc2cb6"));
                interruptionReason.setName("New Foil");
                interruptionReason.setDescription("Employees change the foil for packaging or the roll is out. New foil will be loaded which stops the process for a while");
                interruptionReason.setStopProcess(false);
                interruptionReasonRepository.save(interruptionReason);

                this.message(interruptionReason);
            }

            if(interruptionReasonRepository.findByName("Sticker change").isEmpty()){
                InterruptionReason interruptionReason = new InterruptionReason(UUID.fromString("572340cc-8894-4636-8522-6f559692d8db"));
                interruptionReason.setName("Sticker change");
                interruptionReason.setDescription("Employees change the stickers for packaging or the stickers are out. New stickers will be loaded which stops the process for a while");
                interruptionReason.setStopProcess(false);
                interruptionReasonRepository.save(interruptionReason);

                this.message(interruptionReason);
            }

            if(interruptionReasonRepository.findByName("Product swap").isEmpty()){
                InterruptionReason interruptionReason = new InterruptionReason(UUID.fromString("4e359093-2aa3-4559-8582-334ae66c7c7c"));
                interruptionReason.setName("Product swap");
                interruptionReason.setDescription("Employees swap the product being processed on the belt, stopping the current process");
                interruptionReason.setStopProcess(true);
                interruptionReasonRepository.save(interruptionReason);

                this.message(interruptionReason);
            }

            if(interruptionReasonRepository.findByName("Product disapproval").isEmpty()){
                InterruptionReason interruptionReason = new InterruptionReason(UUID.fromString("3a265806-3948-46a1-917b-a725f5c2e1cc"));
                interruptionReason.setName("Product disapproval");
                interruptionReason.setDescription("The product does not meet the specified requirements and may not be further processed");
                interruptionReason.setStopProcess(true);
                interruptionReasonRepository.save(interruptionReason);

                this.message(interruptionReason);
            }

            if(interruptionReasonRepository.findByName("Production line error").isEmpty()){
                InterruptionReason interruptionReason = new InterruptionReason(UUID.fromString("721d6aa0-e3a3-4f12-83a6-72f2d59148e9"));
                interruptionReason.setName("Production line error");
                interruptionReason.setDescription("The line doesn't work anymore and a mechanic has to come to fix it");
                interruptionReason.setStopProcess(true);
                interruptionReasonRepository.save(interruptionReason);

                this.message(interruptionReason);
            }

            log.info("Interruption reason seeding done, seeded: " +  (this.id - 1) + " interruption reasons.");
        }else {
            log.info("Interruption reason seeding not required");
        }
    }
}
