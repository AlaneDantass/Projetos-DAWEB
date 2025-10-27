package br.edu.ifpb.daweb.elenilson.projetodaweb.presentation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.edu.ifpb.daweb.elenilson.projetodaweb.business.dto.EstudanteDTO;
import br.edu.ifpb.daweb.elenilson.projetodaweb.business.dto.request.EstudanteRequestDTO;
import br.edu.ifpb.daweb.elenilson.projetodaweb.business.dto.response.EstudanteResponseDTO;
import br.edu.ifpb.daweb.elenilson.projetodaweb.business.services.EstudanteService;
import br.edu.ifpb.daweb.elenilson.projetodaweb.business.services.Validation;

@RestController
@RequestMapping("/api/v1/estudantes")
public class EstudanteController {

    @Autowired
    private Validation validation;

    @Autowired
    private EstudanteService estudanteService;


    @PostMapping
    public ResponseEntity<?> cadastrarEstudante(@RequestBody EstudanteRequestDTO dto) {
        if (!validation.estudanteValido(dto.getMatricula(), dto.getNome(), dto.getCurso())) {
            return ResponseEntity.badRequest().body("Dados inválidos para cadastrar estudante.");
        }

        estudanteService.cadastrarEstudante(dto.getMatricula(), dto.getNome(), dto.getCurso());
        EstudanteResponseDTO response = new EstudanteResponseDTO(dto.getMatricula(), dto.getNome(), dto.getCurso());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @GetMapping
    public ResponseEntity<List<EstudanteDTO>> listarEstudantes() {
        List<EstudanteDTO> lista = estudanteService.listarTodosEstudantesDTO();
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204
        }
        return ResponseEntity.ok(lista); // 200
    }


    @GetMapping("/{matricula}")
    public ResponseEntity<?> buscarEstudantePorMatricula(@PathVariable int matricula) {
        EstudanteDTO estudante = estudanteService.encontrarEstudanteDTOPelaMatricula(matricula);
        if (estudante != null) {
            return ResponseEntity.ok(estudante);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Estudante não encontrado.");
        }
    }


    @PutMapping("/{matricula}")
    public ResponseEntity<?> atualizarEstudante(
            @PathVariable int matricula,
            @RequestBody EstudanteRequestDTO dto) {

        if (!validation.estudanteValido(matricula, dto.getNome(), dto.getCurso())) {
            return ResponseEntity.badRequest().body("Dados inválidos para atualização.");
        }

        boolean atualizado = estudanteService.atualizaEstudante(matricula, dto.getNome(), dto.getCurso());
        if (atualizado) {
            EstudanteResponseDTO response = new EstudanteResponseDTO(matricula, dto.getNome(), dto.getCurso());
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Estudante não encontrado para atualização.");
        }
    }


    @DeleteMapping("/{matricula}")
    public ResponseEntity<?> removerEstudante(@PathVariable int matricula) {
        boolean removido = estudanteService.removerEstudante(matricula);
        if (removido) {
            return ResponseEntity.noContent().build(); // 204
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Estudante não encontrado para exclusão.");
        }
    }
}
