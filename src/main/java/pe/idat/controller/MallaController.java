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

import pe.idat.service.MallaService;


@RestController
@RequestMapping("/malla/v1")
public class MallaController {
	
	@Autowired
	private MallaService service;
	
	@Autowired
	@RequestMapping(path = "/listar", method = RequestMethod.GET)		
	public ResponseEntity<List<Malla>> listar() {
		return new ResponseEntity<List<Malla>>(service.listar(), HttpStatus.OK);	
	}
	
	@RequestMapping(path = "/guardar", method = RequestMethod.POST)
	public ResponseEntity<Void> guardar(@RequestBody Malla malla) {
		service.guardar(malla);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@RequestMapping(path = "/detalle/{id}", method = RequestMethod.GET)
	public ResponseEntity<Malla> obtenerPorId(@PathVariable Integer id) {
		Malla malla = service.obtener(id);
		if (malla != null) {
			return new ResponseEntity<Malla>(malla, HttpStatus.OK);
		}else {
			return new ResponseEntity<Malla>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(path = "/editar", method = RequestMethod.PUT)
	public ResponseEntity<Void> editar(@RequestBody Malla malla) {
		Malla mall = service.obtener(malla.getIdMalla());
		
		if (mall != null) {
			service.actualizar(malla);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(path = "/eliminar/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
		Malla malla = service.obtener(id);
		
		if (malla != null) {
			service.eliminar(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
}
