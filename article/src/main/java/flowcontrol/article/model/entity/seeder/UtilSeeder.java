package flowcontrol.article.model.entity.seeder;

import flowcontrol.article.exception.ResourceNotFoundException;
import flowcontrol.article.model.entity.*;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;

@Getter
@Setter
@Slf4j
public class UtilSeeder {
    private Set<Color> colors;
    private Set<PalletType> palletTypes;
    private Set<Cask> casks;
    private Set<Group> groups;
    private Set<SortType> sortTypes;
    private Set<Inset> insets;
    private Set<Type> types;

    public Color findColorInSet(String color){
        if(this.colors.stream().count() > 0) {
            return this.colors.stream()
                    .filter(item ->
                            item.getName().equals(color)).findFirst()
                    .orElseThrow(() -> new ResourceNotFoundException("Color", "Name", color));
        }else {
            log.error("Colors not set yet");
            return null;
        }
    }
    public PalletType findPalletTypeInSet(String palletType){
        if(this.palletTypes.stream().count() >0){
            return palletTypes.stream()
                    .filter(item ->
                            item.getName().equals(palletType)).findFirst()
                    .orElseThrow(() -> new ResourceNotFoundException("PalletType", "Name", palletType));
        }else {
            log.error("Pallet types not set yet");
            return null;
        }
    }

    public Cask findCaskInSet(String cask){
        if(this.casks.stream().count() >0){
            return casks.stream()
                    .filter(item ->
                            item.getName().equals(cask)).findFirst()
                    .orElseThrow(() -> new ResourceNotFoundException("Cask", "Name", cask));
        }else {
            log.error("Casks not set yet");
            return null;
        }
    }

    public Group findGroupInSet(String group){
        if(this.groups.stream().count() >0){
            return groups.stream()
                    .filter(item ->
                            item.getName().equals(group)).findFirst()
                    .orElseThrow(() -> new ResourceNotFoundException("Group", "Name", group));
        }else {
            log.error("Groups not set yet");
            return null;
        }
    }
    public Type findTypeInSet(String type){
        if(this.types.stream().count() >0){
            return types.stream()
                    .filter(item ->
                            item.getName().equals(type)).findFirst()
                    .orElseThrow(() -> new ResourceNotFoundException("Type", "Name", type));
        }else {
            log.error("Types not set yet");
            return null;
        }
    }

    public SortType findSortTypeInSet(String sortType){
        if(this.sortTypes.stream().count() >0){
            return sortTypes.stream()
                    .filter(item ->
                            item.getName().equals(sortType)).findFirst()
                    .orElseThrow(() -> new ResourceNotFoundException("SortType", "Name", sortType));
        }else {
            log.error("SortType not set yet");
            return null;
        }
    }

    public Inset findInsetInSet(String insetExcelCode){
        if(this.insets.stream().count() >0){
            return insets.stream()
                    .filter(item ->
                            item.getExcelCode().equals(insetExcelCode)).findFirst()
                    .orElseThrow(() -> new ResourceNotFoundException("Inset", "ExcelCode", insetExcelCode));
        }else {
            log.error("Insets not set yet");
            return null;
        }
    }
}
