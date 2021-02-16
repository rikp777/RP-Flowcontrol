package flowcontrol.production.service;

import flowcontrol.production.model.entity.Interruption;
import flowcontrol.production.repository.InterruptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InterruptionService {

    @Autowired
    private final InterruptionRepository interruptionRepository;

    public InterruptionService(InterruptionRepository interruptionRepository) {
        this.interruptionRepository = interruptionRepository;
    }


    public List<Interruption> getAll(){
        return interruptionRepository.findAll();
    }
    public Interruption getById(Long id){
        return interruptionRepository.findById(id).orElse(null);
    }

    public Interruption create(Interruption interruption){
        interruptionRepository.save(interruption);
        return null;
    }
    public Interruption update(Interruption interruption){
        interruptionRepository.save(interruption);
        return null;
    }
    public boolean delete(Long id){
        interruptionRepository.deleteById(id);
        return false;
    }
}
