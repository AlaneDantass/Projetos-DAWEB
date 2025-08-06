package br.edu.ifpb.daweb.elenilson.projetodaweb.presentation;

//
//Source code recreated from a .class file by IntelliJ IDEA
//(powered by FernFlower decompiler)
//

import br.edu.ifpb.daweb.elenilson.projetodaweb.business.dto.DisciplinaDTO;
import br.edu.ifpb.daweb.elenilson.projetodaweb.business.dto.EstudanteDTO;
import br.edu.ifpb.daweb.elenilson.projetodaweb.business.services.DisciplinaService;
import br.edu.ifpb.daweb.elenilson.projetodaweb.business.services.EstudanteService;
import br.edu.ifpb.daweb.elenilson.projetodaweb.business.services.Printer;
import br.edu.ifpb.daweb.elenilson.projetodaweb.business.services.Reader;
import java.util.List;
import br.edu.ifpb.daweb.elenilson.projetodaweb.model.repository.DisciplinaRepository;
import br.edu.ifpb.daweb.elenilson.projetodaweb.model.repository.EstudanteRepository;
import br.edu.ifpb.daweb.elenilson.projetodaweb.presentation.controller.DisciplinaController;
import br.edu.ifpb.daweb.elenilson.projetodaweb.presentation.controller.EstudanteController;

public class Main {
 private final DisciplinaRepository disciplinaRepositoryInstance = new DisciplinaRepository();
 private final EstudanteRepository estudanteRepositoryInstance = new EstudanteRepository();
 private final DisciplinaService disciplinaServiceInstance;
 private final EstudanteService estudanteServiceInstance;
 private final EstudanteController estudanteController;
 private final DisciplinaController disciplinaController;
 private final Reader reader;

 public Main() {
     this.disciplinaServiceInstance = new DisciplinaService(this.disciplinaRepositoryInstance, this.estudanteRepositoryInstance);
     this.estudanteServiceInstance = new EstudanteService(this.estudanteRepositoryInstance, this.disciplinaRepositoryInstance);
     this.estudanteController = new EstudanteController(this.estudanteServiceInstance);
     this.disciplinaController = new DisciplinaController(this.disciplinaServiceInstance);
     this.reader = new Reader();
 }

 public static void main(String[] args) {
     Main mainApp = new Main();
     mainApp.exibirMenu();
 }

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
         opcao = this.reader.lerNum();
         switch (opcao) {
             case 1:
                 this.cadastrarDisciplina();
                 break;
             case 2:
                 this.cadastrarEstudante();
                 break;
             case 3:
                 this.inserirEstudante();
                 break;
             case 4:
                 this.listarTudo();
                 break;
             case 5:
                 this.atualizarDisciplina();
                 break;
             case 6:
                 this.atualizarEstudante();
                 break;
             case 7:
                 this.removerDisciplina();
                 break;
             case 8:
                 this.removerEstudante();
                 break;
             case 9:
                 Printer.print("Saindo...");
                 break;
             default:
                 Printer.print("ERROR! Tente novamente.");
         }
     } while(opcao != 9);

 }

 public void cadastrarEstudante() {
     Printer.print("Digite a matrícula do estudante: ");
     int matricula = this.reader.lerNum();
     Printer.print("Digite o nome do estudante: ");
     String nomeEstudante = this.reader.ler();
     Printer.print("Digite o nome do curso do estudante: ");
     String curso = this.reader.ler();
     this.estudanteController.cadastrarEstudante(matricula, nomeEstudante, curso);
     Printer.print("Estudante cadastrado!");
 }

 public void cadastrarDisciplina() {
     Printer.print("Digite o código da disciplina: ");
     int codDisciplinaLocal = this.reader.lerNum();
     Printer.print("Digite o nome da disciplina: ");
     String nomeDisciplinaLocal = this.reader.ler();
     Printer.print("Digite o nome do professor da disciplina: ");
     String nomeProfessorLocal = this.reader.ler();
     this.disciplinaController.cadastrarDisciplina(codDisciplinaLocal, nomeDisciplinaLocal, nomeProfessorLocal);
     Printer.print("Disciplina cadastrada!");
 }

 private void inserirEstudante() {
     Printer.print("Digite a matrícula do estudante: ");
     int matriculaEstudante = this.reader.lerNum();
     Printer.print("Digite o código da disciplina: ");
     int codDisciplina = this.reader.lerNum();
     if (this.disciplinaController.inserirEstudanteNaDisciplina(codDisciplina, matriculaEstudante)) {
         Printer.print("Estudante matriculado com sucesso na disciplina!");
     } else {
         Printer.print("Não foi possível matricular o estudante. Verifique a matrícula do estudante e o código da disciplina.");
     }

 }

 public void listarTudo() {
     Printer.print("\n--- Disciplinas Cadastradas ---");
     List<DisciplinaDTO> disciplinas = this.disciplinaController.getTodasDisciplinasComEstudantesDTO();
     if (disciplinas != null && !disciplinas.isEmpty()) {
         for(DisciplinaDTO dto : disciplinas) {
             int var10000 = dto.getCodDisciplina();
             Printer.print("Cód: " + var10000 + " | Disciplina: " + dto.getNomeDisciplina() + " | Prof.: " + dto.getNomeProfessor());
             List<EstudanteDTO> estudantesMatriculados = dto.getEstudantesMatriculados();
             if (estudantesMatriculados != null && !estudantesMatriculados.isEmpty()) {
                 Printer.print("  Alunos Matriculados:");

                 for(EstudanteDTO estDTO : estudantesMatriculados) {
                     Printer.print("    - Nome: " + estDTO.getNomeEstudante());
                 }
             } else {
                 Printer.print("  -> Nenhum estudante matriculado nesta disciplina.");
             }

             Printer.print("\n--- Estudantes Cadastrados ---");
             List<EstudanteDTO> todosEstudantes = this.estudanteController.getTodosEstudantesDTO();
             if (todosEstudantes != null && !todosEstudantes.isEmpty()) {
                 for(EstudanteDTO dtoE : todosEstudantes) {
                     var10000 = dtoE.getMatricula();
                     Printer.print("Mat: " + var10000 + " | Nome: " + dtoE.getNomeEstudante() + " | Curso: " + dtoE.getCurso());
                 }
             } else {
                 Printer.print("Nenhum estudante cadastrado.");
             }

             Printer.print("-----");
         }
     } else {
         Printer.print("Nenhuma disciplina cadastrada.");
     }

 }

 private void atualizarDisciplina() {
     Printer.print("\n--- Atualizar Dados da Disciplina ---");
     Printer.print("\nDigite o código da matéria a ser atualizada: ");
     int codDisciplina = this.reader.lerNum();
     Printer.print("Novo nome da disciplina: ");
     String nomeDisciplina = this.reader.ler();
     Printer.print("Novo professor: ");
     String nomeProfessor = this.reader.ler();
     if (this.disciplinaController.atualizaDisciplina(codDisciplina, nomeDisciplina, nomeProfessor)) {
         Printer.print("Disciplina atualizada com sucesso!");
     } else {
         Printer.print("Dados inválidos ou disciplina não encontrada. Atualização não realizada.");
     }

 }

 private void atualizarEstudante() {
     Printer.print("\n--- Atualizar Dados do Estudante ---");
     Printer.print("Digite a matrícula do estudante a ser atualizado: ");
     int matricula = this.reader.lerNum();
     Printer.print("Digite o novo nome do estudante: ");
     String novoNome = this.reader.ler();
     Printer.print("Digite o novo curso do estudante: ");
     String novoCurso = this.reader.ler();
     if (this.estudanteController.atualizaEstudante(matricula, novoNome, novoCurso)) {
         Printer.print("Dados do estudante atualizados com sucesso!");
     } else {
         Printer.print("Não foi possível atualizar os dados do estudante. Verifique os dados ou a matrícula.");
     }

 }

 public void removerEstudante() {
     Printer.print("Digite a matrícula do estudante a ser removido: ");
     int matriculaParaRemover = this.reader.lerNum();
     if (this.estudanteController.removerEstudante(matriculaParaRemover)) {
         Printer.print("Estudante removido com sucesso!");
     } else {
         Printer.print("Estudante não encontrado ou não pôde ser removido.");
     }

 }

 public void removerDisciplina() {
     Printer.print("Digite o código da disciplina a ser removida: ");
     int codParaRemover = this.reader.lerNum();
     if (this.disciplinaController.removerDisciplina(codParaRemover)) {
         Printer.print("Disciplina removida com sucesso!");
     } else {
         Printer.print("Disciplina não encontrada ou não pôde ser removida.");
     }

 }
}

