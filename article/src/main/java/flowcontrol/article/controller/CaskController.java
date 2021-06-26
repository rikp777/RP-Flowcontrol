package flowcontrol.article.controller;


import flowcontrol.article.controller.assembler.ArticleAssembler;
import flowcontrol.article.controller.assembler.CaskAssembler;
import flowcontrol.article.exception.ResourceNotFoundException;
import flowcontrol.article.model.entity.Cask;
import flowcontrol.article.model.mapper.CaskMapper;
import flowcontrol.article.model.request.cask.CreateCaskRequest;
import flowcontrol.article.model.request.cask.UpdateCaskRequest;
import flowcontrol.article.model.response.CaskResponse;
import flowcontrol.article.service.CaskService;
import lombok.EqualsAndHashCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/v1/casks")
@EqualsAndHashCode(callSuper = true)
public class CaskController extends BaseController<CaskResponse, Cask, CreateCaskRequest, UpdateCaskRequest, CaskMapper>{

    //Assemblers
    private final CaskAssembler caskAssembler;
    private final ArticleAssembler articleAssembler;

    //Mapper
    private final CaskMapper caskMapper;

    //Services
    private final CaskService caskService;



    //Constructor
    @Autowired
    public CaskController(CaskService caskService, CaskAssembler caskAssembler, ArticleAssembler articleAssembler, CaskMapper caskMapper){
        super(caskService, caskAssembler, caskMapper);
        this.caskAssembler = caskAssembler;
        this.articleAssembler = articleAssembler;
        this.caskMapper = caskMapper;
        this.caskService = caskService;
    }



    //region CRUD
    @PostMapping(
            consumes = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    "multipart/form-data"
            }
    )//CREATE
    public ResponseEntity create(@Valid @ModelAttribute("cask") CreateCaskRequest createCask){
        Cask mappedCask = caskMapper.toEntity(createCask);
        return caskService.createOrUpdate(mappedCask)
                .map(cask -> ResponseEntity.ok(caskAssembler.toModel(cask)))
                .orElseThrow(() ->
                        new IllegalArgumentException("Something went wrong")
                );
    }
    @PutMapping(
            path = "/{caskId}",
            consumes = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    "multipart/form-data"
            }
    )//UPDATE
    public ResponseEntity update(@PathVariable UUID caskId, @Valid @ModelAttribute("cask") CreateCaskRequest createCask){
        return caskService.getById(caskId)
                .map(cask -> {
                    Cask mappedCask = caskMapper.mapUpdatesToOriginal(createCask, cask);
                    return caskService.createOrUpdate(mappedCask)
                            .map(updatedCask -> ResponseEntity.ok(caskAssembler.toModel(updatedCask)))
                            .orElseThrow(() ->
                                    new IllegalArgumentException("Something went wrong")
                            );
                })
                .orElseThrow(() ->
                        new ResourceNotFoundException("Casks", "Id", caskId)
                );
    }
    @DeleteMapping(
            path = "/{caskId}",
            consumes = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
            }
    )//DELETE
    public ResponseEntity delete(@PathVariable UUID caskId){
        return caskService.getById(caskId)
                .map(cask -> {
                    caskService.delete(cask);
                    return ResponseEntity.ok("Deleted cask[" + caskId + "]");
                })
                .orElseThrow(() ->
                        new ResourceNotFoundException("Casks", "Id", caskId)
                );
    }
    //endregion


    //region Relations
    @GetMapping("/{caskId}/articles") //READ ALL
    public ResponseEntity getAllBelongingArticles(@PathVariable UUID caskId){
        return caskService.getById(caskId)
                .map(cask -> ResponseEntity.ok(articleAssembler.toCollectionModel(cask.getArticles())))
                .orElseThrow(() ->
                        new ResourceNotFoundException("Casks", "Id", caskId)
                );
    }
    //endregion
}
