package med.vol.api.infra.errores;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class TratadorDeErrores {
	
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity traterError404() {
		
		return ResponseEntity.notFound().build();
		
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity traterError400(MethodArgumentNotValidException e) {
		
		var errores = e.getFieldErrors()
				.stream()
				.map(DatosErrorValidacion::new).toList();
		
		return ResponseEntity.badRequest().body(errores);
		
	}
	
	private record DatosErrorValidacion(String campo, String error) {
		public DatosErrorValidacion(FieldError e){
			this(e.getField(),e.getDefaultMessage());
		}
	}

}
