package br.edu.ifpb.daweb.elenilson.projetodaweb.presentation.controller;


import java.util.List; // Importar a interface List

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import br.edu.ifpb.daweb.elenilson.projetodaweb.business.dto.EstudanteDTO;
import br.edu.ifpb.daweb.elenilson.projetodaweb.business.services.EstudanteService;
import br.edu.ifpb.daweb.elenilson.projetodaweb.business.services.Printer;
import br.edu.ifpb.daweb.elenilson.projetodaweb.business.services.Validation;

@Controller
public class EstudanteController {

	@Autowired
    private Validation validation; //Alteração do new
	@Autowired
    private EstudanteService estudanteService; // Serviço será injetado

    public EstudanteController(EstudanteService estudanteService) {
        this.estudanteService = estudanteService;
    }

    public void cadastrarEstudante(int matricula, String nomeEstudante, String curso) {
        if (validation.estudanteValido(matricula, nomeEstudante, curso)) {
            estudanteService.cadastrarEstudante(matricula, nomeEstudante, curso);
        } else {
        }
    }

//	public void listarEstudantes() {
//		List<EstudanteDTO> lista = estudanteService.listarTodosEstudantesDTO();
//
//		if (lista.isEmpty()) {
//			Printer.print("Nenhum estudante cadastrado.");
//		} else {
//			for (EstudanteDTO e : lista) {
//				Printer.print("\n-" + e.getNomeEstudante());
//			}
//		}
//	}

    public List<EstudanteDTO> getTodosEstudantesDTO() {
        return estudanteService.listarTodosEstudantesDTO();
    }

    public EstudanteDTO getEstudanteDTOPorMatricula(int matricula) {
        return estudanteService.encontrarEstudanteDTOPelaMatricula(matricula);
    }

    public boolean atualizaEstudante(int matricula, String novoNome, String novoCurso) {
        if (validation.estudanteValido(matricula, novoNome, novoCurso)) {
            return estudanteService.atualizaEstudante(matricula, novoNome, novoCurso);
        } else {
            return false;
        }
    }

    public boolean removerEstudante(int matricula) {
        return estudanteService.removerEstudante(matricula);
    }
}

