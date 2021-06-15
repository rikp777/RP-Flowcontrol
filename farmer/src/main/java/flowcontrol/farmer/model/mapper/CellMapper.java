package flowcontrol.farmer.model.mapper;

import flowcontrol.farmer.model.entity.Cell;
import flowcontrol.farmer.model.entity.Farmer;
import flowcontrol.farmer.model.request.cell.CreateCellRequest;
import flowcontrol.farmer.model.request.cell.UpdateCellRequest;
import flowcontrol.farmer.model.request.farmer.CreateFarmerRequest;
import flowcontrol.farmer.model.request.farmer.UpdateFarmerRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class CellMapper extends BaseMapper<Cell, CreateCellRequest, UpdateCellRequest>{
}
