package flowcontrol.article.service;


import flowcontrol.article.model.entity.Cask;
import flowcontrol.article.repository.CaskRepository;
import org.springframework.stereotype.Service;

@Service
public class CaskService extends BaseService<Cask> {

    private final CaskRepository caskRepository;

    public CaskService(CaskRepository caskRepository) {
        super(caskRepository);
        this.caskRepository = caskRepository;
    }
}
