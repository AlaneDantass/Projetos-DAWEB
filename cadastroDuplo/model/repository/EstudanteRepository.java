package model.repository;

import java.util.ArrayList;
import model.entity.Estudante;

public class EstudanteRepository {
	private ArrayList<Estudante> estudantes = new ArrayList<>();
	

	public ArrayList<Estudante> getEstudantes() {
        return estudantes;
    }
	
	public void cadastrarEstudante(Estudante estudante) {
		estudantes.add(estudante);
	}
	
	 public boolean atualizarEstudante(int matricula, String novoNome, String novoCurso) {
	        Estudante estudanteParaAtualizar = encontraPelaMatricula(matricula);
	        estudanteParaAtualizar.setNomeEstudante(novoNome); 
            estudanteParaAtualizar.setCurso(novoCurso); 
            return true; 

	    }
	
	public void deletarEstudantePelaMatricula(int matricula) {
        Estudante estudanteParaRemover = encontraPelaMatricula(matricula);
        if (estudanteParaRemover != null) {
            estudantes.remove(estudanteParaRemover); 
            
        }
    }
	
	public Estudante encontraPelaMatricula (int matricula) {
        for (Estudante e : estudantes) {
            if (e.getMatricula() == matricula) {
                return e;
            }
        }
        return null;
    }
    
}
