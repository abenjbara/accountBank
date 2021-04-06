package ing.kata.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// code 400
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BankException extends RuntimeException{
	
	private static final long serialVersionUID = -4304463549053669898L;

	public BankException(String message) {
		super(message);
	}

}
