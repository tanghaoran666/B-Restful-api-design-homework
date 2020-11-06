package com.thoughtworks.capability.gtb.restfulapidesign.service;

import com.thoughtworks.capability.gtb.restfulapidesign.bo.Group;
import com.thoughtworks.capability.gtb.restfulapidesign.bo.Student;
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

    // TODO GTB-知识点: - 应该使用Repository层保存数据
    StudentService() {
        init();
    }

    public void init() {
        students.removeAll(students);
        students.add(new Student(1, "成吉思汗"));
        students.add(new Student(2, "鲁班七号"));
        students.add(new Student(3, "太乙真人"));
        students.add(new Student(4, "钟无艳"));
        students.add(new Student(5, "花木兰"));
        students.add(new Student(6, "雅典娜"));
        students.add(new Student(7, "芈月"));
        students.add(new Student(8, "白起"));
        students.add(new Student(9, "刘禅"));
        students.add(new Student(10, "庄周"));
        students.add(new Student(11, "马超"));
        students.add(new Student(12, "刘备"));
        students.add(new Student(13, "哪吒"));
        students.add(new Student(14, "大乔"));
        students.add(new Student(15, "蔡文姬"));
    }

    public List<Student> getStudents() {
        return students;
    }

    public void createStudent(String name) {
        // TODO GTB-工程实践: - 计算id的方式不够健壮，可以使用字段保存最大id
        Student student = new Student(students.size() + 1, name);
        students.add(student);
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
        // TODO GTB-工程实践: - 注意代码风格，需要适当的添加空格
        for (int i = 1; i < 7; i++) {
            groups.add(new Group(i, groupMap.get(i)));
        }

    }

    public List<Group> getGroups() {
        return groups;

    }

    public void deleteStudent(int id) {
        students.remove(id - 1);
    }
}

