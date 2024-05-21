package med.vol.api.domain.direccion;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Direccion {

	private String calle;
	private String distrito;
	private String ciudad;
	private String numero;
	private String complemento;


	public Direccion(DatosDireccion datosDireccion) {

		this.calle = datosDireccion.calle();
		this.distrito = datosDireccion.distrito();
		this.ciudad = datosDireccion.ciudad();
		this.numero = datosDireccion.numero();
		this.complemento = datosDireccion.complemento();

	}


	public Direccion actualizarDatos(DatosDireccion datosDireccion) {
		this.calle = datosDireccion.calle();
		this.distrito = datosDireccion.distrito();
		this.ciudad = datosDireccion.ciudad();
		this.numero = datosDireccion.numero();
		this.complemento = datosDireccion.complemento();
		return this;
	}


}
