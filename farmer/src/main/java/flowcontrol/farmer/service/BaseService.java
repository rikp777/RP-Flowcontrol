package flowcontrol.farmer.service;

import flowcontrol.farmer.exception.BadRequestException;
import flowcontrol.farmer.model.entity.BaseEntity;
import flowcontrol.farmer.repository.AbstractBaseRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Slf4j
public abstract class BaseService<T extends BaseEntity> {

    private AbstractBaseRepository<T, UUID> repository;

    @Transactional
    public boolean isAlreadyPresentById(UUID value){
        try {
            return repository.findById(value).isPresent();
        } catch (Exception exception){
            throw new BadRequestException("Something went wrong");
        }
    }

    public Page<T> getAll(Pageable pageable){
        return repository.findAll(pageable);
    }
    public Iterable<T> getAll(){
        return repository.findAll();
    }

    public Optional<T> getById(UUID id){
        return repository.findById(id);
    }


    public Optional<T> createOrUpdate(T entity){
        return Optional.of(repository.save(entity));
    }

    public void delete(T entity){
        try{
            repository.delete(entity);
        }catch (Exception exception){
            log.error(exception.getMessage());
            throw new BadRequestException("It is possible that the data you want to delete has relational data, Delete that data first!");
        }
    }
}
