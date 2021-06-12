package flowcontrol.article.repository.generic;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.io.Serializable;

@NoRepositoryBean
public interface AbstractBaseRepository<T extends AbstractBaseEntity, I extends Serializable> extends PagingAndSortingRepository<T, I> {
}
