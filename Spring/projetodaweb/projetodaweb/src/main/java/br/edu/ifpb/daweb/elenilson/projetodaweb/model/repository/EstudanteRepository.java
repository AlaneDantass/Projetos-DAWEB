package br.edu.ifpb.daweb.elenilson.projetodaweb.model.repository;

import java.util.List; 


import org.springframework.data.jpa.repository.JpaRepository;
import br.edu.ifpb.daweb.elenilson.projetodaweb.model.entity.Estudante;

public interface EstudanteRepository extends JpaRepository<Estudante, Long>{
	List <Estudante> findByNomeEstudante(String nomeEstudante);
	List <Estudante> findByCurso(String curso);
	List <Estudante> findByMatricula(int matricula);
}
