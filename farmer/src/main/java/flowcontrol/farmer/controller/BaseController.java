package flowcontrol.farmer.controller;

import flowcontrol.farmer.controller.assembler.BaseAssembler;
import flowcontrol.farmer.exception.ResourceNotFoundException;
import flowcontrol.farmer.model.entity.BaseEntity;
import flowcontrol.farmer.model.mapper.BaseMapper;
import flowcontrol.farmer.service.BaseService;
import net.bytebuddy.description.type.TypeDescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.UUID;

public abstract class BaseController<R extends RepresentationModel<?>, T extends BaseEntity, C, U, M extends BaseMapper<T, C, U>> {

    private BaseService<T> service;
    private BaseAssembler<T, R> assembler;
    private BaseMapper<T, C, U> mapper;

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private PagedResourcesAssembler<T> pagedResourcesAssembler;

    BaseController(BaseService<T> baseService, BaseAssembler<T,R> baseAssembler, BaseMapper<T, C, U> baseMapper){
        service = baseService;
        assembler = baseAssembler;
        mapper = baseMapper;
    }


    @PostMapping(
            consumes = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    "multipart/form-data"
            }
    )//CREATE
    @PreAuthorize("hasRole('ADMIN') || hasRole('PLANNING') || hasRole('ICT')")
    public ResponseEntity<R> create(@Valid C createRequest){
        var mappedEntity = mapper.toEntity(createRequest);
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
    @PreAuthorize("hasRole('ADMIN') || hasRole('PLANNING') || hasRole('ICT')")
    public ResponseEntity<R> update(@PathVariable UUID id, @Valid @ModelAttribute("article") U updateEntity){
        return service.getById(id)
                .map(entity -> {
                    var mappedEntity = mapper.mapUpdatesToOriginal(updateEntity, entity);
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
    @PreAuthorize("hasRole('ADMIN') || hasRole('PLANNING') || hasRole('ICT')")
    public ResponseEntity<String> delete(@PathVariable UUID id){
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
