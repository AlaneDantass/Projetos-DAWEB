package business.validation;
import model.entity.Estudante;


public class EstudanteValidacao {

	// Classe de validação para a classe Estudante

	public static boolean valido(Estudante estudante) {
		return estudante.getMatricula() > 0 && 
				estudante.getNomeEstudante() != null && 
				estudante.getCurso() != null
				&& estudante.getEmail() != null;
	}
}