package flowcontrol.article.service;

import flowcontrol.article.exception.BadRequestException;
import flowcontrol.article.repository.generic.AbstractBaseEntity;
import flowcontrol.article.repository.generic.AbstractBaseRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.transaction.Transactional;
import java.util.Optional;

@AllArgsConstructor
@Slf4j
public abstract class BaseService<T extends AbstractBaseEntity> {

    private AbstractBaseRepository<T, Long> repository;

    @Transactional
    public boolean isAlreadyPresentById(String value) throws Exception {
        try {
            return repository.findById(Long.parseLong(value)).isPresent();
        } catch (Exception exception){
            return true;
        }
    }

    public Iterable<T> getAll(){
        return repository.findAll();
    }

    public Optional<T> getById(Long id){
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
