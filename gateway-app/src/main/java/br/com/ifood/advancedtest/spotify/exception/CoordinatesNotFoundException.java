package br.com.ifood.advancedtest.spotify.exception;

public class CoordinatesNotFoundException extends Exception {
	private static final long serialVersionUID = -1109630245255280448L;

	public CoordinatesNotFoundException(String message) {
		super(message);
	}
}
