package flowcontrol.article.controller;


import flowcontrol.article.controller.assembler.ArticleAssembler;
import flowcontrol.article.controller.assembler.GroupAssembler;
import flowcontrol.article.exception.ResourceNotFoundException;
import flowcontrol.article.model.entity.Group;
import flowcontrol.article.model.mapper.GroupMapper;
import flowcontrol.article.model.request.group.CreateGroupRequest;
import flowcontrol.article.model.request.group.UpdateGroupRequest;
import flowcontrol.article.model.response.ArticleResponse;
import flowcontrol.article.model.response.GroupResponse;
import flowcontrol.article.service.GroupService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/groups")
@SecurityRequirement(name = "bearerAuth")
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


    //region Api Endpoints
    //endregion

    //region Relations
    @GetMapping("/{groupId}/articles")
    public ResponseEntity<CollectionModel<ArticleResponse>> getAllBelongingArticles(@PathVariable UUID groupId){
        return groupService.getById(groupId)
                .map(color -> ResponseEntity.ok(articleAssembler.toCollectionModel(color.getArticles())))
                .orElseThrow(() ->
                        new ResourceNotFoundException("Group", "Id", groupId)
                );
    }
    //endregion
}
