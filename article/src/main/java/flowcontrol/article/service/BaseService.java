package flowcontrol.article.service;

import flowcontrol.article.exception.BadRequestException;
import flowcontrol.article.repository.Generic.AbstractBaseEntity;
import flowcontrol.article.repository.Generic.AbstractBaseRepository;
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
            throw new BadRequestException("Syntax error [" + value + "] is not a number");
        }
    }

    public Iterable<T> getAll(){
        return repository.findAll();
    }

    public Optional<T> getById(Long id){
        return repository.findById(id);
    }

}
