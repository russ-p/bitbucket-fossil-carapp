package ru.penkrat.cartracking.client.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

public class PersonDTO implements Serializable{
	private Long id;
	private String name;
	private String surname;
	private String middlename;
	private int sex;
	private Date DOB;
	private Set<CarDTO> cars;
	
	public PersonDTO() {
	}
	
	public PersonDTO(Long id, String name, String surname, String middlename,
			int sex, Date dOB, Set<CarDTO> cars) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.middlename = middlename;
		this.sex = sex;
		DOB = dOB;
		this.cars = cars;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getMiddlename() {
		return middlename;
	}
	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public Date getDOB() {
		return DOB;
	}
	public void setDOB(Date dOB) {
		DOB = dOB;
	}
	public Set<CarDTO> getCars() {
		return cars;
	}
	public void setCars(Set<CarDTO> cars) {
		this.cars = cars;
	}
	
	public String getFullName(){
		return surname +" "+ name;
	}
	public String toString(){
		return getFullName() + " id #"+id;
		
	}
}
