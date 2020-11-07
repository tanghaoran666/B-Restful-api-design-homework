package com.thoughtworks.capability.gtb.restfulapidesign.service;

import com.thoughtworks.capability.gtb.restfulapidesign.bo.Group;
import com.thoughtworks.capability.gtb.restfulapidesign.bo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class GroupService {
    private static final int GROUP_SIZE = 6;
    @Autowired
    StudentService studentService;

    List<Group> groups = new ArrayList<>();
    // TODO GTB-工程实践: - 长方法，建议抽子方法来提高可读性

    public void divideGroups() {
        groups.clear();
        for (int i = 1; i <= GROUP_SIZE; i++) {
            groups.add(Group.builder()
                    .id(i).name(i + "组").note("").students(new ArrayList<>()).build());
        }
        List<Student> randomStudents = new ArrayList<>(studentService.students);
        Collections.shuffle(randomStudents);
        int groupIndex = 1;
        for (Student randomStudent : randomStudents) {
            groups.get(groupIndex - 1).getStudents().add(randomStudent);
            groupIndex++;
            if (groupIndex == 7) groupIndex = 1;
        }
    }

    public List<Group> getGroups() {
        return groups;
    }

    public Group updateGroup(int id, String name) {
        Group group = groups.get(id - 1);
        group.setName(name);
        return group;
    }
}
