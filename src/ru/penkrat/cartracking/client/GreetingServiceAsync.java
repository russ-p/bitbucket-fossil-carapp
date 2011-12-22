package ru.penkrat.cartracking.client;

import java.util.List;

import ru.penkrat.cartracking.client.dto.CarDTO;
import ru.penkrat.cartracking.client.dto.PersonDTO;
import ru.penkrat.cartracking.client.dto.ViolationDTO;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {
	void greetServer(String input, AsyncCallback<String> callback)
			throws IllegalArgumentException;

	void getPersons(AsyncCallback<List<PersonDTO>> callback);
	void savePerson(PersonDTO personDTO, AsyncCallback<Long> callback);
	void saveCar(CarDTO carDTO, AsyncCallback<Long> callback);
	void saveViolation(ViolationDTO violationDTO, AsyncCallback<Long> callback);
}
