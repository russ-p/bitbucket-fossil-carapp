package ru.penkrat.cartracking.client;

import java.io.Serializable;
import java.util.Date;

import ru.penkrat.cartracking.client.dto.PersonDTO;
import ru.penkrat.cartracking.client.dto.ViolationDTO;

public class Violation implements Serializable {
	private Long id;
	private Person person;
	private Date violationDate;
	private String violationText;

	public Violation() {

	}

	public Violation(ViolationDTO violationDTO) {
		id = violationDTO.getId();
		person = new Person(violationDTO.getPersonDTO());
		violationDate = violationDTO.getViolationDate();
		violationText = violationDTO.getViolationText();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Date getViolationDate() {
		return violationDate;
	}

	public void setViolationDate(Date violationDate) {
		this.violationDate = violationDate;
	}

	public String getViolationText() {
		return violationText;
	}

	public void setViolationText(String violationText) {
		this.violationText = violationText;
	}
}
