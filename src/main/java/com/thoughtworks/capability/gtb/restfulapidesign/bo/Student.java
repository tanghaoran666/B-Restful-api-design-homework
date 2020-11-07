package com.thoughtworks.capability.gtb.restfulapidesign.bo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Student {
    private int id;
    private String name;
    private String gender;
    private String note;
}
