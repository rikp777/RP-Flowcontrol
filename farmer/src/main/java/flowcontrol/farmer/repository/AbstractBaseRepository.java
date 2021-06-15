package flowcontrol.farmer.repository;

import flowcontrol.farmer.model.entity.BaseEntity;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.io.Serializable;

@NoRepositoryBean
public interface AbstractBaseRepository<T extends BaseEntity, I extends Serializable> extends PagingAndSortingRepository<T, I>
{
}
