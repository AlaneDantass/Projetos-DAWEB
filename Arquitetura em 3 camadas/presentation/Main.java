package presentation;

import model.repository.DisciplinaRepository;
import model.repository.EstudanteRepository;
import presentation.controller.EstudanteController;
import presentation.controller.DisciplinaController;
import business.service.EstudanteService;
import business.service.DisciplinaService;

public class Main {
	public static void main(String[] args) {

		
		EstudanteRepository estudanteRepository = new EstudanteRepository();
		EstudanteService estudanteService = new EstudanteService(estudanteRepository);
		
		DisciplinaRepository disciplinaRepository = new DisciplinaRepository(estudanteService);
		DisciplinaService disciplinaService = new DisciplinaService(disciplinaRepository);

		// Controllers
		EstudanteController estudanteController = new EstudanteController(estudanteService);
		DisciplinaController disciplinaController = new DisciplinaController(disciplinaService, estudanteService);

		// Menu
		Menu menu = new Menu(estudanteController, disciplinaController);
		menu.exibirMenu();
	}
}
