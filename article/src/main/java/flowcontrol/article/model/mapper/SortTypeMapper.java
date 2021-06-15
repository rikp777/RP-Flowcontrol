package flowcontrol.article.model.mapper;

import flowcontrol.article.model.entity.SortType;
import flowcontrol.article.model.request.sortType.CreateSortTypeRequest;
import flowcontrol.article.model.request.sortType.UpdateSortTypeRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class SortTypeMapper extends BaseMapper<SortType, CreateSortTypeRequest, UpdateSortTypeRequest>{
}
