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

import pe.idat.model.Curso;
import pe.idat.model.Malla;
import pe.idat.service.CursoService;
import pe.idat.service.MallaService;

@RestController
@RequestMapping("/curso/v1")
public class CursoController {

	@Autowired
	private CursoService service;
	
	@Autowired
	@RequestMapping(path = "/listar", method = RequestMethod.GET)		
	public ResponseEntity<List<Curso>> listar() {
		return new ResponseEntity<List<Curso>>(service.listar(), HttpStatus.OK);	
	}
	
	@RequestMapping(path = "/guardar", method = RequestMethod.POST)
	public ResponseEntity<Void> guardar(@RequestBody Curso curso) {
		service.guardar(curso);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@RequestMapping(path = "/detalle/{id}", method = RequestMethod.GET)
	public ResponseEntity<Curso> obtenerPorId(@PathVariable Integer id) {
		Curso curso = service.obtener(id);
		if (curso != null) {
			return new ResponseEntity<Curso>(curso, HttpStatus.OK);
		}else {
			return new ResponseEntity<Curso>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(path = "/editar", method = RequestMethod.PUT)
	public ResponseEntity<Void> editar(@RequestBody Curso curso) {
		Curso cur = service.obtener(curso.getIdCurso());
		
		if (cur != null) {
			service.actualizar(curso);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(path = "/eliminar/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
		Curso curso = service.obtener(id);
		
		if (curso != null) {
			service.eliminar(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
}
