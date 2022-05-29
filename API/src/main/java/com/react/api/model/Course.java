package com.react.api.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "courses")
public class Course implements Serializable {

	private static final long serialVersionUID = 7368595451301172850L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long courseId;
	@NotEmpty(message = "this field cannot be empty")
	@Pattern(regexp = "^[A-Z]{2}-[0-9]{3}-[0-9]{2}$", message = "the code should have the form AZ-123-01")
	private String code;
	private String time;
	private String day;
	@Column(name = "max_capacity")
	private int capacity;

	@ManyToOne()
	@JoinColumn(name = "subject_id", insertable = false)
	private Subject subject;

	@ManyToMany()
	@JoinTable(name = "course_to_professor", joinColumns = @JoinColumn(name = "course_id"), inverseJoinColumns = @JoinColumn(name = "professor_id"))
	private List<Professor> professors;

	@ManyToMany
	@JoinTable(name = "course_to_student", joinColumns = @JoinColumn(name = "course_id"), inverseJoinColumns = @JoinColumn(name = "student_id"))
	private List<Student> students;

}
