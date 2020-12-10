package be.sfpd.rest.service;

public class IllegalIdException extends RuntimeException{
	public IllegalIdException(Long id) {
		super("object with id " + id + "does not exist");
	}
}
