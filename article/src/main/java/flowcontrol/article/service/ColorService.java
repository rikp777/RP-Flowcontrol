package flowcontrol.article.service;

import flowcontrol.article.model.entity.Color;
import flowcontrol.article.repository.ColorRepository;
import org.springframework.stereotype.Service;

@Service
public class ColorService extends BaseService<Color> {

    private final ColorRepository colorRepository;

    public ColorService(ColorRepository colorRepository) {
        super(colorRepository);
        this.colorRepository = colorRepository;
    }
}
