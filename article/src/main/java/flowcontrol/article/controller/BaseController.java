package flowcontrol.article.controller;

import flowcontrol.article.controller.assembler.BaseAssembler;
import flowcontrol.article.exception.ResourceNotFoundException;
import flowcontrol.article.model.entity.Article;
import flowcontrol.article.model.mapper.BaseMapper;
import flowcontrol.article.model.request.article.UpdateArticleRequest;
import flowcontrol.article.model.response.ArticleResponse;
import flowcontrol.article.repository.generic.AbstractBaseEntity;
import flowcontrol.article.service.BaseService;
import net.bytebuddy.description.type.TypeDescription;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class BaseController<R extends RepresentationModel<?>, T extends AbstractBaseEntity, C, U, M extends BaseMapper<T, C, U>> {

    private BaseService<T> service;
    private BaseAssembler<T, R> assembler;
    private BaseMapper<T, C, U> mapper;

    BaseController(BaseService<T> baseService, BaseAssembler<T,R> baseAssembler, BaseMapper<T, C, U> baseMapper){
        service = baseService;
        assembler = baseAssembler;
        mapper = baseMapper;
    }


    //region CRUD
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




    @PostMapping(
        consumes = {
                MediaType.APPLICATION_JSON_VALUE,
                MediaType.APPLICATION_XML_VALUE,
                "multipart/form-data"
        }
    )//CREATE
    public ResponseEntity<R> create(@Valid C createRequest){
        T mappedEntity = mapper.toEntity(createRequest);
        return service.createOrUpdate(mappedEntity)
                .map(entity -> ResponseEntity.ok(assembler.toModel(entity)))
                .orElseThrow(() ->
                        new IllegalArgumentException("Something went wrong")
                );
    }



    @PutMapping(
            path = "/{id}",
            consumes = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    "multipart/form-data"
            }
    ) //UPDATE
    public ResponseEntity<R> update(@PathVariable Long id, @Valid @ModelAttribute("article") U updateEntity){
        return service.getById(id)
                .map(entity -> {
                    T mappedEntity = mapper.mapUpdatesToOriginal(updateEntity, entity);
                    return service.createOrUpdate(mappedEntity)
                            .map(updatedEntity -> ResponseEntity.ok(assembler.toModel(updatedEntity)))
                            .orElseThrow(() ->
                                    new IllegalArgumentException("Something went wrong")
                            );
                })
                .orElseThrow(() ->
                        new ResourceNotFoundException(getName(), "Id", id)
                );
    }



    @DeleteMapping(
            path = "/{id}",
            consumes = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
            }
    )//DELETE
    public ResponseEntity<String> delete(@PathVariable Long id){
        return service.getById(id)
                .map(entity -> {
                    service.delete(entity);
                    return ResponseEntity.ok("Deleted entity [" + id + "]");
                })
                .orElseThrow(() ->
                        new ResourceNotFoundException(getName(), "Id", id)
                );
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
