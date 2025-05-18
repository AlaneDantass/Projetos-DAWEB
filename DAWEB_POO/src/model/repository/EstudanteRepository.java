package model.repository;

import java.util.ArrayList;
import model.entity.Estudante;

public class EstudanteRepository {
	private static ArrayList<Estudante> estudantes = new ArrayList<>();

	public void cadastrarEstudante(Estudante estudante) {
		estudantes.add(estudante);
	}

	public ArrayList<Estudante> getEstudantes() {
		return estudantes;
	}

	public void deletarEstudantePelaMatricula(int matricula) {
		Estudante estudanteParaRemover = null;
		for (Estudante e : estudantes) {
			if (e.getMatricula() == matricula) {
				estudanteParaRemover = e;
				break;
			}
		}
		if (estudanteParaRemover != null) {
			estudantes.remove(estudanteParaRemover);
		}
	}

	public void atualizaEstudante(Estudante atualizado) {
		for (int i = 0; i < estudantes.size(); i++) {
			if (estudantes.get(i).getMatricula() == atualizado.getMatricula()) {
				estudantes.set(i, atualizado);
				return;
			}
		}
	}

	public void atualizarEstudante(Estudante estudanteAtualizado) {
		for (Estudante e : estudantes) {
			if (e.getMatricula() == estudanteAtualizado.getMatricula()) {
				e.setNomeEstudante(estudanteAtualizado.getNomeEstudante());
				e.setAno(estudanteAtualizado.getAno());
				e.setCurso(estudanteAtualizado.getCurso());
				e.setEmail(estudanteAtualizado.getEmail());
				break;
			}
		}
	}

	public Estudante encontraPelaMatricula(int matricula) {
		for (Estudante e : estudantes) {
			if (e.getMatricula() == matricula) {
				return e;
			}
		}
		return null;
	}

	public ArrayList<Estudante> todosEstudantes() {
		return estudantes;
	}
}
