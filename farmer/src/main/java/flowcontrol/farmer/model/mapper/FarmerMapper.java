package flowcontrol.farmer.model.mapper;

import flowcontrol.farmer.model.entity.Farmer;
import flowcontrol.farmer.model.request.farmer.CreateFarmerRequest;
import flowcontrol.farmer.model.request.farmer.UpdateFarmerRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class FarmerMapper extends BaseMapper<Farmer, CreateFarmerRequest, UpdateFarmerRequest>{
}
