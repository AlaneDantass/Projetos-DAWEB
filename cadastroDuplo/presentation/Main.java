package presentation;

// Importações de DTOs (do pacote business.dto como você preferiu)
import business.dto.DisciplinaDTO;
import business.dto.EstudanteDTO;

// Importações de Serviços e Utilitários
import business.services.Printer;
import business.services.Reader;
import business.services.DisciplinaService; // Usado pelos controllers
import business.services.EstudanteService;   // Usado pelos controllers


import presentation.Controller.DisciplinaController;
import presentation.Controller.EstudanteController;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        Main main = new Main(); // necessário para acessar métodos não estáticos
        main.exibirMenu();
    }
    EstudanteController estudanteController = new EstudanteController(null);
    DisciplinaController disciplinaController = new DisciplinaController(null);

    Reader reader = new Reader();
    // Printer printer = new Printer(); // Removido se Printer.print for estático

    private static void printMenu() { // Mantido estático, ok
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
                    inserirEstudante(); // Renomeado na sua versão para inserirEstudante
                    break;
                case 4:
                    listarTudo();
                    break;
                case 5:
                    // CORREÇÃO DE LÓGICA DO MENU: Opção 5 é Editar Disciplina no seu printMenu
                    atualizarDisciplina();
                    break;
                case 6:
                    // CORREÇÃO DE LÓGICA DO MENU: Opção 6 é Editar Estudante no seu printMenu
                    atualizarEstudante();
                    break;
                case 7:
                    // CORREÇÃO DE LÓGICA DO MENU: Opção 7 é Remover Disciplina no seu printMenu
                    removerDisciplina();
                    break;
                case 8:
                    // CORREÇÃO DE LÓGICA DO MENU: Opção 8 é Remover Estudante no seu printMenu
                    removerEstudante();
                    break;
                case 9:
                    Printer.print("Saindo...");
                    // reader.fechar(); // Removido conforme sua solicitação, pois não existe em Reader
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
        String nomeEstudante = reader.ler(); // Correto usar ler() para String

        Printer.print("Digite o nome do curso do estudante: ");
        String curso = reader.ler(); // Correto usar ler() para String

        estudanteController.cadastrarEstudante(matricula, nomeEstudante, curso);
        Printer.print("Estudante cadastrado!"); // Feedback
    }

    public void cadastrarDisciplina() {
        Printer.print("Digite o código da disciplina: ");
        // COMENTÁRIO/SUGESTÃO: Nome da variável local poderia ser 'codDisciplina' para clareza
        int codDisciplinaLocal = reader.lerNum();

        Printer.print("Digite o nome da disciplina: ");
        // COMENTÁRIO/SUGESTÃO: Nome da variável local poderia ser 'nomeDisciplina'
        String nomeDisciplinaLocal = reader.ler();

        Printer.print("Digite o nome do professor da disciplina: ");
        // COMENTÁRIO/SUGESTÃO: Nome da variável local poderia ser 'nomeProfessor'
        String nomeProfessorLocal = reader.ler();

        // Passando as variáveis com os dados corretos para o controller
        disciplinaController.cadastrarDisciplina(codDisciplinaLocal, nomeDisciplinaLocal, nomeProfessorLocal);
        Printer.print("Disciplina cadastrada!"); // Feedback
    }

    private void inserirEstudante() { // Nome do seu método
        Printer.print("Digite a matrícula do estudante: ");
        int matriculaEstudante = reader.lerNum();

        Printer.print("Digite o código da disciplina: ");
        int codDisciplina = reader.lerNum();

        if (disciplinaController.inserirEstudanteNaDisciplina(codDisciplina, matriculaEstudante)) {
            Printer.print("Estudante matriculado com sucesso na disciplina!");
        } else {
            Printer.print("Não foi possível matricular o estudante. Verifique a matrícula do estudante e o código da disciplina.");
            // COMENTÁRIO: Esta falha provavelmente ocorre porque a DisciplinaService usa uma instância
            // diferente de EstudanteRepository daquela usada por EstudanteService para cadastrar o estudante.
            // A solução ideal é Injeção de Dependência ou repositórios com listas estáticas.
        }
    }

    public void listarTudo() {
        Printer.print("\n--- Disciplinas Cadastradas ---");
        // CORREÇÃO: Capturar a lista de DTOs e iterar sobre ela para imprimir
        // Assumindo que disciplinaController.getTodasDisciplinasDTO() retorna List<DisciplinaDTO>
        List<DisciplinaDTO> disciplinas = disciplinaController.getTodasDisciplinasDTO();
        if (disciplinas == null || disciplinas.isEmpty()) {
            Printer.print("Nenhuma disciplina cadastrada.");
        } else {
            for (DisciplinaDTO dto : disciplinas) {
                Printer.print("Cód: " + dto.getCodDisciplina() + " | Disciplina: " + dto.getNomeDisciplina() + " | Prof.: " + dto.getNomeProfessor());
                if (dto.getEstudantesMatriculados() == null || dto.getEstudantesMatriculados().isEmpty()) {
                    Printer.print("  -> Nenhum estudante matriculado.");
                } else {
                    Printer.print("  Alunos Matriculados:");
                    for (EstudanteDTO estDTO : dto.getEstudantesMatriculados()) {
                        Printer.print("    - Mat: " + estDTO.getMatricula() + ", Nome: " + estDTO.getNomeEstudante() + ", Curso: " + estDTO.getCurso());
                    }
                }
                Printer.print("-----");
            }
        }

        Printer.print("\n--- Estudantes Cadastrados ---");
        // CORREÇÃO: Capturar a lista de DTOs e iterar sobre ela para imprimir
        // Assumindo que estudanteController.getTodosEstudantesDTO() retorna List<EstudanteDTO>
        List<EstudanteDTO> estudantes = estudanteController.getTodosEstudantesDTO();
        if (estudantes == null || estudantes.isEmpty()) {
            Printer.print("Nenhum estudante cadastrado.");
        } else {
            for (EstudanteDTO dto : estudantes) {
                Printer.print("Mat: " + dto.getMatricula() + " | Nome: " + dto.getNomeEstudante() + " | Curso: " + dto.getCurso());
            }
        }
        Printer.print("-----");
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
        // CORREÇÃO: Ler a matrícula do usuário em vez de usar 0
        int matriculaParaRemover = reader.lerNum();
        if (estudanteController.removerEstudante(matriculaParaRemover)) {
            Printer.print("Estudante removido com sucesso!");
        } else {
            Printer.print("Estudante não encontrado ou não pôde ser removido.");
        }
    }

    public void removerDisciplina() {
        Printer.print("Digite o código da disciplina a ser removida: ");
        // CORREÇÃO: Ler o código do usuário em vez de usar 0
        int codParaRemover = reader.lerNum();
        if (disciplinaController.removerDisciplina(codParaRemover)) {
            Printer.print("Disciplina removida com sucesso!");
        } else {
            Printer.print("Disciplina não encontrada ou não pôde ser removida.");
        }
    }
}