package presentation;

// Importações de DTOs
import business.dto.DisciplinaDTO;
import business.dto.EstudanteDTO;

// Importações de Serviços e Utilitários
import business.services.Printer;
import business.services.Reader;
import business.services.DisciplinaService;
import business.services.EstudanteService;

// Importações de Repositórios
import model.repository.DisciplinaRepository;
import model.repository.EstudanteRepository;

// Importações de Controllers
import presentation.Controller.DisciplinaController;
import presentation.Controller.EstudanteController;

import java.util.List;

public class Main {

	// CORREÇÃO PRINCIPAL: Instanciar repositórios e serviços aqui para serem
	// compartilhados
	private final DisciplinaRepository disciplinaRepositoryInstance;
	private final EstudanteRepository estudanteRepositoryInstance;
	private final DisciplinaService disciplinaServiceInstance;
	private final EstudanteService estudanteServiceInstance;

	private final EstudanteController estudanteController;
	private final DisciplinaController disciplinaController;
	private final Reader reader;
	// private final Printer printer; // Se Printer não for estático

	// Construtor do Main para inicializar tudo
	public Main() {
		// 1. Criar instâncias dos repositórios UMA VEZ
		this.disciplinaRepositoryInstance = new DisciplinaRepository();
		this.estudanteRepositoryInstance = new EstudanteRepository();

		// 2. Criar instâncias dos serviços, injetando os repositórios
		this.disciplinaServiceInstance = new DisciplinaService(disciplinaRepositoryInstance,
				estudanteRepositoryInstance);
		this.estudanteServiceInstance = new EstudanteService(estudanteRepositoryInstance, disciplinaRepositoryInstance);

		// 3. Criar instâncias dos controllers, injetando os serviços
		this.estudanteController = new EstudanteController(estudanteServiceInstance);
		this.disciplinaController = new DisciplinaController(disciplinaServiceInstance);

		// 4. Utilitários
		this.reader = new Reader();
		// this.printer = new Printer(); // Se não for estático
	}

	public static void main(String[] args) {
		Main mainApp = new Main(); // Cria a instância de Main, que inicializa tudo acima
		mainApp.exibirMenu(); // Chama o método de instância
	}

	// printMenu pode continuar estático se não depender de campos de instância de
	// Main
	private static void printMenu() {
		Printer.print("\n===== Menu de Produtos =====");
		Printer.print("1. Cadastrar disciplina");
		Printer.print("2. Cadastrar estudante");
		Printer.print("3. Matricular estudante em uma disciplina");
		Printer.print("4. Listar disciplinas e estudantes");
		Printer.print("5. Editar disciplina");
		Printer.print("6. Editar estudante");
		Printer.print("7. Remover disciplina");
		Printer.print("8. Remover estudante");
		Printer.print("9. Sair");
		Printer.print("Escolha uma opção:");
	}

	public void exibirMenu() {
		int opcao;
		do {
			printMenu();
			opcao = reader.lerNum();

			switch (opcao) {
			case 1:
				cadastrarDisciplina();
				break;
			case 2:
				cadastrarEstudante();
				break;
			case 3:
				inserirEstudante();
				break;
			case 4:
				listarTudo();
				break;
			case 5:
				atualizarDisciplina();
				break;
			case 6:
				atualizarEstudante();
				break;
			case 7:
				removerDisciplina();
				break;
			case 8:
				removerEstudante();
				break;
			case 9:
				Printer.print("Saindo...");
				break;
			default:
				Printer.print("ERROR! Tente novamente.");
				break;
			}
		} while (opcao != 9);
	}

	public void cadastrarEstudante() {
		Printer.print("Digite a matrícula do estudante: ");
		int matricula = reader.lerNum();
		Printer.print("Digite o nome do estudante: ");
		String nomeEstudante = reader.ler();
		Printer.print("Digite o nome do curso do estudante: ");
		String curso = reader.ler();
		estudanteController.cadastrarEstudante(matricula, nomeEstudante, curso);
		Printer.print("Estudante cadastrado!");
	}

	public void cadastrarDisciplina() {
		Printer.print("Digite o código da disciplina: ");
		int codDisciplinaLocal = reader.lerNum();
		Printer.print("Digite o nome da disciplina: ");
		String nomeDisciplinaLocal = reader.ler();
		Printer.print("Digite o nome do professor da disciplina: ");
		String nomeProfessorLocal = reader.ler();
		disciplinaController.cadastrarDisciplina(codDisciplinaLocal, nomeDisciplinaLocal, nomeProfessorLocal);
		Printer.print("Disciplina cadastrada!");
	}

	private void inserirEstudante() {
		Printer.print("Digite a matrícula do estudante: ");
		int matriculaEstudante = reader.lerNum();
		Printer.print("Digite o código da disciplina: ");
		int codDisciplina = reader.lerNum();

		if (disciplinaController.inserirEstudanteNaDisciplina(codDisciplina, matriculaEstudante)) {
			Printer.print("Estudante matriculado com sucesso na disciplina!");
		} else {
			Printer.print(
					"Não foi possível matricular o estudante. Verifique a matrícula do estudante e o código da disciplina.");
		}
	}

//    public void listarTudo() {
//        Printer.print("\nDisciplinas cadastradas:");
//        disciplinaController.listarDisciplinas();
//
//        Printer.print("\nEstudantes cadastrados:");
//        estudanteController.listarEstudantes();
//
//    }

	public void listarTudo() {
        Printer.print("\n--- Disciplinas Cadastradas ---");
        List<DisciplinaDTO> disciplinas = disciplinaController.getTodasDisciplinasComEstudantesDTO();
        if (disciplinas == null || disciplinas.isEmpty()) {
            Printer.print("Nenhuma disciplina cadastrada.");
        } else {
            for (DisciplinaDTO dto : disciplinas) {
                Printer.print("Cód: " + dto.getCodDisciplina() + " | Disciplina: " + dto.getNomeDisciplina() + " | Prof.: " + dto.getNomeProfessor());
                // Agora vai imprimir os estudantes
                List<EstudanteDTO> estudantesMatriculados = dto.getEstudantesMatriculados();
                if (estudantesMatriculados == null || estudantesMatriculados.isEmpty()) {
                    Printer.print("  -> Nenhum estudante matriculado nesta disciplina.");
                } else {
                    Printer.print("  Alunos Matriculados:");
                    for (EstudanteDTO estDTO : estudantesMatriculados) {
                        Printer.print("    - Nome: " + estDTO.getNomeEstudante());
                    }
                }
                Printer.print("\n--- Estudantes Cadastrados ---");
            
	             List<EstudanteDTO> todosEstudantes = estudanteController.getTodosEstudantesDTO(); // Assumindo que este método existe no controller
	
	             if (todosEstudantes == null || todosEstudantes.isEmpty()) {
	                 Printer.print("Nenhum estudante cadastrado.");
	             } else {
	                 for (EstudanteDTO dtoE : todosEstudantes) {
	                     Printer.print("Mat: " + dtoE.getMatricula() + " | Nome: " + dtoE.getNomeEstudante() + " | Curso: " + dtoE.getCurso());
	                 }
	             }
	            Printer.print("-----"); 
                
            	}
        	}
        }

	private void atualizarDisciplina() {
		Printer.print("\n--- Atualizar Dados da Disciplina ---");
		Printer.print("\nDigite o código da matéria a ser atualizada: ");
		int codDisciplina = reader.lerNum();
		Printer.print("Novo nome da disciplina: ");
		String nomeDisciplina = reader.ler();
		Printer.print("Novo professor: ");
		String nomeProfessor = reader.ler();
		if (disciplinaController.atualizaDisciplina(codDisciplina, nomeDisciplina, nomeProfessor)) {
			Printer.print("Disciplina atualizada com sucesso!");
		} else {
			Printer.print("Dados inválidos ou disciplina não encontrada. Atualização não realizada.");
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
		Printer.print("Digite a matrícula do estudante a ser removido: ");
		int matriculaParaRemover = reader.lerNum();
		if (estudanteController.removerEstudante(matriculaParaRemover)) {
			Printer.print("Estudante removido com sucesso!");
		} else {
			Printer.print("Estudante não encontrado ou não pôde ser removido.");
		}
	}

	public void removerDisciplina() {
		Printer.print("Digite o código da disciplina a ser removida: ");
		int codParaRemover = reader.lerNum();
		if (disciplinaController.removerDisciplina(codParaRemover)) {
			Printer.print("Disciplina removida com sucesso!");
		} else {
			Printer.print("Disciplina não encontrada ou não pôde ser removida.");
		}
	}
}