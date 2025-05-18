package presentation.controller;

import business.utils.Printer;
import business.utils.Reader;
import model.entity.Estudante;

import java.util.ArrayList;

import business.service.EstudanteService;
import business.validation.*;

public class EstudanteController {

	// Classe que recebe os dados iniciais, valida e (se tiver tudo ok) envia para o
	// service

	Printer impressora = new Printer();
	private EstudanteService service;

	public EstudanteController(EstudanteService service) {
		this.service = service;
	}

	public void cadastrarEstudante() {
		Printer.print("\nDigite a matrícula do novo estudante: ");
		int matricula = Reader.lerNum();

		Printer.print("Digite o nome do novo estudante: ");
		String nomeEstudante = Reader.ler();

		Printer.print("Digite o curso do novo estudante: ");
		String curso = Reader.ler();

		Printer.print("Digite o ano escolar que o estudante está cursando: ");
		int ano = Reader.lerNum();

		Printer.print("Digite o email acadêmico do novo estudante: ");
		String email = Reader.ler();

		Estudante estudante = new Estudante(matricula, nomeEstudante, curso, ano, email);

		if (EstudanteValidacao.valido(estudante)) {
			service.registraEstudante(estudante);
			Printer.print("Estudante cadastrado com sucesso!");
		} else {
			Printer.print("Dados inválidos. Cadastro não realizado.");
		}
	}

	public void removerEstudante() {
		Printer.print("Digite a matrícula do estudante a remover: ");
		int matricula = Reader.lerNum();

		if (service.removerEstudante(matricula)) {
			Printer.print("Estudante removido com sucesso!");
		} else {
			Printer.print("Estudante não encontrado.");
		}
	}

	public void atualizarEstudante() {
		Printer.print("\nDigite a matrícula do estudante a ser atualizado: ");
		int matricula = Reader.lerNum();

		Estudante estudanteExistente = service.encontrarPelaMatricula(matricula);

		if (estudanteExistente != null) {

			Printer.print("Novo nome do Estudante: ");
			String nomeEstudante = Reader.ler();

			Printer.print("Novo curso do Estudante: ");
			String curso = Reader.ler();

			Printer.print("Ano escolar atual: ");
			int ano = Reader.lerNum();

			Printer.print("Email atual do Estudante: ");
			String email = Reader.ler();

			Estudante estudanteAtualizado = new Estudante(matricula, nomeEstudante, curso, ano, email);
			if (EstudanteValidacao.valido(estudanteAtualizado)) {
				service.atualizaEstudante(matricula, nomeEstudante, curso, ano, email);
				Printer.print("Estudante atualizado com sucesso!");
			} else {
				Printer.print("Dados inválidos.");
			}
		} else {
			Printer.print("Estudante não encontrado.");
		}
	}

	public void listarEstudantes() {
		ArrayList<Estudante> todosEstudantes = service.listarTodosEstudantes();
		Printer.print("----------------ESTUDANTES-----------------");
		for (Estudante e : todosEstudantes) {
			Printer.print("Matrícula: " + e.getMatricula());
			Printer.print("Nome: " + e.getNomeEstudante());
			Printer.print("Curso: " + e.getCurso());
			Printer.print("Email: " + e.getEmail());
			Printer.print("-----------------------------------");
		}
	}

}
