package ing.kata.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// code 404
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException  extends RuntimeException{
private static final long serialVersionUID = 7876075890349114220L;
	
	public NotFoundException(String message) {
		super(message);
	}

}
