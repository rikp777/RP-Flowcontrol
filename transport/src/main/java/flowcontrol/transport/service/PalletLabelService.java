package flowcontrol.transport.service;

import flowcontrol.transport.model.entity.PalletLabel;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PalletLabelService {

    private List<PalletLabel> palletLabels = Arrays.asList(
            new PalletLabel("1", "1", "2020-02-16", "test", "1"),
            new PalletLabel("2", "2", "2020-02-16", "test", "2")
    );

    public List<PalletLabel> getAll(){
        return this.palletLabels;
    }

    public PalletLabel getById(String palletLabelId){
        return this.palletLabels.stream()
                .filter(
                        palletLabel -> palletLabel.getId().equals(palletLabelId)
                )
                .findFirst()
                .orElse(null);
    }
}
