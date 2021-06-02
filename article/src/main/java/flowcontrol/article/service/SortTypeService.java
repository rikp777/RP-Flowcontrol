package flowcontrol.article.service;

import flowcontrol.article.model.entity.SortType;
import flowcontrol.article.repository.SortTypeRepository;
import org.springframework.stereotype.Service;

@Service
public class SortTypeService extends BaseService<SortType> {

    private final SortTypeRepository sortTypeRepository;

    public SortTypeService(SortTypeRepository sortTypeRepository) {
        super(sortTypeRepository);
        this.sortTypeRepository = sortTypeRepository;
    }
}
