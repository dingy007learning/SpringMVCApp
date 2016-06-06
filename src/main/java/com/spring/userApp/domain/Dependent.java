package com.spring.userApp.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="dependents")
public class Dependent {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	private int dependentId;
	@Column
	@NotEmpty(message="Please enter your name.")
	@Size(min=3, max=45, message="Name should be between 3 and 45 chars.")
	private String name;
	@Column
	@NotEmpty(message="Please enter your Date Of Birth")
	
	private String dob;
	@Column
	@NotEmpty(message="Please choose the correct gender")
	private String gender;
	
	@Transient
	private String error;
	
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getDependentId() {
		return dependentId;
	}
	public void setDependentId(int dependentId) {
		this.dependentId = dependentId;
	}
	@Override
	public String toString() {
		return String.format("[name=%s, dob=%s, gender=%s]", name, dob, gender);
	}
}
