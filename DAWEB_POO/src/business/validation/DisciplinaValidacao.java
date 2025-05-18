package business.validation;
import model.entity.Disciplina;

public class DisciplinaValidacao {

	//Classe de validação para a classe Disciplina
	    
	    public static boolean valida(Disciplina disciplina) {
	        return disciplina.getCodDisciplina() > 0 &&
	               disciplina.getNomeDisciplina() != null &&
	               disciplina.getNomeProfessor() != null;
	    }
	}