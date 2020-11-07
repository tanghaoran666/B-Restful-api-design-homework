package com.thoughtworks.capability.gtb.restfulapidesign.service;

import com.thoughtworks.capability.gtb.restfulapidesign.bo.Student;
import com.thoughtworks.capability.gtb.restfulapidesign.exception.NotMatchException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class StudentService {
    List<Student> students = new ArrayList<>();

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

    public Student createStudent(Student student) {
        student.setId(studentId + 1);
        studentId++;
        students.add(student);
        return student;
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

}

