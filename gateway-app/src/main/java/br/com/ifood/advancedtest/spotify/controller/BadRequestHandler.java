package br.com.ifood.advancedtest.spotify.controller;

import br.com.ifood.advancedtest.spotify.domain.ServerError;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;


/**
 * Created by michaeldfti on 16/09/17.
 */

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class BadRequestHandler {
	/**
	 * Handling the missing parameters
	 *
	 * @param ex the caught exception to be validated
	 * @return a {@link ServerError} with the error message
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = MissingServletRequestParameterException.class)
	public ServerError handleMissingParameters(MissingServletRequestParameterException ex) {
		String param = ex.getParameterName();
		return new ServerError("Missing parameter: " + param);
	}

	/**
	 * Handling the wrong parameter type
	 *
	 * @param ex the caught exception to be validated
	 * @return a {@link ServerError} with the error message
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ServerError handleArgumentTypeMismatch(MethodArgumentTypeMismatchException ex) {
		String type = ex.getRequiredType().getSimpleName();
		String param = ex.getParameter().getParameterName();
		return new ServerError("Wrong type for parameter: " + param + ". Expected type: " + type);
	}
}
