package pe.idat.service;

import java.util.List;

import pe.idat.model.Malla;

public interface MallaService {

	void guardar(Malla malla);
	void actualizar(Malla malla);
	void eliminar (Integer id);
	List<Malla> listar();
	Malla obtener(Integer id);
}
