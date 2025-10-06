package br.edu.ifpb.daweb.elenilson.projetodaweb.presentation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifpb.daweb.elenilson.projetodaweb.business.dto.DisciplinaDTO;
import br.edu.ifpb.daweb.elenilson.projetodaweb.business.dto.request.DisciplinaRequestDTO;
import br.edu.ifpb.daweb.elenilson.projetodaweb.business.dto.response.DisciplinaResponseDTO;
import br.edu.ifpb.daweb.elenilson.projetodaweb.business.services.DisciplinaService;
import br.edu.ifpb.daweb.elenilson.projetodaweb.business.services.Validation;

@RestController
@RequestMapping(value = "/api/v1/disciplinas") // ← endpoint base da API de disciplinas
public class DisciplinaController {

    @Autowired
    private DisciplinaService disciplinaService;

    @Autowired
    private Validation disciplinaValidacao;

    // Cadastrar nova disciplina (POST)
    @PostMapping
    public DisciplinaResponseDTO cadastrarDisciplina(@RequestBody DisciplinaRequestDTO dto) {
        if (disciplinaValidacao.disciplinaValida(dto.getCodigo(), dto.getNome(), dto.getProfessor())) {
            disciplinaService.registraDisciplina(dto.getCodigo(), dto.getNome(), dto.getProfessor());
            return new DisciplinaResponseDTO(dto.getCodigo(), dto.getNome(), dto.getProfessor());
        }
        return null;
    }
    
 //  Matricular estudante em disciplina existente
    @PostMapping("/{codigoDisciplina}/matricular/{matriculaEstudante}")
    public boolean matricularEstudante(
            @PathVariable int codigoDisciplina,
            @PathVariable int matriculaEstudante) {

        return disciplinaService.matricularEstudante(codigoDisciplina, matriculaEstudante);
    }
 // ✅ Listar todas as disciplinas (GET)
    @GetMapping
    public List<DisciplinaDTO> getTodasDisciplinasComEstudantesDTO() {
        return disciplinaService.listarTodasDisciplinasDTO();
    }

    @PutMapping("/{codigo}")
    public boolean atualizarDisciplina(
            @PathVariable int codigo,
            @RequestBody DisciplinaRequestDTO dto) {

        if (disciplinaValidacao.disciplinaValida(dto.getCodigo(), dto.getNome(), dto.getProfessor())) {
            return disciplinaService.atualizaDisciplina(codigo, dto.getNome(), dto.getProfessor());
        }
        return false;
   
    }
    @DeleteMapping("/{codigo}")
    public boolean removerDisciplina(@PathVariable int codigo) {
        return disciplinaService.removerDisciplina(codigo);
    }
}
