package flowcontrol.article.model.mapper;

import flowcontrol.article.model.entity.Group;
import flowcontrol.article.model.request.group.CreateGroupRequest;
import flowcontrol.article.model.request.group.UpdateGroupRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;


@AllArgsConstructor
@Component
public class GroupMapper extends BaseMapper<Group, CreateGroupRequest, UpdateGroupRequest>
{

    @Override
    public Group toEntity(CreateGroupRequest createGroup) {
        var original = new Group();
        if (createGroup.getName() != null) {
            original.setName(createGroup.getName());
        }
        if (createGroup.getDescription() != null) {
            original.setDescription(createGroup.getDescription());
        }
        return original;
    }


    public Group mapUpdatesToOriginal(CreateGroupRequest updateGroup, Group original) {
        if (updateGroup.getName() != null) {
            original.setName(updateGroup.getName());
        }
        if (updateGroup.getDescription() != null) {
            original.setDescription(updateGroup.getDescription());
        }
        return original;
    }
}
