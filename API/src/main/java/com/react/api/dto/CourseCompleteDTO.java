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
public class CourseCompleteDTO {

	private Long courseId;
	private String code;
	private String time;
	private String day;
	private int capacity;
	private String subjectName;
	private List<String> professorNames;
	private int availableSeats;
	
}
