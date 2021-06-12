package flowcontrol.article.model.mapper;

import flowcontrol.article.model.entity.Type;
import flowcontrol.article.model.request.type.CreateTypeRequest;
import flowcontrol.article.model.request.type.UpdateTypeRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class TypeMapper extends BaseMapper<Type, CreateTypeRequest, UpdateTypeRequest>{
}
