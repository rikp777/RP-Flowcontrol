package flowcontrol.article.service;

import flowcontrol.article.model.entity.Inset;
import flowcontrol.article.repository.InsetRepository;
import org.springframework.stereotype.Service;

@Service
public class InsetService extends BaseService<Inset> {

    private final InsetRepository insetRepository;

    public InsetService(InsetRepository insetRepository) {
        super(insetRepository);
        this.insetRepository = insetRepository;
    }
}
