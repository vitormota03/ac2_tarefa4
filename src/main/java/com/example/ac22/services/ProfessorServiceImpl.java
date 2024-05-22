package com.example.ac22.services;

import com.example.ac22.dtos.ProfessorDto;
import com.example.ac22.exceptions.RegraNegocioException;
import com.example.ac22.models.Curso;
import com.example.ac22.models.Professor;
import com.example.ac22.repositories.CursoRepository;
import com.example.ac22.repositories.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfessorServiceImpl implements ProfessorService{
    @Autowired
    ProfessorRepository professorRepository;

    @Autowired
    CursoRepository cursoRepository;
    @Override
    public void inserir(ProfessorDto dto) {

        List<Curso> cursos = dto.getCursosId().stream().map(l -> cursoRepository.findById(l).orElseThrow(() -> new RegraNegocioException("Curso não encontrado"))).collect(Collectors.toList());
        Professor professor = new Professor();
        professor.setNome(dto.getNome());
        professor.setCpf(dto.getCpf());
        professor.setEndereco(dto.getEndereco());
        professor.setTelefone(dto.getTelefone());
        professorRepository.save(professor);
        professor = professorRepository.findById(professor.getId()).orElseThrow(() -> new RegraNegocioException("Professor não encontrado"));
        professor.setCursos(cursos);
        professorRepository.save(professor);
    }

    @Override
    public List<ProfessorDto> obterTodos() {
        List<ProfessorDto> professor = professorRepository.findAll()
                .stream().map( p -> {
                    return ProfessorDto.builder()
                            .id(p.getId())
                            .nome(p.getNome())
                            .cpf(p.getCpf())
                            .endereco(p.getEndereco())
                            .telefone(p.getTelefone())
                            .cursosId(p.getCursos() == null ? null : p.getCursos().stream().map(c -> c.getId()).collect(Collectors.toList()))
                            .agendaId(p.getAgendas() == null ? null : p.getAgendas().stream().map(a -> a.getId()).collect(Collectors.toList()))
                            .build();
                }).collect(Collectors.toList());
        return professor;
    }
}
