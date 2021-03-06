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
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class PalletLabelService {

    private final PalletLabelRepository palletLabelRepository;
    private final ArticleRepository articleRepository;
    private final CellRepository cellRepository;
    private final FarmerRepository farmerRepository;
    private final PalletTypeRepository palletTypeRepository;

    public List<PalletLabel> getAll(UUID farmerId){
        log.info(
                Thread.currentThread().getStackTrace()[2].getClassName() + "." +
                Thread.currentThread().getStackTrace()[2].getMethodName() + "." +
                "farmerId:[" + farmerId +"]"
        );

        return palletLabelRepository.findAll();
    }

    public Optional<PalletLabel> getById(UUID farmerId, UUID palletLabelId){
        log.info(
                Thread.currentThread().getStackTrace()[2].getClassName() + "." +
                Thread.currentThread().getStackTrace()[2].getMethodName() + "." +
                "farmerId:[" + farmerId +"]." +
                "palletLabelId[" + palletLabelId + "]"
        );

        return palletLabelRepository
                .findById(palletLabelId);
    }

    public Optional<PalletLabel> getByGeneralId(UUID farmerId, Long generalPalletLabelId){
        log.info(
                Thread.currentThread().getStackTrace()[2].getClassName() + "." +
                        Thread.currentThread().getStackTrace()[2].getMethodName() + "." +
                        "farmerId:[" + farmerId +"]." +
                        "palletLabelId[" + generalPalletLabelId + "]"
        );

        return palletLabelRepository
                .findByGeneralId(generalPalletLabelId);
    }


    public Optional<PalletLabel> create(UUID farmerId, CreatePalletLabelRequest newPalletLabel){
        log.info(
                Thread.currentThread().getStackTrace()[2].getClassName() + "." +
                Thread.currentThread().getStackTrace()[2].getMethodName() + "." +
                "farmerId:[" + farmerId +"]"
        );

        UUID articleId = newPalletLabel.getArticleId();
        UUID cellId = newPalletLabel.getCellId();
        UUID palletTypeId = newPalletLabel.getPalletTypeId();

        Article article = articleRepository.findById(articleId);
        Cell cell = cellRepository.findById(farmerId, cellId);
        Farmer farmer = farmerRepository.findById(farmerId);
        PalletType palletType = palletTypeRepository.findById(palletTypeId);

        // Create new
        PalletLabel palletLabel = new PalletLabel();

        //Get lasts id's
        //Last general id
        //Last specific farmer id

        // IDS
        palletLabel.setGeneralId(1L);
        palletLabel.setPalletLabelFarmerId(2l);

        // Data
        palletLabel.setCropDate(newPalletLabel.getCropDate());
        palletLabel.setHarvestCycle(newPalletLabel.getHarvestCycle());
        palletLabel.setHarvestCycleDay(newPalletLabel.getHarvestCycleDay());
        palletLabel.setArticleAmount(newPalletLabel.getArticleAmount());
        palletLabel.setNote(newPalletLabel.getNote());

        // Relations
        palletLabel.setArticle(newPalletLabel.getPalletTypeId());
        palletLabel.setCell(cellId);
        palletLabel.setFarmer(farmerId);
        palletLabel.setPalletType(palletTypeId);

        // Save and return
        return Optional.of(palletLabelRepository.save(palletLabel));
    }
}
