package ru.penkrat.cartracking.client;

import java.util.List;

import ru.penkrat.cartracking.client.dto.CarDTO;
import ru.penkrat.cartracking.client.dto.PersonDTO;
import ru.penkrat.cartracking.client.dto.ViolationDTO;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {
	String greetServer(String name) throws IllegalArgumentException;
	List<PersonDTO> getPersons();
	Long savePerson(PersonDTO personDTO);
	Long saveCar(CarDTO carDTO);
	Long saveViolation(ViolationDTO violationDTO);
}
