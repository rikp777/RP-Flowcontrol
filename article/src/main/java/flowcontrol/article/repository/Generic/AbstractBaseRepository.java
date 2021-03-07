package flowcontrol.article.repository.Generic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.io.Serializable;

@NoRepositoryBean
public interface AbstractBaseRepository<T extends AbstractBaseEntity, ID extends Serializable> extends PagingAndSortingRepository<T, ID> {
}
