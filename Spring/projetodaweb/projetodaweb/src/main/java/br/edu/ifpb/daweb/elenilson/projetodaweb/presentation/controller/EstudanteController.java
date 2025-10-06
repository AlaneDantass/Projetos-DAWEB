package br.edu.ifpb.daweb.elenilson.projetodaweb.presentation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import br.edu.ifpb.daweb.elenilson.projetodaweb.business.dto.EstudanteDTO;
import br.edu.ifpb.daweb.elenilson.projetodaweb.business.dto.request.EstudanteRequestDTO;
import br.edu.ifpb.daweb.elenilson.projetodaweb.business.dto.response.EstudanteResponseDTO;
import br.edu.ifpb.daweb.elenilson.projetodaweb.business.services.EstudanteService;
import br.edu.ifpb.daweb.elenilson.projetodaweb.business.services.Validation;

@RestController
@RequestMapping(value = "/api/v1/estudantes") // ← endpoint base da API de estudantes
public class EstudanteController {

    @Autowired
    private Validation validation;

    @Autowired
    private EstudanteService estudanteService;

    // ✅ Cadastrar novo estudante (POST)
    @PostMapping
    public EstudanteResponseDTO cadastrarEstudante(@RequestBody EstudanteRequestDTO dto) {
        if (validation.estudanteValido(dto.getMatricula(), dto.getNome(), dto.getCurso())) {
            estudanteService.cadastrarEstudante(dto.getMatricula(), dto.getNome(), dto.getCurso());
            return new EstudanteResponseDTO(dto.getMatricula(), dto.getNome(), dto.getCurso());
        }
        return null;
    }

    // ✅ Listar todos os estudantes (GET)
    @GetMapping
    public List<EstudanteDTO> getTodosEstudantesDTO() {
        return estudanteService.listarTodosEstudantesDTO();
    }

    // ✅ Buscar estudante por matrícula (GET)
    @GetMapping("/{matricula}")
    public EstudanteDTO getEstudanteDTOPorMatricula(@PathVariable int matricula) {
        return estudanteService.encontrarEstudanteDTOPelaMatricula(matricula);
    }

    // ✅ Atualizar estudante (PUT)
    @PutMapping("/{matricula}")
    public boolean atualizaEstudante(@PathVariable int matricula, @RequestBody EstudanteRequestDTO dto) {
        if (validation.estudanteValido(matricula, dto.getNome(), dto.getCurso())) {
            return estudanteService.atualizaEstudante(matricula, dto.getNome(), dto.getCurso());
        } else {
            return false;
        }
    }

    // ✅ Remover estudante (DELETE)
    @DeleteMapping("/{matricula}")
    public boolean removerEstudante(@PathVariable int matricula) {
        return estudanteService.removerEstudante(matricula);
    }
}
