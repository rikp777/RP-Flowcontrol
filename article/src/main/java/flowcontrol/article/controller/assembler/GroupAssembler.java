package flowcontrol.article.controller.assembler;


import flowcontrol.article.controller.GroupController;
import flowcontrol.article.model.entity.Group;
import flowcontrol.article.model.response.GroupResponse;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class GroupAssembler extends BaseAssembler<Group, GroupResponse> {

    @Override
    public GroupResponse toModel(Group group) {
        GroupResponse response = GroupResponse.builder()
                .name(group.getName())
                .description(group.getDescription())
                .build();

        response
                .add(linkTo(methodOn(GroupController.class)
                        .getById(group.getId()))
                        .withSelfRel());

        return response;
    }
}
