package presentation;

import presentation.controller.EstudanteController;
import presentation.controller.DisciplinaController;
import business.utils.Printer;
import business.utils.Reader;

public class Menu {

	private DisciplinaController dControl;
	private EstudanteController eControl;
	private int opcao;

	public Menu(EstudanteController estudanteController, DisciplinaController disciplinaController) {
		this.eControl = estudanteController;
		this.dControl = disciplinaController;
	}

	public static void printMenu() {
		Printer.print("\n===== Menu de Produtos =====");
		Printer.print("1. Cadastrar disciplina");
		Printer.print("2. Cadastrar estudante");
		Printer.print("3. Matricular aluno na disciplina");
		Printer.print("4. Listar");
		Printer.print("5. Editar disciplina");
		Printer.print("6. Editar estudante");
		Printer.print("7. Remover disciplina");
		Printer.print("8. Remover estudante");
		Printer.print("9. Sair");

		Printer.print("Escolha uma opção:");
	}

	public void exibirMenu() {
		do {
			printMenu();
			opcao = Reader.lerNum();

			switch (opcao) {
			case 1:
				dControl.cadastrarDisciplina();
				break;
			case 2:
				eControl.cadastrarEstudante();
				break;
			case 3:
				dControl.matricularEstudante();
				break;
			case 4:
				dControl.listarDisciplinas();
				eControl.listarEstudantes();
				break;
			case 5:
				dControl.atualizarDisciplina();
				break;
			case 6:
				eControl.atualizarEstudante();
				break;
			case 7:
				dControl.removerDisciplina();
				break;
			case 8:
				eControl.removerEstudante();
				break;
			case 9:
				Printer.print("Saindo...");
				break;
			default:
				Printer.print("ERROR! Tente novamente.");
				break;
			}
		} while (opcao != 7);
	}
}
