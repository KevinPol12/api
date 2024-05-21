package med.vol.api.domain.usuario;

import jakarta.validation.constraints.NotBlank;

public record DatosAutenticacionUsuario(
		@NotBlank
		String login, 
		@NotBlank
		String clave) {

}
