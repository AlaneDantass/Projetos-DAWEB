package br.edu.ifpb.daweb.elenilson.projetodaweb.business.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpb.daweb.elenilson.projetodaweb.business.dto.DisciplinaDTO;
import br.edu.ifpb.daweb.elenilson.projetodaweb.business.dto.EstudanteDTO;
import br.edu.ifpb.daweb.elenilson.projetodaweb.model.entity.Disciplina;
import br.edu.ifpb.daweb.elenilson.projetodaweb.model.entity.Estudante;
import br.edu.ifpb.daweb.elenilson.projetodaweb.model.repository.DisciplinaRepository;
import br.edu.ifpb.daweb.elenilson.projetodaweb.model.repository.EstudanteRepository;

@Service
public class DisciplinaService {

    @Autowired
    private DisciplinaRepository disciplinaRepository;
    @Autowired
    private EstudanteRepository estudanteRepository;

    // Lista todas as disciplinas em formato DTO
    public List<DisciplinaDTO> listarTodasDisciplinasDTO() {
        List<Disciplina> disciplinasEntities = disciplinaRepository.findAll();
        List<DisciplinaDTO> disciplinaDTOs = new ArrayList<>();

        if (disciplinasEntities == null) {
            return disciplinaDTOs;
        }

        for (Disciplina disciplinaEntity : disciplinasEntities) {
            DisciplinaDTO disciplinaDTO = new DisciplinaDTO(
                    disciplinaEntity.getCodDisciplina(),
                    disciplinaEntity.getNomeDisciplina(),
                    disciplinaEntity.getNomeProfessor()
            );

            List<EstudanteDTO> estudanteDTOsMatriculados = new ArrayList<>();
            if (disciplinaEntity.getEstudanteDisciplina() != null) {
                for (Estudante estudanteEntity : disciplinaEntity.getEstudanteDisciplina()) {
                    estudanteDTOsMatriculados.add(new EstudanteDTO(
                            estudanteEntity.getMatricula(),
                            estudanteEntity.getNomeEstudante(),
                            estudanteEntity.getCurso()
                    ));
                }
            }
            disciplinaDTO.setEstudantesMatriculados(estudanteDTOsMatriculados);
            disciplinaDTOs.add(disciplinaDTO);
        }
        return disciplinaDTOs;
    }

    // Busca disciplina por código e retorna DTO
    public DisciplinaDTO encontrarDisciplinaDTOPeloCod(int codDisciplina) {
        Optional<Disciplina> disciplinaOpt = disciplinaRepository.findById((long) codDisciplina);

        if (disciplinaOpt.isPresent()) {
            Disciplina disciplinaEntity = disciplinaOpt.get();
            DisciplinaDTO disciplinaDTO = new DisciplinaDTO(
                    disciplinaEntity.getCodDisciplina(),
                    disciplinaEntity.getNomeDisciplina(),
                    disciplinaEntity.getNomeProfessor()
            );

            List<EstudanteDTO> estudanteDTOsMatriculados = new ArrayList<>();
            if (disciplinaEntity.getEstudanteDisciplina() != null) {
                for (Estudante estudanteEntity : disciplinaEntity.getEstudanteDisciplina()) {
                    estudanteDTOsMatriculados.add(new EstudanteDTO(
                            estudanteEntity.getMatricula(),
                            estudanteEntity.getNomeEstudante(),
                            estudanteEntity.getCurso()
                    ));
                }
            }
            disciplinaDTO.setEstudantesMatriculados(estudanteDTOsMatriculados);
            return disciplinaDTO;
        }
        return null;
    }

    // Registra uma nova disciplina
    public void registraDisciplina(int codDisciplina, String nomeDisciplina, String nomeProfessor) {
        Disciplina disciplina = new Disciplina(codDisciplina, nomeDisciplina, nomeProfessor);
        disciplinaRepository.save(disciplina);
    }

    // Matricula estudante em uma disciplina
    public boolean matricularEstudante(int codDisciplina, int matriculaAluno) {
        Optional<Disciplina> disciplinaOpt = disciplinaRepository.findById((long) codDisciplina);
        Optional<Estudante> estudanteOpt = estudanteRepository.findById((long) matriculaAluno);

        if (disciplinaOpt.isPresent() && estudanteOpt.isPresent()) {
            Disciplina disciplina = disciplinaOpt.get();
            Estudante estudante = estudanteOpt.get();

            disciplina.getEstudanteDisciplina().add(estudante);

            disciplinaRepository.save(disciplina);
            return true;
        }
        return false;
    }

    // Remove disciplina pelo código
    public boolean removerDisciplina(int codDisciplina) {
        Optional<Disciplina> disciplinaOpt = disciplinaRepository.findById((long) codDisciplina);

        if (disciplinaOpt.isPresent()) {
            Disciplina disciplina = disciplinaOpt.get();
            disciplinaRepository.delete(disciplina);
            return true;
        }
        return false;
    }

    // Atualiza disciplina existente
    public boolean atualizaDisciplina(int codAtual, String nomeAtual, String professorAtual) {
        Optional<Disciplina> existenteOpt = disciplinaRepository.findById((long) codAtual);

        if (existenteOpt.isPresent()) {
            Disciplina existente = existenteOpt.get();

            existente.setNomeDisciplina(nomeAtual);
            existente.setNomeProfessor(professorAtual);

            disciplinaRepository.save(existente);
            return true;
        }
        return false;
    }
}
