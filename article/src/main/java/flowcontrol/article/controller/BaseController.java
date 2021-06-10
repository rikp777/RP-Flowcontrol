package flowcontrol.article.controller;

import com.netflix.discovery.converters.Auto;
import flowcontrol.article.controller.assembler.BaseAssembler;
import flowcontrol.article.exception.ResourceNotFoundException;
import flowcontrol.article.model.entity.Cask;
import flowcontrol.article.model.request.cask.CreateCaskRequest;
import flowcontrol.article.repository.Generic.AbstractBaseEntity;
import flowcontrol.article.service.BaseService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.description.type.TypeDescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class BaseController<R extends RepresentationModel<?>, T extends AbstractBaseEntity> {

    private BaseService<T> service;
    private BaseAssembler<T, R> assembler;

    BaseController(BaseService<T> baseService, BaseAssembler<T,R> baseAssembler){
        service = baseService;
        assembler = baseAssembler;
    }


    //
    @GetMapping(
            path = "/{id}",
            consumes = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE
            }
    ) //READ BY ID
    public ResponseEntity getById(@PathVariable Long id){
        return service.getById(id)
                .map(entity -> ResponseEntity.ok(assembler.toModel(entity)))
                .orElseThrow(() ->
                        new ResourceNotFoundException(getName(), "Id", id)
                );
    }
    @GetMapping()//READ ALL
    public ResponseEntity getAll(){
        return ResponseEntity.ok(assembler.toCollectionModel(service.getAll()));
    }



    public String getName() {
        Class<?> clazz = this.getClass();
        for (;;) {
            Class<?> superClass = clazz.getSuperclass();
            if (superClass == null) {
                throw new IllegalStateException();
            }
            if (superClass == TypeDescription.Generic.class) {
                Type genericSuper = clazz.getGenericSuperclass();
                if (genericSuper instanceof ParameterizedType) {
                    ParameterizedType parameterized = (ParameterizedType)genericSuper;
                    Type[] params = parameterized.getActualTypeArguments();
                    if (params.length == 1) {
                        Type param = params[0];
                        if (param instanceof Class<?>) {
                            return ((Class<?>)param).getName();
                        }
                    }
                }
                throw new IllegalStateException();
            }
            clazz = superClass;
        }
    }
}
