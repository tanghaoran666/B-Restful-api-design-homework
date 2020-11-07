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

    List<Group> groupRepository = new ArrayList<>();

    public void divideGroups() {
        groupInit();
        List<Student> randomStudents = new ArrayList<>(studentService.studentRepository);
        Collections.shuffle(randomStudents);
        divideRandomStudents(randomStudents);
    }

    private void divideRandomStudents(List<Student> randomStudents) {
        int groupIndex = 1;
        for (Student randomStudent : randomStudents) {
            groupRepository.get(groupIndex - 1).getStudents().add(randomStudent);
            groupIndex++;
            if (groupIndex == 7) groupIndex = 1;
        }
    }

    private void groupInit() {
        groupRepository.clear();
        for (int i = 1; i <= GROUP_SIZE; i++) {
            groupRepository.add(Group.builder()
                    .id(i).name(i + "组").note("").students(new ArrayList<>()).build());
        }
    }

    public List<Group> getGroups() {
        return groupRepository;
    }

    public Group updateGroup(int id, String name) {
        Group group = groupRepository.get(id - 1);
        group.setName(name);
        return group;
    }
}
