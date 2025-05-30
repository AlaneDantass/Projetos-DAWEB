package model.repository;

import java.util.ArrayList;
import model.entity.Disciplina;
import model.entity.Estudante;

public class DisciplinaRepository {
	  private ArrayList<Disciplina> disciplinas = new ArrayList<>();

	  public void cadastrarDisciplina(Disciplina disciplina) {
	        disciplinas.add(disciplina);
	    }

	  public ArrayList<Disciplina> listarTodasDisciplinas() {
	        return disciplinas;
	    }

	   public void adicionarEstudanteNaDisciplina(Disciplina disciplina, Estudante estudante) {
	          for (Disciplina d : disciplinas) {
	              if (d.getCodDisciplina() == disciplina.getCodDisciplina()) {
	                  d.getEstudanteDisciplina().add(estudante);
	                  return;
	              }
	          }
	      }

	  public void removerDisciplinaPeloCod(int codDisciplina) {
	        Disciplina paraRemover = encontraPeloCod(codDisciplina);
	        if (paraRemover != null) {
	            disciplinas.remove(paraRemover);
	        }
	    }


	  public Disciplina encontraPeloCod (int codDisciplina) {
	        for (Disciplina d : disciplinas) {
	            if (d.getCodDisciplina() == codDisciplina) {
	                return d;
	            }
	        }
	        return null;
	    }

//		public void deletarEstudantePelaMatricula(int matricula) {
//		    for (Disciplina disciplina : disciplinas) {
//		        ArrayList<Estudante> estudantes = disciplina.getEstudanteDisciplina();
//		        estudantes.removeIf(estudante -> estudante.getMatricula() == matricula);
//		    }
//		}

	  public void atualizarDisciplina(int codAtual, String nomeAtual, String professorAtual) {
	        for (Disciplina d :listarTodasDisciplinas()){
	            if (d.getCodDisciplina() == codAtual){
	                d.setNomeDisciplina(nomeAtual);
	                d.setNomeProfessor(professorAtual);
	                break;
	            } 
	        }
	    }

	  public void deletarEstudantePelaMatricula(int matricula) {
	      for (Disciplina disciplina : disciplinas) {
	          ArrayList<Estudante> estudantes = disciplina.getEstudanteDisciplina();
	          for (int i = 0; i < estudantes.size(); i++) {
	              if (estudantes.get(i).getMatricula() == matricula) {
	                  estudantes.remove(i);
	                  break; 
	              }
	          }
	      }
	  }


	}
