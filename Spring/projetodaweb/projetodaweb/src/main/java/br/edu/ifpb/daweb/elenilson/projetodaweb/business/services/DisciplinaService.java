package br.edu.ifpb.daweb.elenilson.projetodaweb.business.services;

import java.util.ArrayList;
import java.util.List; // Usar a interface List

// CORREÇÃO: Importar DTOs do pacote correto
import br.edu.ifpb.daweb.elenilson.projetodaweb.business.dto.DisciplinaDTO;
import br.edu.ifpb.daweb.elenilson.projetodaweb.business.dto.EstudanteDTO;
import br.edu.ifpb.daweb.elenilson.projetodaweb.model.entity.Disciplina;
import br.edu.ifpb.daweb.elenilson.projetodaweb.model.entity.Estudante;
import br.edu.ifpb.daweb.elenilson.projetodaweb.model.repository.DisciplinaRepository;
import br.edu.ifpb.daweb.elenilson.projetodaweb.model.repository.EstudanteRepository;

public class DisciplinaService {
    // CORREÇÃO: Repositórios agora são injetados via construtor
    private DisciplinaRepository disciplinaRepository;
    private EstudanteRepository estudanteRepository;

    // CORREÇÃO: Construtor para injeção de dependência
    public DisciplinaService(DisciplinaRepository disciplinaRepository, EstudanteRepository estudanteRepository) {
        this.disciplinaRepository = disciplinaRepository;
        this.estudanteRepository = estudanteRepository;
    }

    /**
     * CORREÇÃO: Retorna uma lista de todas as disciplinas como DTOs.
     * Nome antigo: todasDisciplinas
     */
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

    public DisciplinaDTO encontrarDisciplinaDTOPeloCod(int codDisciplina) {
        Disciplina disciplinaEntity = null;//disciplinaRepository.encontraPeloCod(codDisciplina);
        if (disciplinaEntity != null) {
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

    public void registraDisciplina(int codDisciplina, String nomeDisciplina, String nomeProfessor) {
        Disciplina disciplina = new Disciplina(codDisciplina, nomeDisciplina, nomeProfessor);
        // Certifique-se que o construtor de Disciplina faz: this.estudantes = new ArrayList<>();
        disciplinaRepository.save(disciplina);
    }

    public boolean matricularEstudante(int codDisciplina, int matriculaAluno) {
        // Agora usa as instâncias de repositório injetadas e compartilhadas
        Disciplina disciplina = null;//disciplinaRepository.encontraPeloCod(codDisciplina);
        Estudante estudante = null;//estudanteRepository.encontraPelaMatricula(matriculaAluno); // Crucial!
//        if (disciplina != null && estudante != null) {
//            disciplinaRepository.adicionarEstudanteNaDisciplina(disciplina, estudante);
//            return true;
//        }
        return false; // Falha se disciplina ou estudante não forem encontrados
    }

    public boolean removerDisciplina(int codDisciplina) {
//        Disciplina existe = disciplinaRepository.encontraPeloCod(codDisciplina);
//        if (existe != null) {
//            disciplinaRepository.removerDisciplinaPeloCod(codDisciplina);
//            // Adicional: Remover esta disciplina de qualquer estudante que a referencie (se aplicável em seu modelo)
//            // Adicional: Remover os estudantes desta disciplina da lista de estudantes da disciplina (já feito implicitamente se o obj disciplina é removido)
//            return true;
//        }
        return false;
    }

    public boolean atualizaDisciplina(int codAtual, String nomeAtual, String professorAtual) {
//        Disciplina existente = disciplinaRepository.encontraPeloCod(codAtual);
//        if (existente != null) {
//            // O método no DisciplinaRepository já faz o loop e atualização
//            disciplinaRepository.atualizarDisciplina(codAtual, nomeAtual, professorAtual);
//            return true;
//        }
        return false;
    }
}