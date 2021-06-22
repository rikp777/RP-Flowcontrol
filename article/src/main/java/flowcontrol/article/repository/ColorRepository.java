package flowcontrol.article.repository;

import flowcontrol.article.model.entity.Cask;
import flowcontrol.article.model.entity.Color;
import flowcontrol.article.repository.generic.AbstractBaseRepository;

import java.util.Optional;

public interface ColorRepository  extends AbstractBaseRepository<Color, Long> {
    Optional<Color> findByName(String name);
}
