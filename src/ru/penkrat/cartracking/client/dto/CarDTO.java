package ru.penkrat.cartracking.client.dto;

import java.io.Serializable;

public class CarDTO  implements Serializable {
	private Long id;
	private String model;
	private String year;
	private String color;
	private Double distance;
	private Double engineLiters;
	private String vinNumber;
	private String registrationPlate;
	
	public CarDTO() {
	  id = 0L;
	}

	public CarDTO(Long id, String model, String year, String color,
			Double distance, Double engineLiters, String vinNumber,
			String registrationPlate) {
		super();
		this.id = id;
		this.model = model;
		this.year = year;
		this.color = color;
		this.distance = distance;
		this.engineLiters = engineLiters;
		this.vinNumber = vinNumber;
		this.registrationPlate = registrationPlate;
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
	
	public String toString(){
		return model +" "+ registrationPlate + " "+id;
		
	}
}
