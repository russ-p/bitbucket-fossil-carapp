package ru.penkrat.cartracking.client;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import ru.penkrat.cartracking.client.dto.CarDTO;
import ru.penkrat.cartracking.client.dto.PersonDTO;

public class Person  implements Serializable {
	private Long id;
	private String name;
	private String surname;
	private String middlename;
	private int sex;
	private Date DOB;
	private Set<Car> cars;

	public Person(){
	}
	
	public Person(PersonDTO personDTO){
		id = personDTO.getId();
		name = personDTO.getName();
		surname = personDTO.getSurname();
		middlename = personDTO.getMiddlename();
		sex = personDTO.getSex();
		DOB = personDTO.getDOB();
		Set<CarDTO> carDTOs = personDTO.getCars();
		  if (carDTOs != null) {
		    Set<Car> cars = new HashSet<Car>(carDTOs.size());
		    for (CarDTO carDTO : carDTOs) {
		      cars.add(new Car(carDTO));
		    }
		    this.cars = cars;
		  }
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

	public void setSex(int i) {
		this.sex = i;
	}

	public Date getDOB() {
		return DOB;
	}

	public void setDOB(Date dOB) {
		DOB = dOB;
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
	
	public Set<Car> getCars() {
		return cars;
	}

	public void setCars(Set<Car> cars) {
		this.cars = cars;
	}

}
