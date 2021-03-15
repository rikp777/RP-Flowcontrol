package flowcontrol.transport.service;

import flowcontrol.transport.exception.ShippingLabelException;
import flowcontrol.transport.model.entity.PalletLabel;
import flowcontrol.transport.model.entity.ShippingLabel;
import flowcontrol.transport.model.request.CreateShippingLabelRequest;
import flowcontrol.transport.repository.PalletLabelRepository;
import flowcontrol.transport.repository.ShippingLabelRepository;
import flowcontrol.transport.repository.impl.ArticleRepository;
import flowcontrol.transport.repository.impl.CellRepository;
import flowcontrol.transport.repository.impl.FarmerRepository;
import flowcontrol.transport.repository.impl.PalletTypeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
@Slf4j
public class ShippingLabelService {

    private final PalletLabelRepository palletLabelRepository;
    private final ArticleRepository articleRepository;
    private final CellRepository cellRepository;
    private final FarmerRepository farmerRepository;
    private final PalletTypeRepository palletTypeRepository;
    private final ShippingLabelRepository shippingLabelRepository;


    private final PalletLabelService palletLabelService;

    public Optional<ShippingLabel> getAll(Long farmerId, Long shippingLabelId){
        Optional<ShippingLabel> shippingLabel = shippingLabelRepository.findById(shippingLabelId);

        return shippingLabel;
    }

    public Optional<ShippingLabel> create(Long farmerId, CreateShippingLabelRequest newShippingLabel){
        if(newShippingLabel.getPalletLabelIds().length == 0) throw new ShippingLabelException("Create", "Palletlabels" +
                " are not set", newShippingLabel);

        for (Long palletLabelId : newShippingLabel.getPalletLabelIds()){
            PalletLabel palletLabel = palletLabelService.getById(farmerId, palletLabelId).get();
            if(palletLabel.getShippingLabel() != null){
                throw  new ShippingLabelException("Create", "Given palletlabel already has a shippinglabel attached to it", newShippingLabel);
            }
        }

        ShippingLabel shippingLabel = new ShippingLabel();
        shippingLabel.setGeneralId(1L);
        shippingLabel.setTransportDate("2020-02-02");
        shippingLabel.setTransportDeliveryDate("2020-02-02");
        shippingLabel.setFarmerId(farmerId);
        shippingLabel.setTruckId(1L);
        shippingLabel.setTransportDriverId(1L);
        ShippingLabel shippingLabelSaved = shippingLabelRepository.save(shippingLabel);

        for (Long palletLabelId : newShippingLabel.getPalletLabelIds()){
            PalletLabel palletLabel = palletLabelService.getById(farmerId, palletLabelId).get();
            palletLabel.setShippingLabel(shippingLabelSaved);
            palletLabelRepository.save(palletLabel);
        }

        return shippingLabelRepository.findById(shippingLabelSaved.getId());
    }
}
