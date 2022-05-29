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
public class StudentCompleteDTO {

    private Long StudentId;
    private String nationalID;
    private String firstName;
    private String lastName;
    private List<String> courseCodes;

}