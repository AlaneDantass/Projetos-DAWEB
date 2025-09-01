package br.edu.ifpb.daweb.elenilson.projetodaweb.model.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Estudante {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
	private int matricula;
    @Column(nullable = false)
    private String nomeEstudante;
    @Column(nullable = false)
    private String curso;
 
//	private List<Disciplina> disciplinas = new ArrayList<>();

    public Estudante(int matricula, String nomeEstudante, String curso) {
        this.matricula = matricula;
        this.nomeEstudante = nomeEstudante;
        this.curso = curso;
    }
//    public List<Disciplina> getDisciplinaEstudante() {
//        return this.disciplinas;
//    }
    
    
    
    public int getMatricula() {
        return matricula;
    }

    public Estudante() {
		super();
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

    @Override
    public String toString() {
        return "Matricula: " + matricula + ", Nome: " + nomeEstudante + ", Curso: " + curso;
    }

}