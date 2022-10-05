package pe.idat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import pe.idat.model.Profesor;

import pe.idat.service.ProfesorService;

@RestController
@RequestMapping("/profesor/v1")
public class ProfesorController {

	@Autowired
	private ProfesorService service;
	
	@Autowired
	@RequestMapping(path = "/listar", method = RequestMethod.GET)		
	public ResponseEntity<List<Profesor>> listar() {
		return new ResponseEntity<List<Profesor>>(service.listar(), HttpStatus.OK);	
	}
	
	@RequestMapping(path = "/guardar", method = RequestMethod.POST)
	public ResponseEntity<Void> guardar(@RequestBody Profesor profesor) {
		service.guardar(profesor);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@RequestMapping(path = "/detalle/{id}", method = RequestMethod.GET)
	public ResponseEntity<Profesor> obtenerPorId(@PathVariable Integer id) {
		Profesor profesor = service.obtener(id);
		if (profesor != null) {
			return new ResponseEntity<Profesor>(profesor, HttpStatus.OK);
		}else {
			return new ResponseEntity<Profesor>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(path = "/editar", method = RequestMethod.PUT)
	public ResponseEntity<Void> editar(@RequestBody Profesor profesor) {
		Profesor prof = service.obtener(profesor.getIdProfesor());
		
		if (prof != null) {
			service.actualizar(profesor);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(path = "/eliminar/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
		Profesor profesor = service.obtener(id);
		
		if (profesor != null) {
			service.eliminar(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
}
