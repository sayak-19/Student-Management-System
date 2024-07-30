package com.studentms.model;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "students")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_student")
	@GenericGenerator(name = "seq_student", strategy = "increment")
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "first_name", nullable = false)
	@NotBlank(message = "{student.firstName.notBlank}")
	@Size(max = 50, message = "{student.firstName.maxSize}")
	private String firstName;

	@Column(name = "last_name", nullable = false)
	@NotBlank(message = "{student.lastName.notBlank}")
	@Size(max = 50, message = "{student.lastName.maxSsize}")
	private String lastName;

	@Column(name = "date_of_birth", nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "{student.dateOfBirth.notNull}")
	@Past(message = "{student.dateOfBirth.past}")
	private LocalDate dateOfBirth;

	@Column(name = "email", nullable = false)
	@NotBlank(message = "{student.email.notBlank}")
	@Size(max = 254, message = "{student.email.maxSize}")
	@Email(message = "{student.email.notValid}", regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")
	private String email;

	@Column(name = "index_number", nullable = false)
	@NotNull(message = "{student.indexNumber.notNull}")
	@Min(value = 1, message = "{student.indexNumber.maxSize}")
	@Max(value = 99999999, message = "{student.indexNumber.maxSize}")
	private Integer indexNumber;

	@Column(name = "is_on_budget", nullable = false)
	@NotNull(message = "{student.isOnBudget.notNull}")
	private Boolean isOnBudget;

	@OneToMany(mappedBy = "student")
	@JsonIgnore
	private List<Project> projects;

	public Student() {
	}

	public Student(Long id, String firstName, String lastName, LocalDate dateOfBirth, String email, Integer indexNumber,
			Boolean isOnBudget) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.email = email;
		this.indexNumber = indexNumber;
		this.isOnBudget = isOnBudget;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getIndexNumber() {
		return indexNumber;
	}

	public void setIndexNumber(Integer indexNumber) {
		this.indexNumber = indexNumber;
	}

	public Boolean getIsOnBudget() {
		return isOnBudget;
	}

	public void setIsOnBudget(Boolean isOnBudget) {
		this.isOnBudget = isOnBudget;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	@Override
	public String toString() {
		return "ID: " + id + "\nFirst name: " + firstName + "\nLast name: " + lastName + "\nDate of Birth: "
				+ dateOfBirth + "\nIndex number: " + indexNumber + "\nBudget financed: " + isOnBudget;
	}

}