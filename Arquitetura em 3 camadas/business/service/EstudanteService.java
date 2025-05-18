package business.service;

import java.util.ArrayList;

import model.entity.Disciplina;
import model.entity.Estudante;
import model.repository.EstudanteRepository;

public class EstudanteService {

	// Classe que após a validação dos dados, os envia para o repositório que vai
	// cadastrar o estudante

	private EstudanteRepository repository;

	public EstudanteService(EstudanteRepository repository) {
		this.repository = repository;
	}

	public void registraEstudante(Estudante estudante) {
		repository.cadastrarEstudante(estudante);
	}

	public boolean removerEstudante(int matricula) {
		for (Estudante e : repository.getEstudantes()) {
			if (e.getMatricula() == matricula) {
				repository.deletarEstudantePelaMatricula(matricula);
				return true;
			}
		}
		return false;
	}

	public boolean atualizaEstudante(int matricula, String nomeEstudante, String curso, int ano, String email) {
		Estudante existente = repository.encontraPelaMatricula(matricula);
		if (existente != null) {
			Estudante atualizado = new Estudante(matricula, nomeEstudante, curso, ano, email);
			repository.atualizaEstudante(atualizado);
			return true;
		}
		return false;
	}

	public ArrayList<Estudante> listarTodosEstudantes() {
		return repository.getEstudantes();
	}

	public Estudante encontrarPelaMatricula(int matricula) {
		return repository.encontraPelaMatricula(matricula);
	}
}
