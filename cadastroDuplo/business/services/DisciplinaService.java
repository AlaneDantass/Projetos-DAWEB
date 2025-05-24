package business.services;
/*A camada de serviço ela serve para atender o que a camada presentation espera, atender a interação do cliente, 
logo é possível usar os DTO's aqui, 
já que os DTOs vão ser necessários para as classes controleer*/

import java.util.ArrayList;
import presentation.dto.DisciplinaDTO;
import presentation.dto.EstudanteDTO;
import model.entity.Disciplina;
import model.entity.Estudante;
import model.repository.DisciplinaRepository;
import model.repository.EstudanteRepository;

public class DisciplinaService {
	private DisciplinaRepository repository = new DisciplinaRepository();
	private EstudanteRepository estudanteRepository = new EstudanteRepository();
	
	public ArrayList<Disciplina> todasDisciplinas() {
        return repository.listarTodasDisciplinas();
    }
	
	public void registraDisciplina(int codDisciplina, String nomeDisciplina, String nomeProfessor) {
		Disciplina disciplina = new Disciplina(codDisciplina, nomeDisciplina, nomeProfessor);
        repository.cadastrarDisciplina(disciplina);
    }
	
	public boolean removerDisciplina(int codDisciplina) {
        Disciplina existe = repository.encontraPeloCod(codDisciplina);
        if (existe != null) {
                repository.removerDisciplinaPeloCod(codDisciplina);
                return true;
            }
        return false;
    }
	
	public boolean atualizaDisciplina(int codAtual, String nomeAtual, String professorAtual) {
        Disciplina existente = repository.encontraPeloCod(codAtual);
        if (existente != null) {
            repository.atualizarDisciplina(codAtual, nomeAtual,professorAtual);
            return true;
        }
        return false;
    }
	
	public boolean matricularEstudante(int codDisciplina, int matriculaAluno) {
        Disciplina disciplina = repository.encontraPeloCod(codDisciplina);
        Estudante estudante = estudanteRepository.encontraPelaMatricula(matriculaAluno);
        if (disciplina != null && estudante != null) {	
            repository.adicionarEstudanteNaDisciplina(disciplina, estudante);
            return true;
        }
        return false;
    }

	public Disciplina encontrarPeloCod(int codDisciplina) {
	    return repository.encontraPeloCod(codDisciplina);
	}

}
