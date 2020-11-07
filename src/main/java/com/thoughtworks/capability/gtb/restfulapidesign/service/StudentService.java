package com.thoughtworks.capability.gtb.restfulapidesign.service;

import com.thoughtworks.capability.gtb.restfulapidesign.bo.Student;
import com.thoughtworks.capability.gtb.restfulapidesign.exception.NotMatchException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class StudentService {
    List<Student> studentRepository = new ArrayList<>();

    int studentId = 10;
    // TODO GTB-知识点: - 应该使用Repository层保存数据
    StudentService() {
        init();
    }

    public void init() {
        studentRepository.removeAll(studentRepository);
        studentRepository.add(Student.builder().name("成吉思汗").gender("male").id(1).note("").build());
        studentRepository.add(Student.builder().name("鲁班七号").gender("male").id(2).note("").build());
        studentRepository.add(Student.builder().name("太乙真人").gender("male").id(3).note("").build());
        studentRepository.add(Student.builder().name("钟无艳").gender("female").id(4).note("").build());
        studentRepository.add(Student.builder().name("花木兰").gender("female").id(5).note("").build());
        studentRepository.add(Student.builder().name("雅典娜").gender("female").id(6).note("").build());
        studentRepository.add(Student.builder().name("芈月").gender("female").id(7).note("").build());
        studentRepository.add(Student.builder().name("白起").gender("male").id(8).note("").build());
        studentRepository.add(Student.builder().name("刘禅").gender("male").id(9).note("").build());
        studentRepository.add(Student.builder().name("庄周").gender("male").id(10).note("").build());
    }

    public List<Student> getStudents(String gender) {
        if (gender == null) {
            return studentRepository;
        } else {
            List<Student> genderStudents = new ArrayList<>();
            for (Student student : studentRepository) {
                if (student.getGender().equals(gender)) genderStudents.add(student);
            }
            return genderStudents;
        }
    }

    public Student createStudent(Student student) {
        student.setId(studentId + 1);
        studentId++;
        studentRepository.add(student);
        return student;
    }

    public void deleteStudent(int id) {
        if (id > studentRepository.size()) {
            throw new NotMatchException("id illegal");
        }
        studentRepository.remove(id - 1);
    }

    public Student getStudent(int id) {
        if (id > studentRepository.size()) {
            throw new NotMatchException("id illegal");
        }
        return studentRepository.get(id - 1);
    }

    public Student updateStudent(int id, Student student) {
        if (id > studentRepository.size()) {
            throw new NotMatchException("id illegal");
        }
        studentRepository.remove(id - 1);
        student.setId(id);
        studentRepository.add(id - 1, student);
        return student;
    }

}

