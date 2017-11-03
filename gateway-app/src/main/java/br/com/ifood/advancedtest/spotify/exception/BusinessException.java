package br.com.ifood.advancedtest.spotify.exception;

public class BusinessException extends Exception {
	private static final long serialVersionUID = -1109630245255280448L;

	public BusinessException(String message) {
		super(message);
	}

	public BusinessException(Exception ex) {
		super(ex);
	}
}
