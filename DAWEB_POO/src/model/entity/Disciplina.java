package model.entity;

import java.util.ArrayList;

public class Disciplina {

	private int codDisciplina;
	private String nomeDisciplina;
	private String nomeProfessor;
	private ArrayList<Estudante> estudantes;

	public Disciplina(int codDisciplina, String nomeDisciplina, String nomeProfessor) {
		this.codDisciplina = codDisciplina;
		this.nomeDisciplina = nomeDisciplina;
		this.nomeProfessor = nomeProfessor;
		this.estudantes = new ArrayList<>();
	}

	public int getCodDisciplina() {
		return codDisciplina;
	}

	public void setCodDisciplina(int codDisciplina) {
		this.codDisciplina = codDisciplina;
	}

	public String getNomeDisciplina() {
		return nomeDisciplina;
	}

	public void setNomeDisciplina(String nomeDisciplina) {
		this.nomeDisciplina = nomeDisciplina;
	}

	public String getNomeProfessor() {
		return nomeProfessor;
	}

	public void setNomeProfessor(String nomeProfessor) {
		this.nomeProfessor = nomeProfessor;
	}

	@Override
	public String toString() {
		return "Código da Disciplina: " + codDisciplina + ", Nome da Disciplina: " + nomeDisciplina
				+ ", Nome do Professor: " + nomeProfessor;
	}

	public ArrayList<Estudante> getEstudanteDisciplina() {
		return estudantes;
	}

	/*
	 * public void removerEstudante(int matricula) { for (int i = 0; i <
	 * estudantes.size(); i++) { if (estudantes.get(i).getMatricula() == matricula)
	 * { estudantes.remove(i); return; } } }
	 */

}
