package ru.penkrat.cartracking.client;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import ru.penkrat.cartracking.client.dto.CarDTO;

public class Car implements Serializable {
	private Long id;
	private String model;
	private String year;
	private String color;
	private Double distance;
	private Double engineLiters;
	private String vinNumber;
	private String registrationPlate;
	private Set<Person> persons;

	public Car() {

	}

	public Car(CarDTO carDTO) {
		id = carDTO.getId();
		model = carDTO.getModel();
		year = carDTO.getYear();
		color = carDTO.getColor();
		distance = carDTO.getDistance();
		engineLiters = carDTO.getEngineLiters();
		vinNumber = carDTO.getVinNumber();
		registrationPlate = carDTO.getRegistrationPlate();
	}

	public Car(Long id) {
		this.id = id;
	}

	public void addPerson(Person person) {
		if (persons == null) {
			persons = new HashSet<Person>();
		}
		persons.add(person);
	}

	public void removePerson(Person person) {
		if (persons == null) {
			return;
		}
		persons.remove(person);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	public Double getEngineLiters() {
		return engineLiters;
	}

	public void setEngineLiters(Double engineLiters) {
		this.engineLiters = engineLiters;
	}

	public String getVinNumber() {
		return vinNumber;
	}

	public void setVinNumber(String vinNumber) {
		this.vinNumber = vinNumber;
	}

	public String getRegistrationPlate() {
		return registrationPlate;
	}

	public void setRegistrationPlate(String registrationPlate) {
		this.registrationPlate = registrationPlate;
	}
}
