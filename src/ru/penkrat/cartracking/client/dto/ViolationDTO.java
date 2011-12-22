package ru.penkrat.cartracking.client.dto;

import java.io.Serializable;
import java.util.Date;

public class ViolationDTO implements Serializable {
	private Long id;
	private PersonDTO personDTO;
	private Date violationDate;
	private String violationText;

	public ViolationDTO() {
	}

	public ViolationDTO(Long id, PersonDTO personDTO, Date violationDate,
			String violationText) {
		super();
		this.id = id;
		this.personDTO = personDTO;
		this.violationDate = violationDate;
		this.violationText = violationText;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PersonDTO getPersonDTO() {
		return personDTO;
	}

	public void setPersonDTO(PersonDTO personDTO) {
		this.personDTO = personDTO;
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
