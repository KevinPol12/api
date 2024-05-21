package med.vol.api.domain.medico;

import med.vol.api.domain.direccion.DatosDireccion;
import med.vol.api.domain.direccion.Direccion;

public record DatosRespuestaMedico(String id,String nombre, String email, String telefono, 
		String documento, DatosDireccion direccion) {

	public DatosRespuestaMedico(Medico medico) {
		this(
				medico.getId().toString(),
				medico.getNombre(), 
				medico.getEmail(), 
				medico.getTelefono(),
				medico.getDocumento(),
				new DatosDireccion(
						medico.getDireccion().getCalle(), 
						medico.getDireccion().getDistrito(),
						medico.getDireccion().getCiudad(),
						medico.getDireccion().getNumero(),
						medico.getDireccion().getComplemento())
				);
	}


}
