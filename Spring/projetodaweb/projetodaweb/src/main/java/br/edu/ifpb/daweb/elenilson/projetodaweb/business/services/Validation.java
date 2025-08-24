package br.edu.ifpb.daweb.elenilson.projetodaweb.business.services;

import org.springframework.stereotype.Service;

@Service
public class Validation {
    public boolean estudanteValido(int matricula, String nomeEstudante, String curso) {
        return matricula > 0 && nomeEstudante != null && curso != null;
    }

    public boolean disciplinaValida(int cod, String nomeDisciplina, String professor) {
        return cod > 0 && nomeDisciplina != null && professor != null;
    }
}
