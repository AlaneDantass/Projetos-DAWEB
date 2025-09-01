package br.edu.ifpb.daweb.elenilson.projetodaweb;

import java.util.List;
import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.edu.ifpb.daweb.elenilson.projetodaweb.business.dto.DisciplinaDTO;
import br.edu.ifpb.daweb.elenilson.projetodaweb.business.dto.EstudanteDTO;
import br.edu.ifpb.daweb.elenilson.projetodaweb.business.services.DisciplinaService;
import br.edu.ifpb.daweb.elenilson.projetodaweb.business.services.EstudanteService;

@SpringBootApplication
public class ProjetoDAWEBApplication implements CommandLineRunner {

    private final DisciplinaService disciplinaService;
    private final EstudanteService estudanteService;
    private final Scanner scanner = new Scanner(System.in);

    public ProjetoDAWEBApplication(DisciplinaService disciplinaService, EstudanteService estudanteService) {
        this.disciplinaService = disciplinaService;
        this.estudanteService = estudanteService;
    }

    public static void main(String[] args) {
        SpringApplication.run(ProjetoDAWEBApplication.class, args);
    }

    @Override
    public void run(String... args) {
        exibirMenu();
    }

    private void exibirMenu() {
        int opcao;
        do {
            printMenu();
            opcao = scanner.nextInt();
            scanner.nextLine(); // consumir quebra de linha

            switch (opcao) {
                case 1 -> cadastrarDisciplina();
                case 2 -> cadastrarEstudante();
                case 3 -> matricularEstudante();
                case 4 -> listarTudo();
                case 5 -> atualizarDisciplina();
                case 6 -> atualizarEstudante();
                case 7 -> removerDisciplina();
                case 8 -> removerEstudante();
                case 9 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 9);
    }

    private void printMenu() {
        System.out.println("\n===== Menu =====");
        System.out.println("1. Cadastrar disciplina");
        System.out.println("2. Cadastrar estudante");
        System.out.println("3. Matricular estudante em disciplina");
        System.out.println("4. Listar disciplinas e estudantes");
        System.out.println("5. Editar disciplina");
        System.out.println("6. Editar estudante");
        System.out.println("7. Remover disciplina");
        System.out.println("8. Remover estudante");
        System.out.println("9. Sair");
        System.out.print("Escolha: ");
    }

    private void cadastrarEstudante() {
        System.out.print("Matrícula: ");
        int matricula = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Curso: ");
        String curso = scanner.nextLine();

        estudanteService.cadastrarEstudante(matricula, nome, curso);
        System.out.println("Estudante cadastrado!");
    }

    private void cadastrarDisciplina() {
        System.out.print("Código: ");
        int cod = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Professor: ");
        String professor = scanner.nextLine();

        disciplinaService.registraDisciplina(cod, nome, professor);
        System.out.println("Disciplina cadastrada!");
    }

    private void matricularEstudante() {
        System.out.print("Matrícula do estudante: ");
        int matricula = scanner.nextInt();
        System.out.print("Código da disciplina: ");
        int cod = scanner.nextInt();

        if (disciplinaService.matricularEstudante(cod, matricula)) {
            System.out.println("Estudante matriculado!");
        } else {
            System.out.println("Erro ao matricular.");
        }
    }

    private void listarTudo() {
        System.out.println("\n--- Disciplinas ---");
        List<DisciplinaDTO> disciplinas = disciplinaService.listarTodasDisciplinasDTO();
        disciplinas.forEach(d -> {
            System.out.println("Cód: " + d.getCodDisciplina() +
                               " | " + d.getNomeDisciplina() +
                               " | Prof: " + d.getNomeProfessor());
            if (d.getEstudantesMatriculados() != null) {
                d.getEstudantesMatriculados()
                 .forEach(e -> System.out.println("   - " + e.getNomeEstudante()));
            }
        });

        System.out.println("\n--- Estudantes ---");
        List<EstudanteDTO> estudantes = estudanteService.listarTodosEstudantesDTO();
        estudantes.forEach(e ->
            System.out.println("Mat: " + e.getMatricula() + " | " + e.getNomeEstudante() + " | Curso: " + e.getCurso())
        );
    }

    private void atualizarDisciplina() {
        System.out.print("Código: ");
        int cod = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Novo nome: ");
        String nome = scanner.nextLine();
        System.out.print("Novo professor: ");
        String professor = scanner.nextLine();

        if (disciplinaService.atualizaDisciplina(cod, nome, professor)) {
            System.out.println("Disciplina atualizada!");
        } else {
            System.out.println("Disciplina não encontrada.");
        }
    }

    private void atualizarEstudante() {
        System.out.print("Matrícula: ");
        int matricula = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Novo nome: ");
        String nome = scanner.nextLine();
        System.out.print("Novo curso: ");
        String curso = scanner.nextLine();

        if (estudanteService.atualizaEstudante(matricula, nome, curso)) {
            System.out.println("Estudante atualizado!");
        } else {
            System.out.println("Estudante não encontrado.");
        }
    }

    private void removerDisciplina() {
        System.out.print("Código: ");
        int cod = scanner.nextInt();
        if (disciplinaService.removerDisciplina(cod)) {
            System.out.println("Disciplina removida!");
        } else {
            System.out.println("Disciplina não encontrada.");
        }
    }

    private void removerEstudante() {
        System.out.print("Matrícula: ");
        int matricula = scanner.nextInt();
        if (estudanteService.removerEstudante(matricula)) {
            System.out.println("Estudante removido!");
        } else {
            System.out.println("Estudante não encontrado.");
        }
    }
}
