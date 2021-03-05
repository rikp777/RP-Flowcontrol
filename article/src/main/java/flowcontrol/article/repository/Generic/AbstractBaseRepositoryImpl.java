package flowcontrol.article.repository.Generic;


import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import java.io.Serializable;


public abstract class AbstractBaseRepositoryImpl<T extends AbstractBaseEntity, ID extends Serializable> implements AbstractBaseRepository<T, ID> {

}
