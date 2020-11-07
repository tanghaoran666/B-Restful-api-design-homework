package com.thoughtworks.capability.gtb.restfulapidesign.bo;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Group {
    private int id;
    private String name;
    private String note;
    private List<Student> students;
}
