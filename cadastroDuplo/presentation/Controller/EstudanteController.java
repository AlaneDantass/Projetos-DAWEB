package presentation.Controller; // Ou o nome do seu pacote de controller

import java.util.List; // Importar a interface List
import business.dto.EstudanteDTO;
import business.services.EstudanteService;
import business.services.Printer;
import business.services.Validation;

public class EstudanteController {

	private Validation validation = new Validation();
	private EstudanteService estudanteService; // Serviço será injetado

	public EstudanteController(EstudanteService estudanteService) {
		this.estudanteService = estudanteService;
	}

	public void cadastrarEstudante(int matricula, String nomeEstudante, String curso) {
		if (validation.estudanteValido(matricula, nomeEstudante, curso)) {
			estudanteService.cadastrarEstudante(matricula, nomeEstudante, curso);
		} else {
		}
	}

//	public void listarEstudantes() {
//		List<EstudanteDTO> lista = estudanteService.listarTodosEstudantesDTO();
//
//		if (lista.isEmpty()) {
//			Printer.print("Nenhum estudante cadastrado.");
//		} else {
//			for (EstudanteDTO e : lista) {
//				Printer.print("\n-" + e.getNomeEstudante());
//			}
//		}
//	}

	public List<EstudanteDTO> getTodosEstudantesDTO() {
        return estudanteService.listarTodosEstudantesDTO();
    }
	
	public EstudanteDTO getEstudanteDTOPorMatricula(int matricula) {
		return estudanteService.encontrarEstudanteDTOPelaMatricula(matricula);
	}

	public boolean atualizaEstudante(int matricula, String novoNome, String novoCurso) {
		if (validation.estudanteValido(matricula, novoNome, novoCurso)) {
			return estudanteService.atualizaEstudante(matricula, novoNome, novoCurso);
		} else {
			return false;
		}
	}

	public boolean removerEstudante(int matricula) {
		return estudanteService.removerEstudante(matricula);
	}
}