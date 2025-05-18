package model.entity;

public class Estudante {

	private int matricula;
	private String nomeEstudante;
	private String curso;
	private int ano;
	private String email;

	public Estudante(int matricula, String nomeEstudante, String curso, int ano, String email) {
		this.matricula = matricula;
		this.nomeEstudante = nomeEstudante;
		this.curso = curso;
		this.ano = ano;
		this.email = email;
	}

	public int getMatricula() {
		return matricula;
	}

	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}

	public String getNomeEstudante() {
		return nomeEstudante;
	}

	public void setNomeEstudante(String nomeEstudante) {
		this.nomeEstudante = nomeEstudante;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Matricula: " + matricula + ", Nome: " + nomeEstudante + ", Curso: " + curso + ", Ano: " + ano
				+ ", Email: " + email;
	}

}