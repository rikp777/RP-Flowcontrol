package flowcontrol.production.model.entity.seeder;

import flowcontrol.production.model.entity.InterruptionReason;
import flowcontrol.production.repository.InterruptionReasonRepository;
import flowcontrol.production.repository.InterruptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InterruptionReasonSeeder {

    @Autowired
    private final InterruptionReasonRepository interruptionReasonRepository;

    public InterruptionReasonSeeder(InterruptionReasonRepository interruptionReasonRepository) {
        this.interruptionReasonRepository = interruptionReasonRepository;
    }

    public void run(){
        InterruptionReason interruptionReasonOne = new InterruptionReason();
        interruptionReasonOne.setName("Break");
        interruptionReasonOne.setDescription("Employees will take breaks");
        interruptionReasonOne.setStopProcess(false);
        interruptionReasonRepository.save(interruptionReasonOne);

        InterruptionReason interruptionReasonTwo = new InterruptionReason();
        interruptionReasonTwo.setName("New Foil");
        interruptionReasonTwo.setDescription("Employees change the foil for packaging or the roll is out. New foil will be loaded which stops the process for a while");
        interruptionReasonTwo.setStopProcess(false);
        interruptionReasonRepository.save(interruptionReasonTwo);

        InterruptionReason interruptionReasonThree = new InterruptionReason();
        interruptionReasonThree.setName("Sticker change");
        interruptionReasonThree.setDescription("Employees change the stickers for packaging or the stickers are out. New stickers will be loaded which stops the process for a while");
        interruptionReasonThree.setStopProcess(false);
        interruptionReasonRepository.save(interruptionReasonThree);

        InterruptionReason interruptionReasonFour = new InterruptionReason();
        interruptionReasonFour.setName("Product swap");
        interruptionReasonFour.setDescription("Employees swap the product being processed on the belt, stopping the current process");
        interruptionReasonFour.setStopProcess(true);
        interruptionReasonRepository.save(interruptionReasonFour);

        InterruptionReason interruptionReasonFive = new InterruptionReason();
        interruptionReasonFive.setName("Product disapproval");
        interruptionReasonFive.setDescription("The product does not meet the specified requirements and may not be further processed");
        interruptionReasonFive.setStopProcess(true);
        interruptionReasonRepository.save(interruptionReasonFive);

        InterruptionReason interruptionReasonSix = new InterruptionReason();
        interruptionReasonSix.setName("Production line error");
        interruptionReasonSix.setDescription("The line doesn't work anymore and a mechanic has to come to fix it");
        interruptionReasonSix.setStopProcess(true);
        interruptionReasonRepository.save(interruptionReasonSix);
    }
}
