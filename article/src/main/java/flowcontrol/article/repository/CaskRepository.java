package flowcontrol.article.repository;

import flowcontrol.article.model.entity.Cask;
import flowcontrol.article.repository.generic.AbstractBaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CaskRepository extends AbstractBaseRepository<Cask, Long> {


}
