package business.services;
import java.util.ArrayList;

import model.entity.Disciplina;
import model.entity.Estudante;
import model.repository.DisciplinaRepository;
import model.repository.EstudanteRepository;


public class EstudanteService {
	private EstudanteRepository repository = new EstudanteRepository();
	private DisciplinaRepository disciplinaRepository = new DisciplinaRepository();
	
	
	/*
	public ArrayList<Estudante> todosEstudante() {
	    // ❌ Erro: este método deveria retornar todos os estudantes,
	    // mas tá retornando apenas o estudante com matrícula 0 (ou null).
	    // Isso não cumpre a função esperada do método. (basicamente não tem como passar o dado de matrícula de cada aluno
	    return repository.encontraPelaMatricula(0);
	}
	*/
	public ArrayList<Estudante> listarTodosEstudantes() {
        return repository.getEstudantes();
    }

	 public EstudanteService(EstudanteRepository repository) {
	        this.repository = repository;
	    }
	 
	public void cadastrarEstudante(int matricula, String nomeEstudante, String curso) {
		Estudante estudante = new Estudante(matricula, nomeEstudante, curso);
		repository.cadastrarEstudante(estudante);
	}
	

	 public boolean atualizaEstudante(int matricula, String novoNome, String novoCurso) {	       
	        return repository.atualizarEstudante(matricula, novoNome, novoCurso);
	    }
	
	public boolean removerEstudante(int matricula) {
        Estudante existente = repository.encontraPelaMatricula(matricula);
        if (existente != null) {
            repository.deletarEstudantePelaMatricula(matricula);
            disciplinaRepository.deletarEstudantePelaMatricula(matricula);
            
            return true;
        }
        return false;
    }
	
	public Estudante encontrarPelaMatricula(int matricula) {
        return repository.encontraPelaMatricula(matricula);
    }
}
