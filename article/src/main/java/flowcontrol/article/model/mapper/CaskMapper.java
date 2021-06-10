package flowcontrol.article.model.mapper;

import flowcontrol.article.model.entity.Cask;
import flowcontrol.article.model.entity.Color;
import flowcontrol.article.model.request.cask.CreateCaskRequest;
import flowcontrol.article.repository.ColorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;


@AllArgsConstructor
@Component
public class CaskMapper extends BaseMapper<Cask> {

    private final ColorRepository colorRepository;

    public Cask toEntity(CreateCaskRequest createCaskRequest){
        Cask original = new Cask();

        if(createCaskRequest.getExcelCode() != null){
            original.setExcelCode(createCaskRequest.getExcelCode());
        }
        if(createCaskRequest.getName() != null){
            original.setName(createCaskRequest.getName());
        }
        if(createCaskRequest.getDescription() != null){
            original.setDescription(createCaskRequest.getDescription());
        }
        if(createCaskRequest.getWeight() != null){
            original.setWeight(createCaskRequest.getWeight());
        }
        if(createCaskRequest.getMaterial() != null){
            original.setMaterial(createCaskRequest.getMaterial());
        }
        if(createCaskRequest.getMaxFillingQuantity() != null){
            original.setMaxFillingQuantity(createCaskRequest.getMaxFillingQuantity());
        }
        if(createCaskRequest.getColorId() != null){
            Color color = new Color();
            color.setId(toLong(createCaskRequest.getColorId()));
            if(color.getId() == 0){
                color = null;
            }else{
                color = colorRepository.findById(color.getId()).get();
            }
            original.setColor(color);
        }

        return original;
    }

    public Cask mapUpdatesToOriginal(CreateCaskRequest updateCask, Cask original) {
        if(updateCask.getExcelCode() != null){
            original.setExcelCode(updateCask.getExcelCode());
        }
        if(updateCask.getName() != null){
            original.setName(updateCask.getName());
        }
        if(updateCask.getDescription() != null){
            original.setDescription(updateCask.getDescription());
        }
        if(updateCask.getWeight() != null){
            original.setWeight(updateCask.getWeight());
        }
        if(updateCask.getMaterial() != null){
            original.setMaterial(updateCask.getMaterial());
        }
        if(updateCask.getMaxFillingQuantity() != null){
            original.setMaxFillingQuantity(updateCask.getMaxFillingQuantity());
        }
        if(updateCask.getColorId() != null){
            Color color = new Color();
            color.setId(toLong(updateCask.getColorId()));
            if(color.getId() == 0){
                color = null;
            }else{
                color = colorRepository.findById(color.getId()).get();
            }
            original.setColor(color);
        }

        return original;
    }
}
