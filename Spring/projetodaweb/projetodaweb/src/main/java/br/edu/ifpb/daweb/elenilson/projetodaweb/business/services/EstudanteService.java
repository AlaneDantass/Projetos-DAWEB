package br.edu.ifpb.daweb.elenilson.projetodaweb.business.services;

import java.util.ArrayList;
import java.util.List; // Usar a interface List
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpb.daweb.elenilson.projetodaweb.business.dto.DisciplinaDTO;
// CORREÇÃO: Importar DTOs do pacote correto (business.dto como preferido)
import br.edu.ifpb.daweb.elenilson.projetodaweb.business.dto.EstudanteDTO;
import br.edu.ifpb.daweb.elenilson.projetodaweb.model.entity.Disciplina;
import br.edu.ifpb.daweb.elenilson.projetodaweb.model.entity.Estudante;
import br.edu.ifpb.daweb.elenilson.projetodaweb.model.repository.DisciplinaRepository;
import br.edu.ifpb.daweb.elenilson.projetodaweb.model.repository.EstudanteRepository;

@Service
public class EstudanteService {

    // CORREÇÃO: Repositórios agora são injetados via construtor
	@Autowired
    private EstudanteRepository estudanteRepository;
	// CORREÇÃO: Construtor para injeção de dependência dos repositórios
    public EstudanteService(EstudanteRepository estudanteRepository, DisciplinaRepository disciplinaRepository) {
        this.estudanteRepository = estudanteRepository;
    }

    /**
     * CORREÇÃO: Retorna uma lista de todos os estudantes como DTOs.
     * Nome antigo: listarTodosEstudantes
     */
    public List<EstudanteDTO> listarTodosEstudantesDTO() {
        List<Estudante> estudantesEntities = estudanteRepository.findAll();
        List<EstudanteDTO> estudanteDTOs = new ArrayList<>();
        if (estudantesEntities == null) {
            return estudanteDTOs; // Retorna lista vazia se o repositório retornar null
        }

        for (Estudante estudanteEntity : estudantesEntities) {
            estudanteDTOs.add(new EstudanteDTO(
                    estudanteEntity.getMatricula(),
                    estudanteEntity.getNomeEstudante(),
                    estudanteEntity.getCurso()
            ));
        }
        return estudanteDTOs;
    }

    /**
     * CORREÇÃO: Encontra um estudante pela matrícula e o retorna como um DTO.
     * Nome antigo: encontrarPelaMatricula
     */
    public EstudanteDTO encontrarEstudanteDTOPelaMatricula(int matricula) {
          Optional<Estudante> estudanteOpt = estudanteRepository.findByMatricula(matricula); //Tive que declarar o findByMatricula como optional
          if (estudanteOpt.isPresent()) {
        	  Estudante estudanteEntity = estudanteOpt.get();
        	  EstudanteDTO estudanteDTO = new EstudanteDTO(
        			  estudanteEntity.getMatricula(),
                      estudanteEntity.getNomeEstudante(),
                      estudanteEntity.getCurso());
        	  return estudanteDTO;
          }
        return null;
    }


    public void cadastrarEstudante(int matricula, String nomeEstudante, String curso) {
        Estudante estudante = new Estudante(matricula, nomeEstudante, curso);
        estudanteRepository.save(estudante);
    }

    public boolean atualizaEstudante(int matricula, String novoNome, String novoCurso) {
    	Optional<Estudante> updateOpt = estudanteRepository.findByMatricula(matricula);
    	if (updateOpt.isPresent()) {
    		Estudante updateEstudante = updateOpt.get();
    		updateEstudante.setCurso(novoCurso);
    		updateEstudante.setNomeEstudante(novoCurso);
    		
    		estudanteRepository.save(updateEstudante);
    		return true;// estudanteRepository. (matricula, novoNome, novoCurso);
    	}
        // A lógica de atualização (incluindo a verificação de existência)
        // agora está corretamente no repositório (com a correção do NPE)
    	return false;
    }
    

    public boolean removerEstudante(int matricula) {
          Optional<Estudante> existenteOpt = estudanteRepository.findByMatricula(matricula);
          if (existenteOpt.isPresent()) {
        	  Estudante estudanteEntity = existenteOpt.get();
              estudanteRepository.delete(estudanteEntity);
            // Esta linha agora usa o disciplinaRepository injetado e compartilhado
//            disciplinaRepository.deletarEstudantePelaMatricula(matricula);
//            return true;
          }
        return false;
    }
}
