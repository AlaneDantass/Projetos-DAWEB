package business.service;

import model.repository.DisciplinaRepository;
import java.util.ArrayList;
import model.entity.Disciplina;


public class DisciplinaService {
	private DisciplinaRepository repository;

	public DisciplinaService(DisciplinaRepository repository) {
		this.repository = repository;
	}

	public void registraDisciplina(Disciplina disciplina) {
		repository.cadastrarDisciplina(disciplina);
	}

	public boolean removerDisciplina(int codDisciplina) {
		for (Disciplina d : repository.getDisciplinas()) {
			if (d.getCodDisciplina() == codDisciplina) {
				repository.removerDisciplinaPeloCod(codDisciplina);
				return true;
			}
		}
		return false;
	}

	public boolean atualizaDisciplina(int codDisciplina, String nomeDisciplina, String nomeProfessor) {
		Disciplina existente = repository.encontraPeloCod(codDisciplina);
		if (existente != null) {
			Disciplina atualizada = new Disciplina(codDisciplina, nomeDisciplina, nomeProfessor);
			repository.atualizarDisciplina(atualizada);
			return true;
		}
		return false;
	}

	public boolean matricularEstudante(int codDisciplina, int matriculaAluno) {
		repository.matricularEstudante(codDisciplina, matriculaAluno);
		return true;
	}

	public Disciplina encontrarPeloCod(int codDisciplina) {
		return repository.encontraPeloCod(codDisciplina);
	}

	public ArrayList<Disciplina> listarDisciplinas() {
		return repository.getDisciplinas();
	}

}
