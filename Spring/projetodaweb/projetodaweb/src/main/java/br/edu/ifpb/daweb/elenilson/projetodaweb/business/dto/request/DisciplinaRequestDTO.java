package br.edu.ifpb.daweb.elenilson.projetodaweb.business.dto.request;

public class DisciplinaRequestDTO {
    private int codigo;
    private String nome;
    private String professor;

    public DisciplinaRequestDTO() {}

    public DisciplinaRequestDTO(int codigo, String nome, String professor) {
        this.codigo = codigo;
        this.nome = nome;
        this.professor = professor;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }
}
