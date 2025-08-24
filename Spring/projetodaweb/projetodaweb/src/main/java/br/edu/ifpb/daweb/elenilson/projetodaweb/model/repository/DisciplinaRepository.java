package br.edu.ifpb.daweb.elenilson.projetodaweb.model.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifpb.daweb.elenilson.projetodaweb.model.entity.Disciplina;

@Repository
public interface DisciplinaRepository extends JpaRepository<Disciplina, Long>{
	List <Disciplina> findByNomeDisciplina(String nomeDisciplina);
	List <Disciplina> findByNomeProfessor(String nomeProfessor);
	
}



