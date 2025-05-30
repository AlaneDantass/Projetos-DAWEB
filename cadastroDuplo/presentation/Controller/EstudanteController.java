package presentation.Controller;

import java.util.List; // Usar a interface List

// CORREÇÃO: Importar DTOs do pacote correto
import business.dto.EstudanteDTO;

import business.services.EstudanteService;
import business.services.Validation;

public class EstudanteController {
    private Validation validation = new Validation();
    // CORREÇÃO: Serviço agora é injetado via construtor
    private EstudanteService estudanteService;

    // CORREÇÃO: Construtor para injeção de dependência
    public EstudanteController(EstudanteService estudanteService) {
        this.estudanteService = estudanteService;
    }

    public void cadastrarEstudante(int matricula, String nomeEstudante, String curso) {
        if (validation.estudanteValido(matricula, nomeEstudante, curso)) {
            estudanteService.cadastrarEstudante(matricula, nomeEstudante, curso);
        }
    }

    /**
     * CORREÇÃO: Retorna uma lista de todos os estudantes como DTOs.
     * Nome antigo: listarEstudantes (que era void)
     */
    public List<EstudanteDTO> getTodosEstudantesDTO() {
        return estudanteService.listarTodosEstudantesDTO();
    }

    public boolean atualizaEstudante(int matricula, String novoNome, String novoCurso) {
        if (validation.estudanteValido(matricula, novoNome, novoCurso)) {
            return estudanteService.atualizaEstudante(matricula, novoNome, novoCurso);
        } else {
            return false;
        }
    }

    public boolean removerEstudante(int matricula) {
        return estudanteService.removerEstudante(matricula);
    }
}