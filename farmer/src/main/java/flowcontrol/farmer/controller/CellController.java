package flowcontrol.farmer.controller;

import flowcontrol.farmer.model.entity.Cell;
import flowcontrol.farmer.model.entity.Farmer;
import flowcontrol.farmer.service.CellService;
import flowcontrol.farmer.service.FarmerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/v1/farmers/{farmerId}/cells")
@AllArgsConstructor
public class CellController {

    private final CellService cellService;


    @GetMapping
    public Iterable<Cell> getAll(@PathVariable Long farmerId){
        return cellService.getAll(farmerId);
    }

    @GetMapping("/{cellId}")
    public Optional<Cell> getById(@PathVariable Long farmerId, @PathVariable Long cellId){
        return cellService.getById(farmerId, cellId);
    }
}
