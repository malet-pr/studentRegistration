package com.react.api.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProfessorCompleteDTO {

    private Long professorId;
    private String nationalID;
    private String employeeID;
    private boolean isActive;
    private String firstName;
    private String lastName;
    private List<String> subjectNames;
    private List<String> courseCodes;
    
}
