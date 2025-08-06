package br.edu.ifpb.daweb.elenilson.projetodaweb.model.entity;

public class Estudante {

    private int matricula;
    private String nomeEstudante;
    private String curso;

    public Estudante(int matricula, String nomeEstudante, String curso) {
        this.matricula = matricula;
        this.nomeEstudante = nomeEstudante;
        this.curso = curso;
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

    @Override
    public String toString() {
        return "Matricula: " + matricula + ", Nome: " + nomeEstudante + ", Curso: " + curso;
    }

}