package flowcontrol.transport.service;

import flowcontrol.transport.model.entity.PalletLabel;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PalletLabelService {

    private List<PalletLabel> palletLabels = Arrays.asList(
            new PalletLabel(1l, 1l, LocalDateTime.now(), "test", 1l, 180),
            new PalletLabel(2l, 2l, LocalDateTime.now(), "test", 2l,180)
    );

    public List<PalletLabel> getAll(){
        return this.palletLabels;
    }

    public PalletLabel getById(Long palletLabelId){
        return this.palletLabels.stream()
                .filter(
                        palletLabel -> palletLabel.getId().equals(palletLabelId)
                )
                .findFirst()
                .orElse(null);
    }
}
