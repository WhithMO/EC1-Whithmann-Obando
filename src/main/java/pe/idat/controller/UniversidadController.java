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

import pe.idat.model.Malla;
import pe.idat.model.Universidad;
import pe.idat.service.MallaService;
import pe.idat.service.UniversidadService;

@RestController
@RequestMapping("/universidad/v1")
public class UniversidadController {

	@Autowired
	private UniversidadService service;
	
	@Autowired
	@RequestMapping(path = "/listar", method = RequestMethod.GET)		
	public ResponseEntity<List<Universidad>> listar() {
		return new ResponseEntity<List<Universidad>>(service.listar(), HttpStatus.OK);	
	}
	
	@RequestMapping(path = "/guardar", method = RequestMethod.POST)
	public ResponseEntity<Void> guardar(@RequestBody Universidad universidad) {
		service.guardar(universidad);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@RequestMapping(path = "/detalle/{id}", method = RequestMethod.GET)
	public ResponseEntity<Universidad> obtenerPorId(@PathVariable Integer id) {
		Universidad universidad = service.obtener(id);
		if (universidad != null) {
			return new ResponseEntity<Universidad>(universidad, HttpStatus.OK);
		}else {
			return new ResponseEntity<Universidad>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(path = "/editar", method = RequestMethod.PUT)
	public ResponseEntity<Void> editar(@RequestBody Universidad universidad) {
		Universidad uni = service.obtener(universidad.getIdUniversidad());
		
		if (uni != null) {
			service.actualizar(universidad);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(path = "/eliminar/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
		Universidad universidad = service.obtener(id);
		
		if (universidad != null) {
			service.eliminar(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
}
