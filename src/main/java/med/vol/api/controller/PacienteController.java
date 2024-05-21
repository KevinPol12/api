package med.vol.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import med.vol.api.domain.paciente.DatosRegistroPaciente;
import med.vol.api.domain.paciente.Paciente;
import med.vol.api.domain.paciente.PacienteRepository;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
	
	@Autowired
	private PacienteRepository pacienteRepository;

	@PostMapping
	public void registrarMedico(@RequestBody @Valid DatosRegistroPaciente datosRegistroPaciente) {
		
		System.out.println(datosRegistroPaciente);
		
		pacienteRepository.save(new Paciente(datosRegistroPaciente));
		
				
	}
	
}
