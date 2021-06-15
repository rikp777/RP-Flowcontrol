package flowcontrol.article.model.mapper;

import flowcontrol.article.model.entity.PalletType;
import flowcontrol.article.model.request.palletType.CreatePalletTypeRequest;
import flowcontrol.article.model.request.palletType.UpdatePalletTypeRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class PalletTypeMapper extends BaseMapper<PalletType, CreatePalletTypeRequest, UpdatePalletTypeRequest>{
}
