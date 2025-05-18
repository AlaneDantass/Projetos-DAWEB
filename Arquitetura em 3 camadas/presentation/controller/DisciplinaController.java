package presentation.controller;

import java.util.ArrayList;
import model.entity.Disciplina;
import model.entity.Estudante;
import business.service.DisciplinaService;
import business.service.EstudanteService;
import business.validation.DisciplinaValidacao;
import business.validation.EstudanteValidacao;
import business.utils.Reader;
import business.utils.Printer;

public class DisciplinaController {
	private DisciplinaService service;
	private EstudanteService serviceEstudante;

//Classe que recebe os dados iniciais, valida e (se tiver tudo ok) envia para o service

	public DisciplinaController(DisciplinaService service, EstudanteService serviceEstudante) {
		this.service = service;
		this.serviceEstudante = serviceEstudante;
	}

	public void cadastrarDisciplina() {
		Printer.print("\nDigite o código da nova disciplina: ");
		int codDisciplina = Reader.lerNum();

		Printer.print("Digite o nome da nova disciplina: ");
		String nomeDisciplina = Reader.ler();

		Printer.print("Digite o nome do professor que vai lecionar essa disciplina: ");
		String nomeProfessor = Reader.ler();

		Disciplina disciplina = new Disciplina(codDisciplina, nomeDisciplina, nomeProfessor);

		if (DisciplinaValidacao.valida(disciplina)) {
			service.registraDisciplina(disciplina);
			Printer.print("Disciplina cadastrada com sucesso!");
		} else {
			Printer.print("Dados inválidos. Cadastro não realizado.");
		}
	}

	public void removerDisciplina() {
		Printer.print("\nDigite o código da disciplina a ser removida: ");
		int codDisciplina = Reader.lerNum();
		if (service.removerDisciplina(codDisciplina)) {
			Printer.print("Disciplina removida com sucesso!");
		} else {
			Printer.print("Disciplina não encontrada.");
		}
	}

	public void matricularEstudante() {
		Printer.print("Digite a matrícula do estudante que deseja matricular:");
		int matriculaEstudante = Reader.lerNum();

		Printer.print("Digite o código da disciplina que deseja matriculá-lo: ");
		int codDaDisciplina = Reader.lerNum();

		Disciplina disciplina = service.encontrarPeloCod(codDaDisciplina);
		Estudante estudante = serviceEstudante.encontrarPelaMatricula(matriculaEstudante);

		if (DisciplinaValidacao.valida(disciplina) && EstudanteValidacao.valido(estudante)) {
			service.matricularEstudante(codDaDisciplina, matriculaEstudante);
			Printer.print("Estudante matriculado com sucesso!");
		} else {
			Printer.print("Erro: Disciplina ou estudante inválido(a). Matrícula não realizada.");
		}
	}

	public void listarDisciplinas() {
		ArrayList<Disciplina> disciplinas = service.listarDisciplinas();
		Printer.print("------------------DISCIPLINAS-----------------");
		for (Disciplina d : disciplinas) {
			Printer.print("Código: " + d.getCodDisciplina());
			Printer.print("Nome: " + d.getNomeDisciplina());
			Printer.print("Professor: " + d.getNomeProfessor());
			Printer.print("Alunos Matriculados:");
			for (Estudante e : d.getEstudanteDisciplina()) {
				Printer.print("  - " + e.getNomeEstudante());
			}
		}
	}

	public void atualizarDisciplina() {
		Printer.print("\nDigite o código da matéria a ser atualizada: ");
		int codDisciplina = Reader.lerNum();

		Disciplina disciplinaExistente = service.encontrarPeloCod(codDisciplina);
		;
		if (disciplinaExistente != null) {

			// Printer.print("Novo código : ");
			// int novoCod = Reader.lerNum();

			Printer.print("Novo nome da disciplina: ");
			String nomeDisciplina = Reader.ler();

			Printer.print("Novo professor: ");
			String nomeProfessor = Reader.ler();

			Disciplina disciplinaAtualizada = new Disciplina(codDisciplina, nomeDisciplina, nomeProfessor);
			if (DisciplinaValidacao.valida(disciplinaAtualizada)) {
				service.atualizaDisciplina(codDisciplina, nomeDisciplina, nomeProfessor);
				Printer.print("Disciplina atualizada com sucesso!");
			} else {
				Printer.print("Dados inválidos. Atualização não realizada.");
			}
		} else {
			Printer.print("Disciplina não encontrada.");
		}
	}
}
