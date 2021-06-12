package flowcontrol.article.model.mapper;

import flowcontrol.article.model.entity.Color;
import flowcontrol.article.model.request.color.CreateColorRequest;
import flowcontrol.article.model.request.color.UpdateColorRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class ColorMapper extends BaseMapper<Color, CreateColorRequest, UpdateColorRequest>{
}
