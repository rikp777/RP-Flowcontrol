package flowcontrol.farmer.controller;

import flowcontrol.farmer.model.entity.Farmer;
import flowcontrol.farmer.service.FarmerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/v1/farmers")
@AllArgsConstructor
public class FarmerController {

    private final FarmerService farmerService;


    @GetMapping
    public Iterable<Farmer> getAll(){
        return farmerService.getAll();
    }

    @GetMapping("/{farmerId}")
    public Optional<Farmer> getById(@PathVariable Long farmerId){
        System.out.println(farmerId);
        return farmerService.getById(farmerId);
    }
}
