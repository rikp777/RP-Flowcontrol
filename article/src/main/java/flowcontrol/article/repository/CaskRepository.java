package flowcontrol.article.repository;

import flowcontrol.article.model.entity.Cask;
import flowcontrol.article.repository.Generic.AbstractBaseRepository;
import flowcontrol.article.repository.Generic.AbstractBaseRepositoryImpl;
import org.springframework.stereotype.Repository;

@Repository
public interface CaskRepository extends AbstractBaseRepository<Cask, Long> {


}
