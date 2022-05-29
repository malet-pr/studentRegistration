package com.react.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProfessorDTO {

    private Long professorId;
    private String nationalID;
    private String employeeID;
    private boolean isActive;
    private String firstName;
    private String lastName;

}
