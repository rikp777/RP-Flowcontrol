package flowcontrol.farmer.model.mapper;

import flowcontrol.farmer.model.entity.BaseEntity;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class BaseMapper<T extends BaseEntity, C, U> {

    public T toEntity(C createEntity){
        try{
            throw new Exception("No to entity implementation");
        }catch (Exception exception){
            log.error(exception.getMessage());
        }
        return null;
    }

    public T mapUpdatesToOriginal(U updateEntity, T original){
        try{
            throw new Exception("No to mapUpdatesToOriginal implementation");
        }catch (Exception exception){
            log.error(exception.getMessage());
        }
        return null;
    }

    public Long toLong(String number){
        try {
            return Long.parseLong(number);
        }catch (NumberFormatException ex){
            throw ex;
        }
    }
}
