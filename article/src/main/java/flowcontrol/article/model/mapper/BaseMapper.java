package flowcontrol.article.model.mapper;

public abstract class BaseMapper<T> {

    public T toEntity(){
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
