package presentation;


import business.services.Printer;
import business.services.Reader;
import model.entity.Disciplina;
import model.entity.Estudante;
import presentation.Controller.DisciplinaController;
import presentation.Controller.EstudanteController;

public class Main {
	
	public static void main(String[] args) {
        Main main = new Main(); // necessário para acessar métodos não estáticos
        main.exibirMenu();
    }
	
	EstudanteController estudanteController = new EstudanteController();
	DisciplinaController disciplinaController = new DisciplinaController();

	
	Reader reader = new Reader();
	Printer printer = new Printer();
	private static void printMenu() {
        Printer.print("\n===== Menu de Produtos =====");
        Printer.print("1. Cadastrar disciplina");
        Printer.print("2. Cadastrar estudante");
        Printer.print("3. Editar disciplina");
        Printer.print("4. Editar estudante");
        Printer.print("5. Remover disciplina");
        Printer.print("6. Remover estudante");
        Printer.print("7. Matricular estudante em uma disciplina");
        Printer.print("8. Sair");
        Printer.print("Escolha uma opção:");
    }
	
	public void exibirMenu() {
        int opcao;
        do {
            printMenu();
            opcao = reader.lerNum();

            switch (opcao) {
                case 1:
                	cadastrarDisciplina(); //Concluído
                    break;
                case 2:
                    cadastrarEstudante(); //Concluído
                    break;
                case 3:
                	inserirEstudante(); //Concluído
                    break;
                case 4:
                	listarTudo();
                    break;
                case 5:
                	atualizarEstudante(); //Concluído
                    break;
                case 6:
                	atualizarDisciplina(); //Concluído
                	break;
                case 7:
                	removerEstudante(); //Concluído
                    break;
                case 8:
                	removerDisciplina(); //Concluído
                    break;
                case 9:
                    Printer.print("Saindo...");
                    break;
                default:
                    Printer.print("ERROR! Tente novamente.");
                    break;
            }
        } while (opcao != 8);
    }
	
	public void  cadastrarEstudante() {
		Printer.print("Digite a matrícula do estudante: ");
		int matricula = reader.lerNum();
		
		Printer.print("Digite o nome do estudante: ");
		String nomeEstudante = reader.ler();
		
		Printer.print("Digite o nome do curso do estudante: ");
		String curso = reader.ler();
		
		estudanteController.cadastrarEstudante(matricula, nomeEstudante, curso);
	}
	public void  cadastrarDisciplina() {
		Printer.print("Digite o código da disciplina: ");
		int matricula = reader.lerNum();
		
		Printer.print("Digite o nome da disciplina: ");
		String nomeEstudante = reader.ler();
		
		Printer.print("Digite o nome do professor da disciplina: ");
		String curso = reader.ler();
		
		disciplinaController.cadastrarDisciplina(matricula, nomeEstudante, curso);
	}
	
	private void inserirEstudante() {
	    Printer.print("Digite a matrícula do estudante: ");
	    int matriculaEstudante = reader.lerNum();

	    Printer.print("Digite o código da disciplina: ");
	    int codDisciplina = reader.lerNum();

	    if (disciplinaController.inserirEstudanteNaDisciplina(codDisciplina, matriculaEstudante)) {
	        Printer.print("Estudante matriculado com sucesso na disciplina!");
	    } else {
	        // Se o estudante ou disciplina não existirem
	        Printer.print("Não foi possível matricular o estudante. Verifique a matrícula do estudante e o código da disciplina.");
	    }
	}
	
	public void listarTudo() {
		
	}
	
	private void atualizarDisciplina() {
		Printer.print("\n--- Atualizar Dados da Disciplina ---");
	    Printer.print("\nDigite o código da matéria a ser atualizada: ");
	    int codDisciplina = reader.lerNum();

	    Printer.print("Novo nome da disciplina: ");
	    String nomeDisciplina = reader.ler();

	    Printer.print("Novo professor: ");
	    String nomeProfessor = reader.ler();

	    // Se espera um retorno boleano
	    if (disciplinaController.atualizaDisciplina(codDisciplina, nomeDisciplina, nomeProfessor)) {
	        Printer.print("Disciplina atualizada com sucesso!");
	    } else {
	        Printer.print("Dados inválidos. Atualização não realizada.");
	    }
	}
	private void atualizarEstudante() {
	    Printer.print("\n--- Atualizar Dados do Estudante ---");
	    Printer.print("Digite a matrícula do estudante a ser atualizado: ");
	    int matricula = reader.lerNum(); 

	    Printer.print("Digite o novo nome do estudante: ");
	    String novoNome = reader.ler(); 

	    Printer.print("Digite o novo curso do estudante: ");
	    String novoCurso = reader.ler();

	    if (estudanteController.atualizaEstudante(matricula, novoNome, novoCurso)) {
	        Printer.print("Dados do estudante atualizados com sucesso!");
	    } else {
	        Printer.print("Não foi possível atualizar os dados do estudante. Verifique os dados ou a matrícula.");
	    }
	}
	
	public void removerEstudante() {
		estudanteController.removerEstudante(0);
	}
	
	public void removerDisciplina() {
		disciplinaController.removerDisciplina(0);
	}
}
