package br.edu.ifpb.daweb.elenilson.projetodaweb.business.dto;

public class EstudanteDTO {
    private int matricula;
    private String nomeEstudante;
    private String curso;

    public EstudanteDTO(int matricula, String nomeEstudante, String curso) {
        this.matricula = matricula;
        this.nomeEstudante = nomeEstudante;
        this.curso = curso;
    }

    public int getMatricula() {
        return matricula;
    }

    public String getNomeEstudante() {
        return nomeEstudante;
    }

    public String getCurso() {
        return curso;
    }


    @Override
    public String toString() {
        return "EstudanteDTO{" +
                "matricula=" + matricula +
                ", nomeEstudante='" + nomeEstudante + '\'' +
                ", curso='" + curso + '\'' +
                '}';
    }
}
