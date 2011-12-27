package ru.penkrat.cartracking.server;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import ru.penkrat.cartracking.client.Car;
import ru.penkrat.cartracking.client.GreetingService;
import ru.penkrat.cartracking.client.Person;
import ru.penkrat.cartracking.client.Violation;
import ru.penkrat.cartracking.client.dto.CarDTO;
import ru.penkrat.cartracking.client.dto.PersonDTO;
import ru.penkrat.cartracking.client.dto.ViolationDTO;
import ru.penkrat.cartracking.shared.FieldVerifier;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements
		GreetingService {

	public String greetServer(String input) throws IllegalArgumentException {
		// Verify that the input is valid.
		if (!FieldVerifier.isValidName(input)) {
			// If the input is not valid, throw an IllegalArgumentException back
			// to
			// the client.
			throw new IllegalArgumentException(
					"Name must be at least 4 characters long");
		}

		String serverInfo = getServletContext().getServerInfo();
		String userAgent = getThreadLocalRequest().getHeader("User-Agent");

		// Escape data from the client to avoid cross-site script
		// vulnerabilities.
		input = escapeHtml(input);
		userAgent = escapeHtml(userAgent);

		return "Hello, " + input + "!<br><br>I am running " + serverInfo
				+ ".<br><br>It looks like you are using:<br>" + userAgent;
	}

	/**
	 * Escape an html string. Escaping data received from the client helps to
	 * prevent cross-site script vulnerabilities.
	 * 
	 * @param html
	 *            the html string to escape
	 * @return the escaped string
	 */
	private String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;")
				.replaceAll(">", "&gt;");
	}

	public List<PersonDTO> getPersons() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<Person> records = new ArrayList<Person>(session.createQuery(
				"from Person").list());
		List<PersonDTO> personsDTOs = new ArrayList<PersonDTO>(
				records != null ? records.size() : 0);
		if (records != null) {
			for (Person person : records) {
				personsDTOs.add(new PersonDTO(person.getId(), person.getName(),
						person.getSurname(), person.getMiddlename(), person
								.getSex(), person.getDOB(), null));
			}
		}
		session.getTransaction().commit();
		return personsDTOs;
	}

	public List<CarDTO> getCars() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    session.beginTransaction();
	    List<Car> records = new ArrayList<Car>(session.createQuery("from Car").list());
	    List<CarDTO> carsDTOs = new ArrayList<CarDTO>(
	    records != null ? records.size() : 0);
	    if (records != null) {
	      for (Car car : records) {
	    	  carsDTOs.add(new CarDTO(
	    			  car.getId(),
	    			  car.getModel(),
	    			  car.getYear(),
	    			  car.getColor(),
	    			  car.getDistance(),
	    			  car.getEngineLiters(),
	    			  car.getVinNumber(),
	    			  car.getRegistrationPlate()
	    			  ));
	      }
	    }
	    session.getTransaction().commit();
	    return carsDTOs;
	}

	public Long savePerson(PersonDTO personDTO) {
		Person person = new Person(personDTO);
		System.out.println("PERSON_ID = " + person.getId());
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.saveOrUpdate(person);
		session.getTransaction().commit();
		return person.getId();
	}

	public Long saveCar(CarDTO carDTO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Car car = new Car(carDTO);
		session.saveOrUpdate(car);
		session.getTransaction().commit();
		return car.getId();
	}

	public Long saveViolation(ViolationDTO violationDTO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Violation violation = new Violation(violationDTO);
		session.saveOrUpdate(violation);
		session.getTransaction().commit();
		return violation.getId();
	}
}
