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
		DisciplinaRepository disciplinaRepository = new DisciplinaRepository(estudanteRepository);

		// Criando services
		EstudanteService estudanteService = new EstudanteService(estudanteRepository);
		DisciplinaService disciplinaService = new DisciplinaService(disciplinaRepository);

		// Criando controllers
		EstudanteController estudanteController = new EstudanteController(estudanteService);
		DisciplinaController disciplinaController = new DisciplinaController(disciplinaService, estudanteService);

		// Executando menu principal
		Menu menu = new Menu(estudanteController, disciplinaController);
		menu.exibirMenu();
	}

}
