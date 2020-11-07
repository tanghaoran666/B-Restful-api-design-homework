package com.thoughtworks.capability.gtb.restfulapidesign.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.capability.gtb.restfulapidesign.bo.Student;
import com.thoughtworks.capability.gtb.restfulapidesign.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class StudentControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    StudentService studentService;

    @BeforeEach
    public void setUp() {
        studentService.init();
    }

    @Test
    public void shouldGetStudents() throws Exception {

        mockMvc.perform(get("/students"))
                .andExpect(jsonPath("$[0].name", is("成吉思汗")))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].gender", is("male")))
                .andExpect(jsonPath("$[6].name", is("芈月")))
                .andExpect(jsonPath("$[6].id", is(7)))
                .andExpect(jsonPath("$[6].gender", is("female")))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldCreateStudent() throws Exception {
        Student student = Student.builder().gender("male")
                .name("盲僧")
                .note("")
                .build();
        ObjectMapper objectMapper = new ObjectMapper();
        String studentJson = objectMapper.writeValueAsString(student);
        mockMvc.perform(post("/students").contentType(MediaType.APPLICATION_JSON)
                .content(studentJson))
                .andExpect(status().isCreated());
        assertEquals(11, studentService.getStudents(null).size());
        assertEquals(11, studentService.getStudents(null).get(10).getId());
        assertEquals("盲僧", studentService.getStudents(null).get(10).getName());

    }

    @Test
    public void shouldDivideGroups() throws Exception {
        mockMvc.perform(post("/groups"))
                .andExpect(status().isCreated());
    }


    @Test
    public void should_delete_student() throws Exception {
        mockMvc.perform(delete("/students/4"))
                .andExpect(status().isOk());
        assertEquals(9, studentService.getStudents(null).size());
    }
}
