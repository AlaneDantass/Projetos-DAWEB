package br.edu.ifpb.daweb.elenilson.projetodaweb.presentation.controller;

import java.util.List; // Usar a interface List


// CORREÇÃO: Importar DTOs do pacote correto
import br.edu.ifpb.daweb.elenilson.projetodaweb.business.dto.DisciplinaDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import br.edu.ifpb.daweb.elenilson.projetodaweb.business.services.DisciplinaService;
import br.edu.ifpb.daweb.elenilson.projetodaweb.business.services.Printer;
import br.edu.ifpb.daweb.elenilson.projetodaweb.business.services.Validation;

@Controller
public class DisciplinaController {
    // CORREÇÃO: Serviço agora é injetado via construtor
	@Autowired
    private DisciplinaService disciplinaService;
    @Autowired
    private Validation disciplinaValidacao; //Alteração do new

    // CORREÇÃO: Construtor para injeção de dependência
    public DisciplinaController(DisciplinaService disciplinaService) {
        this.disciplinaService = disciplinaService;
    }

    public void cadastrarDisciplina(int codDisciplina, String nomeDisciplina, String nomeProfessor) {
        if (disciplinaValidacao.disciplinaValida(codDisciplina, nomeDisciplina, nomeProfessor)) {
            disciplinaService.registraDisciplina(codDisciplina, nomeDisciplina, nomeProfessor);
        }
    }

    public boolean inserirEstudanteNaDisciplina(int codDisciplina, int matriculaAluno) {
        return disciplinaService.matricularEstudante(codDisciplina, matriculaAluno);
    } 

    /**
     * CORREÇÃO: Retorna uma lista de todas as disciplinas como DTOs.
     * Nome antigo: listarDisciplinas (que era void)
     */
//    public void listarDisciplinas(){
//    	 List<DisciplinaDTO> lista = disciplinaService.listarTodasDisciplinasDTO();
//
//        if (lista.isEmpty()) {
//            Printer.print("Nenhuma disciplina cadastrada.");
//        } else {
//            for (DisciplinaDTO d : lista) {
//                Printer.print("\n-" + d.getNomeDisciplina());
//            }
//        }
//    }

    public List<DisciplinaDTO> getTodasDisciplinasComEstudantesDTO() {
        return disciplinaService.listarTodasDisciplinasDTO();
    }

    public boolean atualizaDisciplina(int codAtual, String nomeAtual, String professorAtual) {
        if (disciplinaValidacao.disciplinaValida(codAtual, nomeAtual, professorAtual)) {
            return disciplinaService.atualizaDisciplina(codAtual, nomeAtual, professorAtual);
        } else {
            return false;
        }
    }

    public boolean removerDisciplina(int codDisciplina) {
        return disciplinaService.removerDisciplina(codDisciplina);
    }
}