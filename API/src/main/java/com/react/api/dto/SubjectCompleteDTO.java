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
public class SubjectCompleteDTO {

	private Long SubjectId;
	private String name;
	private String description;
	private List<String> courseCodes;
	private List<String> professorNames;

}
