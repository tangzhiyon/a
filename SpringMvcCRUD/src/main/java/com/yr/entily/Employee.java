package com.yr.entily;

import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;


public class Employee {

	private Integer id;
	@NotEmpty
	private String lastName;
	@Email
	private String email;
	private Integer gender;
	private Department department;
	@Past
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birth;
	
	@Max(value = 50000)
	@Min(value = 10000)
	@NumberFormat(pattern = "#,###,###.#")
	private Float salary;

	private String head;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public Float getSalary() {
		return salary;
	}

	public void setSalary(Float salary) {
		this.salary = salary;
	}

	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}
//	@Override
//	public String toString() {
//		return "Employee [id=" + id + ", lastName=" + lastName + ", email="
//				+ email + ", gender=" + gender + ", department=" + department
//				+ ", birth=" + birth + ", salary=" + salary + "]";
//	}
	

	@Override
	public String toString() {
		return "Employee [id=" + id + ", lastName=" + lastName + ", email=" + email + ", gender=" + gender
				+ ", department=" + department + ", birth=" + birth + ", salary=" + salary + ", head=" + head + "]";
	}

	public Employee(Integer id, String lastName, String email, Integer gender,
			Department department,String head) {
		super();
		this.id = id;
		this.lastName = lastName;
		this.email = email;
		this.gender = gender;
		this.department = department;
		this.head = head;
	}

	public Employee() {
		// TODO Auto-generated constructor stub
	}
}
