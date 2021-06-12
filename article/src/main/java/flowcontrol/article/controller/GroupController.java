package flowcontrol.article.controller;


import flowcontrol.article.controller.assembler.ArticleAssembler;
import flowcontrol.article.controller.assembler.GroupAssembler;
import flowcontrol.article.exception.ResourceNotFoundException;
import flowcontrol.article.model.entity.Group;
import flowcontrol.article.model.mapper.GroupMapper;
import flowcontrol.article.model.request.group.CreateGroupRequest;
import flowcontrol.article.model.request.group.UpdateGroupRequest;
import flowcontrol.article.model.response.GroupResponse;
import flowcontrol.article.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/groups")
public class GroupController extends BaseController<GroupResponse, Group, CreateGroupRequest, UpdateGroupRequest, GroupMapper>{

    //Assemblers
    private final GroupAssembler groupAssembler;
    private final ArticleAssembler articleAssembler;

    //Mappers
    private final GroupMapper groupMapper;

    //Services
    private final GroupService groupService;



    //Constructor
    @Autowired
    GroupController(GroupService groupService, GroupAssembler groupAssembler, GroupMapper groupMapper, ArticleAssembler articleAssembler){
        super(groupService, groupAssembler, groupMapper);
        this.groupService = groupService;
        this.groupAssembler = groupAssembler;
        this.groupMapper = groupMapper;
        this.articleAssembler = articleAssembler;
    }



    //region CRUD
    @PostMapping(
            consumes = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    "multipart/form-data"
            }
    )//CREATE
    public ResponseEntity create(@Valid @ModelAttribute("group") CreateGroupRequest createGroup){
        Group mappedGroup = groupMapper.toEntity(createGroup);
        return groupService.createOrUpdate(mappedGroup)
                .map(cask -> ResponseEntity.ok(groupAssembler.toModel(cask)))
                .orElseThrow(() ->
                        new IllegalArgumentException("Something went wrong")
                );
    }
    @PutMapping(
            path = "/{groupId}",
            consumes = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    "multipart/form-data"
            }
    )//UPDATE
    public ResponseEntity update(@PathVariable String groupId, @Valid @ModelAttribute("group") CreateGroupRequest updateGroup){
        return groupService.getById(Long.parseLong(groupId))
                .map(group -> {
                    Group mappedCask = groupMapper.mapUpdatesToOriginal(updateGroup, group);
                    return groupService.createOrUpdate(mappedCask)
                            .map(updatedGroup -> ResponseEntity.ok(groupAssembler.toModel(updatedGroup)))
                            .orElseThrow(() ->
                                    new IllegalArgumentException("Something went wrong")
                            );
                })
                .orElseThrow(() ->
                        new ResourceNotFoundException("Group", "Id", groupId)
                );
    }
    @DeleteMapping(
            path = "/{groupId}",
            consumes = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
            }
    )//DELETE
    public ResponseEntity delete(@PathVariable String groupId){
        return groupService.getById(Long.parseLong(groupId))
                .map(group -> {
                    groupService.delete(group);
                    return ResponseEntity.ok("Deleted group [" + groupId + "]");
                })
                .orElseThrow(() ->
                        new ResourceNotFoundException("Group", "Id", groupId)
                );
    }
    //endregion

    //region Relations
    @GetMapping("/{groupId}/articles")
    public ResponseEntity getAllBelongingArticles(@PathVariable Long groupId){
        return groupService.getById(groupId)
                .map(color -> ResponseEntity.ok(articleAssembler.toCollectionModel(color.getArticles())))
                .orElseThrow(() ->
                        new ResourceNotFoundException("Group", "Id", groupId)
                );
    }
    //endregion
}
