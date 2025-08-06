package br.edu.ifpb.daweb.elenilson.projetodaweb.model.entity;

import java.util.ArrayList;

//
//Source code recreated from a .class file by IntelliJ IDEA
//(powered by FernFlower decompiler)
//
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
     this.estudantes = new ArrayList();
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
     return "Código da Disciplina: " + this.codDisciplina + ", Nome da Disciplina: " + this.nomeDisciplina + ", Nome do Professor: " + this.nomeProfessor;
 }

 public ArrayList<Estudante> getEstudanteDisciplina() {
     return this.estudantes;
 }

 public void removerEstudante(int matricula) {
     for(int i = 0; i < this.estudantes.size(); ++i) {
         if (((Estudante)this.estudantes.get(i)).getMatricula() == matricula) {
             this.estudantes.remove(i);
             return;
         }
     }

 }
}
