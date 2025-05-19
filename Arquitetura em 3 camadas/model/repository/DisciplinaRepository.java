package model.repository;

import java.util.ArrayList;

import business.service.EstudanteService;
import model.entity.Estudante;
import model.entity.Disciplina;

public class DisciplinaRepository {
	private ArrayList<Disciplina> disciplinas = new ArrayList<>();
	private EstudanteService estudanteService;
	
	public DisciplinaRepository(EstudanteService estudanteService) {
	    this.estudanteService = estudanteService;
	}
	
	public void cadastrarDisciplina(Disciplina disciplina) {
		disciplinas.add(disciplina);
	}

	public void matricularEstudante(int codDisciplina, int matriculaAluno) {
		Disciplina disciplinaEncontrada = encontraPeloCod(codDisciplina);

		Estudante estudanteParaMatricular = null;
		for (Estudante e : estudanteService.listarTodosEstudantes()) {
			if (e.getMatricula() == matriculaAluno) {
				estudanteParaMatricular = e;
				break;
			}
			if (disciplinaEncontrada != null && estudanteParaMatricular != null) {
				disciplinaEncontrada.getEstudanteDisciplina().add(estudanteParaMatricular);

			}
		}
	}

	public ArrayList<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public void removerDisciplinaPeloCod(int codDisciplina) {
		Disciplina paraRemover = null;
		for (Disciplina d : disciplinas) {
			if (d.getCodDisciplina() == codDisciplina) {
				paraRemover = d;
				break;
			}
		}
		if (paraRemover != null) {
			disciplinas.remove(paraRemover);
		}
	}

	public void atualizarDisciplina(Disciplina disciplinaAtualizada) {
		for (Disciplina d : disciplinas) {
			if (d.getCodDisciplina() == disciplinaAtualizada.getCodDisciplina()) {
				d.setNomeDisciplina(disciplinaAtualizada.getNomeDisciplina());
				d.setNomeProfessor(disciplinaAtualizada.getNomeProfessor());
				break;
			}
		}
	}

	public Disciplina encontraPeloCod(int codDisciplina) {
		for (Disciplina d : disciplinas) {
			if (d.getCodDisciplina() == codDisciplina) {
				return d;
			}
		}
		return null;
	}

}