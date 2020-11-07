package com.thoughtworks.capability.gtb.restfulapidesign.bo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {
    private int id;
    @NotNull(message = "name must not be null")
    private String name;
    @NotNull(message = "gender must not be null")
    private String gender;
    private String note;
}
