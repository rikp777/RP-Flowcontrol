package flowcontrol.article.service;

import flowcontrol.article.model.entity.PalletType;
import flowcontrol.article.repository.PalletTypeRepository;
import org.springframework.stereotype.Service;

@Service
public class PalletTypeService extends BaseService<PalletType> {

    private final PalletTypeRepository palletTypeRepository;

    public PalletTypeService(PalletTypeRepository palletTypeRepository) {
        super(palletTypeRepository);
        this.palletTypeRepository = palletTypeRepository;
    }
}
