package br.edu.ifpb.daweb.elenilson.projetodaweb.presentation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.edu.ifpb.daweb.elenilson.projetodaweb.business.dto.DisciplinaDTO;
import br.edu.ifpb.daweb.elenilson.projetodaweb.business.dto.request.DisciplinaRequestDTO;
import br.edu.ifpb.daweb.elenilson.projetodaweb.business.dto.response.DisciplinaResponseDTO;
import br.edu.ifpb.daweb.elenilson.projetodaweb.business.services.DisciplinaService;
import br.edu.ifpb.daweb.elenilson.projetodaweb.business.services.Validation;

@RestController
@RequestMapping("/api/v1/disciplinas")
public class DisciplinaController {

    @Autowired
    private DisciplinaService disciplinaService;

    @Autowired
    private Validation disciplinaValidacao;


    @PostMapping
    public ResponseEntity<?> cadastrarDisciplina(@RequestBody DisciplinaRequestDTO dto) {
        if (!disciplinaValidacao.disciplinaValida(dto.getCodigo(), dto.getNome(), dto.getProfessor())) {
            return ResponseEntity.badRequest().body("Dados inválidos para cadastrar disciplina.");
        }

        disciplinaService.registraDisciplina(dto.getCodigo(), dto.getNome(), dto.getProfessor());
        DisciplinaResponseDTO response = new DisciplinaResponseDTO(dto.getCodigo(), dto.getNome(), dto.getProfessor());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @PostMapping("/{codigoDisciplina}/matricular/{matriculaEstudante}")
    public ResponseEntity<?> matricularEstudante(
            @PathVariable int codigoDisciplina,
            @PathVariable int matriculaEstudante) {

        boolean matriculado = disciplinaService.matricularEstudante(codigoDisciplina, matriculaEstudante);
        if (matriculado) {
            return ResponseEntity.ok("Estudante matriculado com sucesso na disciplina " + codigoDisciplina);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Falha ao matricular estudante. Verifique se os dados são válidos.");
        }
    }


    @GetMapping
    public ResponseEntity<List<DisciplinaDTO>> listarDisciplinas() {
        List<DisciplinaDTO> lista = disciplinaService.listarTodasDisciplinasDTO();
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204
        }
        return ResponseEntity.ok(lista); // 200
    }


    @PutMapping("/{codigo}")
    public ResponseEntity<?> atualizarDisciplina(
            @PathVariable int codigo,
            @RequestBody DisciplinaRequestDTO dto) {

        if (!disciplinaValidacao.disciplinaValida(dto.getCodigo(), dto.getNome(), dto.getProfessor())) {
            return ResponseEntity.badRequest().body("Dados inválidos para atualização da disciplina.");
        }

        boolean atualizada = disciplinaService.atualizaDisciplina(codigo, dto.getNome(), dto.getProfessor());
        if (atualizada) {
            DisciplinaResponseDTO response = new DisciplinaResponseDTO(codigo, dto.getNome(), dto.getProfessor());
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Disciplina não encontrada para atualização.");
        }
    }


    @DeleteMapping("/{codigo}")
    public ResponseEntity<?> removerDisciplina(@PathVariable int codigo) {
        boolean removida = disciplinaService.removerDisciplina(codigo);
        if (removida) {
            return ResponseEntity.noContent().build(); // 204
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Disciplina não encontrada para exclusão.");
        }
    }
    
}
