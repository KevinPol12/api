package med.vol.api.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.vol.api.domain.direccion.DatosDireccion;
import med.vol.api.domain.medico.DatosActualizarMedico;
import med.vol.api.domain.medico.DatosListadoMedico;
import med.vol.api.domain.medico.DatosRegistroMedico;
import med.vol.api.domain.medico.DatosRespuestaMedico;
import med.vol.api.domain.medico.Medico;
import med.vol.api.domain.medico.MedicoRepository;

@RestController
@RequestMapping("/medicos")
public class MedicoController {
	
	@Autowired
	private MedicoRepository medicoRepository;

	@PostMapping
	public ResponseEntity<DatosRespuestaMedico> registrarMedico(@RequestBody @Valid DatosRegistroMedico datosRegistroMedico, UriComponentsBuilder uriComponentsBuilder) {
		
		Medico medico = medicoRepository.save(new Medico(datosRegistroMedico));
		
		DatosRespuestaMedico datosRespuestaMedico = 
				new DatosRespuestaMedico(medico);

		URI url = uriComponentsBuilder.path("/medicos/{id}")
							.buildAndExpand(medico.getId()).toUri();
		
		return ResponseEntity.created(url).body(datosRespuestaMedico);
				
	}
	
	@GetMapping
	public ResponseEntity<Page<DatosListadoMedico> >listadoMedico(@PageableDefault(size=3) Pageable paginacion) {
		
		return 	ResponseEntity.ok(medicoRepository
				.findByActivoTrue(paginacion)
				.map(DatosListadoMedico::new)) ;
		
		//return medicoRepository.findAll(paginacion).map(DatosListadoMedico::new);

	}
	
	@PutMapping
	@Transactional
	public ResponseEntity actualizarMedico(@RequestBody @Valid DatosActualizarMedico datosActualizarMedico) {
		
		Medico medico = medicoRepository.getReferenceById(datosActualizarMedico.id());
		
		medico.actualizarDatos(datosActualizarMedico);
		
		return ResponseEntity.ok(new DatosRespuestaMedico(medico));
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity eliminarMedico(@PathVariable Long id) {
		Medico medico = medicoRepository.getReferenceById(id);
		medico.desactivarMedico();
		return ResponseEntity.noContent().build();
	}
	
	
//	public void eliminarMedico(@PathVariable Long id) {
//		Medico medico = medicoRepository.getReferenceById(id);
//		medicoRepository.delete(medico);
//		
//	}
	
	
	
	
	@GetMapping("/{id}")
	public ResponseEntity<DatosRespuestaMedico> retornaDatosMedico(@PathVariable Long id) {
		Medico medico = medicoRepository.getReferenceById(id);
		
		return ResponseEntity.ok(new DatosRespuestaMedico(medico));
		
		
	}

}
	
