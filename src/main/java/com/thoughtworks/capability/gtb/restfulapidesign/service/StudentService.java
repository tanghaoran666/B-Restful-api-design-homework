package com.thoughtworks.capability.gtb.restfulapidesign.service;

import com.thoughtworks.capability.gtb.restfulapidesign.bo.Group;
import com.thoughtworks.capability.gtb.restfulapidesign.bo.Student;
import com.thoughtworks.capability.gtb.restfulapidesign.exception.NotMatchException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class StudentService {
    List<Student> students = new ArrayList<>();
    List<Group> groups = new ArrayList<>();
    int studentId = 10;
    // TODO GTB-知识点: - 应该使用Repository层保存数据
    StudentService() {
        init();
    }

    public void init() {
        students.removeAll(students);
        students.add(Student.builder().name("成吉思汗").gender("male").id(1).note("").build());
        students.add(Student.builder().name("鲁班七号").gender("male").id(2).note("").build());
        students.add(Student.builder().name("太乙真人").gender("male").id(3).note("").build());
        students.add(Student.builder().name("钟无艳").gender("female").id(4).note("").build());
        students.add(Student.builder().name("花木兰").gender("female").id(5).note("").build());
        students.add(Student.builder().name("雅典娜").gender("female").id(6).note("").build());
        students.add(Student.builder().name("芈月").gender("female").id(7).note("").build());
        students.add(Student.builder().name("白起").gender("male").id(8).note("").build());
        students.add(Student.builder().name("刘禅").gender("male").id(9).note("").build());
        students.add(Student.builder().name("庄周").gender("male").id(10).note("").build());
    }

    public List<Student> getStudents(String gender) {
        if (gender == null) {
            return students;
        } else {
            List<Student> genderStudents = new ArrayList<>();
            for (Student student : students) {
                if (student.getGender().equals(gender)) genderStudents.add(student);
            }
            return genderStudents;
        }
    }

    public void createStudent(Student student) {
        student.setId(studentId + 1);
        studentId++;
        students.add(student);
    }

    public void deleteStudent(int id) {
        if (id > students.size()) {
            throw new NotMatchException("id illegal");
        }
        students.remove(id - 1);
    }

    public Student getStudent(int id) {
        if (id > students.size()) {
            throw new NotMatchException("id illegal");
        }
        return students.get(id - 1);
    }

    public Student updateStudent(int id, Student student) {
        if (id > students.size()) {
            throw new NotMatchException("id illegal");
        }
        students.remove(id - 1);
        student.setId(id);
        students.add(id - 1, student);
        return student;
    }
    // TODO GTB-工程实践: - 长方法，建议抽子方法来提高可读性

    public void divideGroups() {
        groups.clear();
        // TODO GTB-工程实践: - 为什么不直接使用groups来分组？
        Map<Integer, List<Student>> groupMap = new HashMap<>();
        // TODO GTB-工程实践: - Magic Number
        for (int i = 1; i <= 6; i++) {
            groupMap.put(i, new ArrayList<>());
        }
        List<Student> randomLists = new ArrayList<>(students);
        Collections.shuffle(randomLists);
        int groupIndex = 1;
        for (Student randomList : randomLists) {
            groupMap.get(groupIndex).add(randomList);
            groupIndex++;
            if (groupIndex == 7) groupIndex = 1;
        }
        for (int i = 1; i < 7; i++) {
            groups.add(Group.builder().id(i).name(i + "组").students(groupMap.get(i)).note("").build());
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

