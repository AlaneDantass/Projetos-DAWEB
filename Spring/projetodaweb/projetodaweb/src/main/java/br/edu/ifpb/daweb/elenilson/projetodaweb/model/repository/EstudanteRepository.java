package br.edu.ifpb.daweb.elenilson.projetodaweb.model.repository;

import java.util.ArrayList;
import  br.edu.ifpb.daweb.elenilson.projetodaweb.model.entity.Estudante;

public class EstudanteRepository {
    private ArrayList<Estudante> estudantes = new ArrayList<>();

    public ArrayList<Estudante> getEstudantes() {
        return estudantes;
    }

    public void cadastrarEstudante(Estudante estudante) {
        estudantes.add(estudante);
    }

    public boolean atualizarEstudante(int matricula, String novoNome, String novoCurso) {
        Estudante estudanteParaAtualizar = encontraPelaMatricula(matricula);
        // CORREÇÃO: Adicionada verificação de nulidade para evitar NullPointerException
        if (estudanteParaAtualizar != null) {
            estudanteParaAtualizar.setNomeEstudante(novoNome);
            // CORREÇÃO: Use o nome do setter da sua entidade Estudante.
            // Se o setter em Estudante.java for setCurso, mantenha como está.
            // Se for setCursoEstudante, ajuste aqui.
            estudanteParaAtualizar.setCurso(novoCurso);
            return true;
        }
        return false; // Retorna false se o estudante não for encontrado
    }

    public void deletarEstudantePelaMatricula(int matricula) {
        Estudante estudanteParaRemover = encontraPelaMatricula(matricula);
        if (estudanteParaRemover != null) {
            estudantes.remove(estudanteParaRemover);
        }
    }

    public Estudante encontraPelaMatricula(int matricula) {
        for (Estudante e : estudantes) {
            if (e.getMatricula() == matricula) {
                return e;
            }
        }
        return null;
    }
}
