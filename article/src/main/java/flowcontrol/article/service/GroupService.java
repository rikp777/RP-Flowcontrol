package flowcontrol.article.service;

import flowcontrol.article.model.entity.Color;
import flowcontrol.article.model.entity.Group;
import flowcontrol.article.repository.ColorRepository;
import flowcontrol.article.repository.GroupRepository;
import org.springframework.stereotype.Service;

@Service
public class GroupService extends BaseService<Group> {

    private final GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository) {
        super(groupRepository);
        this.groupRepository = groupRepository;
    }
}
