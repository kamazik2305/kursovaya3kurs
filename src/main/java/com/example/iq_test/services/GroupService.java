package com.example.iq_test.services;

import com.example.iq_test.models.ChildGroup;
import com.example.iq_test.repositories.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupService {

    @Autowired
    GroupRepository groupRepository;

    public Iterable<ChildGroup> fillAllGroups()
    {
        return groupRepository.findAll();
    }

    public ChildGroup findGroupById(long idGroup)
    {
        return groupRepository.findById(idGroup).orElseThrow();
    }

    public void addNewGroup(String groupName)
    {
        ChildGroup childGroup = new ChildGroup(groupName);
        groupRepository.save(childGroup);
    }
}
