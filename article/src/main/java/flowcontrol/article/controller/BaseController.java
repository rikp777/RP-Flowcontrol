package flowcontrol.article.controller;

import flowcontrol.article.controller.assembler.BaseAssembler;
import flowcontrol.article.exception.ResourceNotFoundException;
import flowcontrol.article.model.mapper.BaseMapper;
import flowcontrol.article.repository.generic.AbstractBaseEntity;
import flowcontrol.article.service.BaseService;
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
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class BaseController<R extends RepresentationModel<?>, T extends AbstractBaseEntity, C, U, M extends BaseMapper<T, C, U>> {

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


    //region CRUD
    @GetMapping("/{id}") //READ BY ID
    @PreAuthorize("hasRole('ADMIN') || hasRole('PLANNING') || hasRole('ICT') || hasRole('USER') ")
    public ResponseEntity<R> getById(@PathVariable Long id){
        return service.getById(id)
                .map(entity -> ResponseEntity.ok(assembler.toModel(entity)))
                .orElseThrow(() ->
                        new ResourceNotFoundException(getName(), "Id", id)
                );
    }



    @GetMapping()//READ ALL
    @PreAuthorize("hasRole('ADMIN') || hasRole('PLANNING') || hasRole('ICT')")
    public CollectionModel<R> getAll(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "3") @Max(7) Integer size,
            @RequestParam(required = false) String[] sort,
            @RequestParam(required = false, defaultValue = "asc") String dir
    ){
        PageRequest pageRequest;
        Sort.Direction direction;
        if(sort == null) {
            pageRequest = PageRequest.of(page, size);
        }
        else {
            if(dir.equalsIgnoreCase("asc")) direction = Sort.Direction.ASC;
            else direction = Sort.Direction.DESC;
            pageRequest = PageRequest.of(page, size, Sort.by(direction, sort));
        }

        Page<T> entities = service.getAll(pageRequest);
        if(! CollectionUtils.isEmpty(entities.getContent())) return pagedResourcesAssembler.toModel(entities, assembler);
        return null;
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
    public ResponseEntity<R> update(@PathVariable Long id, @Valid @ModelAttribute("article") U updateEntity){
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
