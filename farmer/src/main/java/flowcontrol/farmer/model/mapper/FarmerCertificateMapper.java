package flowcontrol.farmer.model.mapper;


import flowcontrol.farmer.model.entity.Cell;
import flowcontrol.farmer.model.entity.FarmerCertificate;
import flowcontrol.farmer.model.request.cell.CreateCellRequest;
import flowcontrol.farmer.model.request.cell.UpdateCellRequest;
import flowcontrol.farmer.model.request.farmerCertificate.FarmerCertificateRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class FarmerCertificateMapper extends BaseMapper<FarmerCertificate, FarmerCertificateRequest, FarmerCertificateRequest>{
}

