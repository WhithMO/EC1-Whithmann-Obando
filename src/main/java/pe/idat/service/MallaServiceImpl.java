package pe.idat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.idat.model.Malla;
import pe.idat.repository.MallaRepository;

@Service
public class MallaServiceImpl implements MallaService {

	@Autowired
	private MallaRepository repository;
	
	@Override
	public void guardar(Malla malla) {
		repository.save(malla);

	}

	@Override
	public void actualizar(Malla malla) {
		repository.saveAndFlush(malla);
	}

	@Override
	public void eliminar(Integer id) {
		repository.deleteById(id);

	}

	@Override
	public List<Malla> listar() {
		
		return repository.findAll();
	}

	@Override
	public Malla obtener(Integer id) {
		
		return repository.findById(id).orElse(null);
	}

}
