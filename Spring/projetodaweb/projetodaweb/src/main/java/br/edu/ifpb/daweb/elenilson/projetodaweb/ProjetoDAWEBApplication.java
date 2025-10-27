package br.edu.ifpb.daweb.elenilson.projetodaweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 👇 Esses imports e serviços do modo console foram comentados.
// import java.util.List;
// import java.util.Scanner;
// import br.edu.ifpb.daweb.elenilson.projetodaweb.business.dto.DisciplinaDTO;
// import br.edu.ifpb.daweb.elenilson.projetodaweb.business.dto.EstudanteDTO;
// import br.edu.ifpb.daweb.elenilson.projetodaweb.business.services.DisciplinaService;
// import br.edu.ifpb.daweb.elenilson.projetodaweb.business.services.EstudanteService;

@SpringBootApplication
public class ProjetoDAWEBApplication {

    // 🔸 Tudo que era usado no menu do console foi comentado, pois agora
    // usamos controladores REST (DisciplinaController e EstudanteController).

//    private final DisciplinaService disciplinaService;
//    private final EstudanteService estudanteService;
//    private final Scanner scanner = new Scanner(System.in);
//
//    public ProjetoDAWEBApplication(DisciplinaService disciplinaService, EstudanteService estudanteService) {
//        this.disciplinaService = disciplinaService;
//        this.estudanteService = estudanteService;
//    }

    public static void main(String[] args) {
        SpringApplication.run(ProjetoDAWEBApplication.class, args);
    }

    // ❌ Não precisamos mais implementar CommandLineRunner.
    // ❌ Também não precisamos mais do método run() nem do menu.
    
//    @Override
//    public void run(String... args) {
//        exibirMenu();
//    }
//
//    private void exibirMenu() {
//        int opcao;
//        do {
//            printMenu();
//            opcao = scanner.nextInt();
//            scanner.nextLine(); // consumir quebra de linha
//
//            switch (opcao) {
//                case 1 -> cadastrarDisciplina();
//                case 2 -> cadastrarEstudante();
//                case 3 -> matricularEstudante();
//                case 4 -> listarTudo();
//                case 5 -> atualizarDisciplina();
//                case 6 -> atualizarEstudante();
//                case 7 -> removerDisciplina();
//                case 8 -> removerEstudante();
//                case 9 -> System.out.println("Saindo...");
//                default -> System.out.println("Opção inválida!");
//            }
//        } while (opcao != 9);
//    }

    // 🟦 Todos os métodos de menu abaixo também foram comentados
    // porque agora essas ações são feitas via endpoints REST no Insomnia.

//    private void printMenu() { ... }
//    private void cadastrarEstudante() { ... }
//    private void cadastrarDisciplina() { ... }
//    private void matricularEstudante() { ... }
//    private void listarTudo() { ... }
//    private void atualizarDisciplina() { ... }
//    private void atualizarEstudante() { ... }
//    private void removerDisciplina() { ... }
//    private void removerEstudante() { ... }

}
