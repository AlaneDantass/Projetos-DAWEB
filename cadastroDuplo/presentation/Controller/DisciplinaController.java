package presentation.Controller;

import java.util.List; // Usar a interface List

// CORREÇÃO: Importar DTOs do pacote correto
import business.dto.DisciplinaDTO;

import business.services.DisciplinaService;
import business.services.Validation;

public class DisciplinaController {
    // CORREÇÃO: Serviço agora é injetado via construtor
    private DisciplinaService disciplinaService;
    private Validation disciplinaValidacao = new Validation();

    // CORREÇÃO: Construtor para injeção de dependência
    public DisciplinaController(DisciplinaService disciplinaService) {
        this.disciplinaService = disciplinaService;
    }

    public void cadastrarDisciplina(int codDisciplina, String nomeDisciplina, String nomeProfessor) {
        if (disciplinaValidacao.disciplinaValida(codDisciplina, nomeDisciplina, nomeProfessor)) {
            disciplinaService.registraDisciplina(codDisciplina, nomeDisciplina, nomeProfessor);
        }
    }

    public boolean inserirEstudanteNaDisciplina(int codDisciplina, int matriculaAluno) {
        return disciplinaService.matricularEstudante(codDisciplina, matriculaAluno);
    }

    /**
     * CORREÇÃO: Retorna uma lista de todas as disciplinas como DTOs.
     * Nome antigo: listarDisciplinas (que era void)
     */
    public List<DisciplinaDTO> getTodasDisciplinasDTO() {
        return disciplinaService.listarTodasDisciplinasDTO();
    }

    public boolean atualizaDisciplina(int codAtual, String nomeAtual, String professorAtual) {
        if (disciplinaValidacao.disciplinaValida(codAtual, nomeAtual, professorAtual)) {
            return disciplinaService.atualizaDisciplina(codAtual, nomeAtual, professorAtual);
        } else {
            return false;
        }
    }

    public boolean removerDisciplina(int codDisciplina) {
        return disciplinaService.removerDisciplina(codDisciplina);
    }
}