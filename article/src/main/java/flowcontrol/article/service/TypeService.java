package flowcontrol.article.service;


import flowcontrol.article.model.entity.Type;
import flowcontrol.article.repository.TypeRepository;
import org.springframework.stereotype.Service;

@Service
public class TypeService extends BaseService<Type> {

    private final TypeRepository typeRepository;

    public TypeService(TypeRepository typeRepository) {
        super(typeRepository);
        this.typeRepository = typeRepository;
    }
}
