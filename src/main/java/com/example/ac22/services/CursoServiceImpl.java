package com.example.ac22.services;

import com.example.ac22.dtos.CursoDto;
import com.example.ac22.dtos.DadosCursoDto;
import com.example.ac22.dtos.ProfessorDtoInfo;
import com.example.ac22.exceptions.RegraNegocioException;
import com.example.ac22.models.Curso;
import com.example.ac22.repositories.CursoRepository;
import com.example.ac22.repositories.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CursoServiceImpl implements CursoService{

    @Autowired
    ProfessorRepository professorRepository;

    @Autowired
    CursoRepository cursoRepository;

    @Override
    public void inserir(CursoDto cursoDto) {
        Curso curso = new Curso();
        curso.setDescricao(cursoDto.getDescricao());
        curso.setCargaHoraria(cursoDto.getCargaHoraria());
        curso.setObjetivo(cursoDto.getObjetivo());
        curso.setEmenta(cursoDto.getEmenta());
        cursoRepository.save(curso);
    }

    @Override
    public List<CursoDto> obterTodos() {
        List<CursoDto> cursos = cursoRepository.findAll().stream().map(c -> {
            return CursoDto.builder()
                    .id(c.getId())
                    .descricao(c.getDescricao())
                    .cargaHoraria(c.getCargaHoraria())
                    .objetivo(c.getObjetivo())
                    .ementa(c.getEmenta())
                    .professoresId(c.getProfessores() == null ? null : c.getProfessores().stream().map(p -> p.getId()).collect(Collectors.toList()))
                    .agendasId(c.getAgendas() == null ? null : c.getAgendas().stream().map(a -> a.getId()).collect(Collectors.toList()))
                    .build();
        }).collect(Collectors.toList());
        return cursos;
    }

    @Override
    public DadosCursoDto obterProfessoresPorCurso(Long id) {
        Curso curso = cursoRepository.findCursoProfessorFetchCursos(id).orElseThrow(() -> new RegraNegocioException("Id do curso não encontrado"));
        return DadosCursoDto.builder()
                .id(curso.getId())
                .descricao(curso.getDescricao())
                .cargaHoraria(curso.getCargaHoraria())
                .objetivo(curso.getObjetivo())
                .ementa(curso.getEmenta())
                .professores(curso.getProfessores().stream().map(p -> {
                    return ProfessorDtoInfo.builder()
                            .id(p.getId())
                            .nome(p.getNome())
                            .cpf(p.getCpf())
                            .endereco(p.getEndereco())
                            .build();
                }).collect(Collectors.toList()))
                .agendas(curso.getAgendas() == null ? null : curso.getAgendas().stream().map(a -> a.getId()).collect(Collectors.toList()))
                .build();
    }
}
