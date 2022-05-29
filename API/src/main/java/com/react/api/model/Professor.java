package com.react.api.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
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
@Table(name = "professors")
public class Professor implements Serializable {

	private static final long serialVersionUID = 6816901681279803861L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long professorId;
	@NotEmpty(message = "this field cannot be empty")
	@Column(name = "national_id")
	private String nationalID;
	@NotEmpty(message = "this field cannot be empty")
	@Pattern(regexp = "^[A-Z]{2}-[0-9]{3}$", message = "Employee ID should have the form AZ-123")
	@Column(name = "employee_id")
	private String employeeID;
	@Column(name = "is_active")
	private boolean isActive;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;

	@ManyToMany
	@JoinTable(name = "course_to_professor", joinColumns =  @JoinColumn(name = "professor_id"), inverseJoinColumns = @JoinColumn(name = "course_id"))	
	private List<Course> courses;

	@ManyToMany
	@JoinTable(name = "subject_to_professor", joinColumns = @JoinColumn(name = "professor_id"), inverseJoinColumns = @JoinColumn(name = "subject_id"))
	private List<Subject> subjects;

}

