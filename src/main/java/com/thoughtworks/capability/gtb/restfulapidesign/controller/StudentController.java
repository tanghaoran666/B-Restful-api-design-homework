package com.thoughtworks.capability.gtb.restfulapidesign.controller;

import com.thoughtworks.capability.gtb.restfulapidesign.bo.Group;
import com.thoughtworks.capability.gtb.restfulapidesign.bo.Student;
import com.thoughtworks.capability.gtb.restfulapidesign.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:1234")
public class StudentController {

    // TODO GTB-知识点: - 推荐使用构造器注入
    @Autowired
    StudentService studentService;

    @GetMapping("/students")
    public ResponseEntity<List<Student>> getStudents(@RequestParam(value = "gender", required = false) String gender) {
        List<Student> students = studentService.getStudents(gender);
        return ResponseEntity.ok(students);
    }

    @PostMapping("/students")
    public ResponseEntity createStudent(@RequestBody Student student) {
        studentService.createStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity deleteStudent(@PathVariable int id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/groups")
    public ResponseEntity<List<Group>> getGroups() {
        List<Group> groups = studentService.getGroups();
        return ResponseEntity.ok(groups);
    }

    @PostMapping("/groups")
    public ResponseEntity<List<Group>> divideGroups() {
        studentService.divideGroups();
        List<Group> groups = studentService.getGroups();
        return ResponseEntity.status(HttpStatus.CREATED).body(groups);
    }

}

