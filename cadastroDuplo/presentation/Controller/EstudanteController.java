package presentation.Controller;

import java.util.ArrayList;

import business.services.EstudanteService;
import business.services.Validation;

//Recebe os dados e faz validações iniciais
public class EstudanteController {
	private Validation validation = new Validation();
	private EstudanteService estudanteService =  new EstudanteService(null);
	
	
	public void  cadastrarEstudante(int matricula, String nomeEstudante, String curso) {
		if (validation.estudanteValido(matricula, nomeEstudante, curso)){
			estudanteService.cadastrarEstudante(matricula, nomeEstudante, curso);
		}
         
	}
	
	public boolean atualizaEstudante(int matricula, String novoNome, String novoCurso) {
        //Validar os dados que o user digitou
        if (validation.estudanteValido(matricula, novoNome, novoCurso)) { 
            //Chamar o serviço para realizar a mudança
            return estudanteService.atualizaEstudante(matricula, novoNome, novoCurso);
        } else {
            return false;
        }
    }
	
	
	public boolean removerEstudante(int matricula) {
        return estudanteService.removerEstudante(matricula);
    }
}
