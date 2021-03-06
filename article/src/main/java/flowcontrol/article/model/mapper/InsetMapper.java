package flowcontrol.article.model.mapper;

import flowcontrol.article.model.entity.Inset;
import flowcontrol.article.model.request.inset.CreateInsetRequest;
import flowcontrol.article.model.request.inset.UpdateInsetRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class InsetMapper extends BaseMapper<Inset, CreateInsetRequest, UpdateInsetRequest>{
}
