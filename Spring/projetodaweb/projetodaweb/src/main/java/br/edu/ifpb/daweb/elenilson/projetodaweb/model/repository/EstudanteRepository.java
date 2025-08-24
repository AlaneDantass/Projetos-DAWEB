package br.edu.ifpb.daweb.elenilson.projetodaweb.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.edu.ifpb.daweb.elenilson.projetodaweb.model.entity.Estudante;

@Repository
public interface EstudanteRepository extends JpaRepository<Estudante, Long>{
	List <Estudante> findByNomeEstudante(String nomeEstudante);
	List <Estudante> findByCurso(String curso);
	Optional <Estudante> findByMatricula(int matricula);
	//// Obs: esse método é redundante porque JpaRepository já tem findById()
	
	
	// Como 'matricula' é chave primária (@Id), não preciso criar findByMatricula.
	// O JpaRepository já fornece o método findById(), que retorna um Optional<Estudante>.
	// O Optional é usado para representar o caso em que não exista nenhum estudante com essa matrícula.

}
