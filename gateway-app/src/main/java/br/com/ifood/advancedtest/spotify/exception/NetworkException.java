package br.com.ifood.advancedtest.spotify.exception;

public class NetworkException extends Exception {
	private static final long serialVersionUID = -1109630245255280448L;

	public NetworkException(String message) {
		super(message);
	}

	public NetworkException(Exception ex) {
		super(ex);
	}
}
