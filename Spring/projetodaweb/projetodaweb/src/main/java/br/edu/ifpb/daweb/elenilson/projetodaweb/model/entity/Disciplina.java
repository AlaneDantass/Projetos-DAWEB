package br.edu.ifpb.daweb.elenilson.projetodaweb.model.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Disciplina {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable = false)
	private int codDisciplina;
	@Column(nullable = false)
	private String nomeDisciplina;
	@Column(nullable = false)
	private String nomeProfessor;
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Estudante> estudantes;

	public Disciplina(int codDisciplina, String nomeDisciplina, String nomeProfessor) {
		this.codDisciplina = codDisciplina;
		this.nomeDisciplina = nomeDisciplina;
		this.nomeProfessor = nomeProfessor;
		this.estudantes = new ArrayList<>();
	}

	public Disciplina() {
		super();
	}

	public int getCodDisciplina() {
		return this.codDisciplina;
	}

	public void setCodDisciplina(int codDisciplina) {
		this.codDisciplina = codDisciplina;
	}

	public String getNomeDisciplina() {
		return this.nomeDisciplina;
	}

	public void setNomeDisciplina(String nomeDisciplina) {
		this.nomeDisciplina = nomeDisciplina;
	}

	public String getNomeProfessor() {
		return this.nomeProfessor;
	}

	public void setNomeProfessor(String nomeProfessor) {
		this.nomeProfessor = nomeProfessor;
	}

	public String toString() {
		return "Código da Disciplina: " + this.codDisciplina + ", Nome da Disciplina: " + this.nomeDisciplina
				+ ", Nome do Professor: " + this.nomeProfessor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Estudante> getEstudantes() {
		return estudantes;
	}

	public void setEstudantes(List<Estudante> estudantes) {
		this.estudantes = estudantes;
	}

//	public void removerEstudante(int matricula) {
//		for (int i = 0; i < this.estudantes.size(); ++i) {
//			if (((Estudante) this.estudantes.get(i)).getMatricula() == matricula) {
//				this.estudantes.remove(i);
//				return;
//			}
//		}
//
//	}
}
