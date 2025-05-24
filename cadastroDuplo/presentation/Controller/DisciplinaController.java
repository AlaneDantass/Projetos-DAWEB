package presentation.Controller;

import business.services.DisciplinaService;
import business.services.Validation;


public class DisciplinaController {
	private DisciplinaService disciplinaService = new DisciplinaService();
    private Validation disciplinaValidacao = new Validation();
    
    public void cadastrarDisciplina(int codDisciplina, String nomeDisciplina, String nomeProfessor) {
        if (disciplinaValidacao.disciplinaValida(codDisciplina, nomeDisciplina, nomeProfessor)) {
        	disciplinaService.registraDisciplina(codDisciplina, nomeDisciplina, nomeProfessor);
        }
    }
    
	public boolean atualizaDisciplina(int codAtual, String nomeAtual, String professorAtual) {
        if (disciplinaValidacao.disciplinaValida(codAtual, nomeAtual,professorAtual)) { 
            //Chamar o serviço para realizar a mudança
            return disciplinaService.atualizaDisciplina(codAtual, nomeAtual,professorAtual);
        } else {
            return false;
        }
    }
	
    public boolean removerDisciplina(int codDisciplina) {
        return disciplinaService.removerDisciplina(codDisciplina);
    }

    public boolean inserirEstudanteNaDisciplina(int codDisciplina, int matriculaAluno) {
        return disciplinaService.matricularEstudante(codDisciplina, matriculaAluno); // Retorna o status do serviço
    }

}
