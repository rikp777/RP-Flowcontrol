package flowcontrol.transport.service;

import flowcontrol.transport.exception.ResourceNotFoundException;
import flowcontrol.transport.model.entity.PalletLabel;
import flowcontrol.transport.model.general.Article;
import flowcontrol.transport.model.general.Cell;
import flowcontrol.transport.model.general.Farmer;
import flowcontrol.transport.model.general.PalletType;
import flowcontrol.transport.model.request.CreatePalletLabelRequest;
import flowcontrol.transport.repository.PalletLabelRepository;
import flowcontrol.transport.repository.impl.ArticleRepository;
import flowcontrol.transport.repository.impl.CellRepository;
import flowcontrol.transport.repository.impl.FarmerRepository;
import flowcontrol.transport.repository.impl.PalletTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PalletLabelService {

    private final PalletLabelRepository palletLabelRepository;
    private final ArticleRepository articleRepository;
    private final CellRepository cellRepository;
    private final FarmerRepository farmerRepository;
    private final PalletTypeRepository palletTypeRepository;

    public List<PalletLabel> getAll(){
        return palletLabelRepository.findAll();
    }

    public Optional<PalletLabel> getById(Long palletLabelId){
        return palletLabelRepository.findById(palletLabelId);
    }


    public Optional<PalletLabel> create(CreatePalletLabelRequest newPalletLabel){
        Long articleId = newPalletLabel.getArticleId();
        Long cellId = newPalletLabel.getCellId();
        Long farmerId = newPalletLabel.getFarmerId();
        Long palletTypeId = newPalletLabel.getPalletTypeId();

        Article article = articleRepository
                .findById(articleId)
                .orElseThrow(() -> new ResourceNotFoundException("Article", "Id", articleId));

        Cell cell = cellRepository
                .findById(cellId)
                .orElseThrow(() -> new ResourceNotFoundException("Cell", "Id", cellId));

        Farmer farmer = farmerRepository
                .findById(farmerId)
                .orElseThrow(() -> new ResourceNotFoundException("Farmer", "Id", farmerId));

        PalletType palletType = palletTypeRepository
                .findById(palletTypeId)
                .orElseThrow(() -> new ResourceNotFoundException("PalletType", "Id", palletTypeId));

        // Create new
        PalletLabel palletLabel = new PalletLabel();

        // IDS
        palletLabel.setGeneralId(1L);
        palletLabel.setPalletLabelFarmerId(1L);

        // Data
        palletLabel.setCropDate(newPalletLabel.getCropDate());
        palletLabel.setHarvestCycle(newPalletLabel.getHarvestCycle());
        palletLabel.setHarvestCycleDay(newPalletLabel.getHarvestCycleDay());
        palletLabel.setArticleAmount(newPalletLabel.getArticleAmount());
        palletLabel.setNote(newPalletLabel.getNote());

        // Relations
        palletLabel.setArticle(articleId);
        palletLabel.setCell(cellId);
        palletLabel.setFarmer(farmerId);
        palletLabel.setPalletType(palletTypeId);

        // Save and return
        return Optional.of(palletLabelRepository.save(palletLabel));
    }
}
