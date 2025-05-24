package presentation.dto;

//Data Transfer Object (DTO) ou simplesmente Transfer Object é um padrão de projetos bastante usado em Java para o transporte de dados entre diferentes componentes de um sistema, diferentes instâncias ou processos de um sistema distribuído ou diferentes sistemas via serialização.
//A ideia consiste basicamente em agrupar um conjunto de atributos numa classe simples de forma a otimizar a comunicação.

import java.util.List;
import java.util.ArrayList;

public class DisciplinaDTO {
    private int codDisciplina;
    private String nomeDisciplina;
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