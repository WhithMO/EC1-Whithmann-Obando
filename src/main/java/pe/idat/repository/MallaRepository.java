package pe.idat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.idat.model.Malla;

@Repository
public interface MallaRepository extends JpaRepository<Malla, Integer>{

}
