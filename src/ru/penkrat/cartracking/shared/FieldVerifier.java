package ru.penkrat.cartracking.shared;

public class FieldVerifier {

	/**
	 * Verifies that the specified name is valid for our service.
	 * 
	 * @param name the name to validate
	 * @return true if valid, false if invalid
	 */
	public static boolean isValidName(String name) {
		if (name == null) {
			return false;
		}
		if (name.length() < 2){
			return false;
		};
		return name.matches("\\D+$");
	}
	
	
	
	public static boolean isValidVinNumber(String name) {
		if (name == null) {
			return false;
		}
		return (name.length() == 27);
	}
}
