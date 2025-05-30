package business.services;

import java.util.ArrayList;
import java.util.List; // Usar a interface List

// CORREÇÃO: Importar DTOs do pacote correto (business.dto como preferido)
import business.dto.EstudanteDTO;

import model.entity.Estudante;
import model.repository.DisciplinaRepository;
import model.repository.EstudanteRepository;

public class EstudanteService {

    // CORREÇÃO: Repositórios agora são injetados via construtor
    private EstudanteRepository estudanteRepository;
    private DisciplinaRepository disciplinaRepository;

    // CORREÇÃO: Construtor para injeção de dependência dos repositórios
    public EstudanteService(EstudanteRepository estudanteRepository, DisciplinaRepository disciplinaRepository) {
        this.estudanteRepository = estudanteRepository;
        this.disciplinaRepository = disciplinaRepository;
    }

    /**
     * CORREÇÃO: Retorna uma lista de todos os estudantes como DTOs.
     * Nome antigo: listarTodosEstudantes
     */
    public List<EstudanteDTO> listarTodosEstudantesDTO() {
        List<Estudante> estudantesEntities = estudanteRepository.getEstudantes();
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
        Estudante estudanteEntity = estudanteRepository.encontraPelaMatricula(matricula);
        if (estudanteEntity != null) {
            return new EstudanteDTO(
                estudanteEntity.getMatricula(),
                estudanteEntity.getNomeEstudante(),
                estudanteEntity.getCurso()
            );
        }
        return null;
    }

    public void cadastrarEstudante(int matricula, String nomeEstudante, String curso) {
        Estudante estudante = new Estudante(matricula, nomeEstudante, curso);
        estudanteRepository.cadastrarEstudante(estudante);
    }

    public boolean atualizaEstudante(int matricula, String novoNome, String novoCurso) {
        // A lógica de atualização (incluindo a verificação de existência)
        // agora está corretamente no repositório (com a correção do NPE)
        return estudanteRepository.atualizarEstudante(matricula, novoNome, novoCurso);
    }

    public boolean removerEstudante(int matricula) {
        Estudante existente = estudanteRepository.encontraPelaMatricula(matricula);
        if (existente != null) {
            estudanteRepository.deletarEstudantePelaMatricula(matricula);
            // Esta linha agora usa o disciplinaRepository injetado e compartilhado
            disciplinaRepository.deletarEstudantePelaMatricula(matricula);
            return true;
        }
        return false;
    }
}