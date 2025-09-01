package br.edu.ifpb.daweb.elenilson.projetodaweb.business.dto;


import java.util.ArrayList;

//Data Transfer Object (DTO) ou simplesmente Transfer Object é um padrão de projetos bastante usado em Java para o transporte de dados entre diferentes componentes de um sistema, diferentes instâncias ou processos de um sistema distribuído ou diferentes sistemas via serialização.
//A ideia consiste basicamente em agrupar um conjunto de atributos numa classe simples de forma a otimizar a comunicação.

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;


public class DisciplinaDTO {
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private Long id;
    @Column(nullable = false)
	private int codDisciplina;
    @Column(nullable = false)
	private String nomeDisciplina;
    @Column(nullable = false)
	private String nomeProfessor;
	private List<EstudanteDTO> estudantesMatriculados;

  public DisciplinaDTO(int codDisciplina, String nomeDisciplina, String nomeProfessor) {
      this.codDisciplina = codDisciplina;
      this.nomeDisciplina = nomeDisciplina;
      this.nomeProfessor = nomeProfessor;
      this.estudantesMatriculados = new ArrayList<>();
  }

  // Getters
  public int getCodDisciplina() {
      return codDisciplina;
  }

  public String getNomeDisciplina() {
      return nomeDisciplina;
  }

  public String getNomeProfessor() {
      return nomeProfessor;
  }

  public List<EstudanteDTO> getEstudantesMatriculados() {
      return estudantesMatriculados;
  }


  public void setEstudantesMatriculados(List<EstudanteDTO> estudantesMatriculados) {
      this.estudantesMatriculados = estudantesMatriculados;
  }
}