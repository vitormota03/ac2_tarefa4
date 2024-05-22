package com.example.ac22.services;

import com.example.ac22.dtos.AgendaDto;
import com.example.ac22.exceptions.RegraNegocioException;
import com.example.ac22.models.Agenda;
import com.example.ac22.models.Curso;
import com.example.ac22.models.Professor;
import com.example.ac22.repositories.AgendaRepository;
import com.example.ac22.repositories.CursoRepository;
import com.example.ac22.repositories.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AgendaServiceImpl implements AgendaService{

    @Autowired
    AgendaRepository agendaRepository;

    @Autowired
    ProfessorRepository professorRepository;

    @Autowired
    CursoRepository cursoRepository;

    @Override
    public void inserir(AgendaDto dto) {
        Professor professor = professorRepository.findById(dto.getProfessorId()).orElseThrow(() -> new RegraNegocioException("Id do professor não encontrado!"));
        Curso curso = cursoRepository.findById(dto.getCursoId()).orElseThrow(() -> new RegraNegocioException("Id do curso não encontrado!"));
        Agenda agenda = new Agenda();
        agenda.setDataInicial(dto.getDataInicial());
        agenda.setDataFinal(dto.getDataFinal());
        agenda.setHorarioInicial(dto.getHorarioInicial());
        agenda.setHorarioFinal(dto.getHorarioFinal());
        agenda.setProfessor(professor);
        agenda.setCidade(dto.getCidade());
        agenda.setEstado(dto.getEstado());
        agenda.setCep(dto.getCep());
        agenda.setCurso(curso);
        agendaRepository.save(agenda);
    }

    @Override
    public List<AgendaDto> obterTodos() {
        List<AgendaDto> agendas = agendaRepository.findAll().stream().map(a -> {
            return AgendaDto.builder()
                    .id(a.getId())
                    .dataInicial(a.getDataInicial())
                    .dataFinal(a.getDataFinal())
                    .horarioInicial(a.getHorarioInicial())
                    .horarioFinal(a.getHorarioFinal())
                    .professorId(a.getProfessor() == null ? null :a.getProfessor().getId())
                    .cidade(a.getCidade())
                    .estado(a.getEstado())
                    .cep(a.getCep())
                    .cursoId(a.getCurso().getId() == null ? null :a.getCurso().getId())
                    .resumo(a.getResumo())
                    .build();
        }).collect(Collectors.toList());
        return agendas;
    }

    @Override
    public List<AgendaDto> agendaProfessorPorData(Long id, LocalDate data) {
        List<AgendaDto> agendas = agendaRepository.agendasProfessorPorData(id, data).stream().map(a -> {
            return AgendaDto.builder()
                    .id(a.getId())
                    .dataInicial(a.getDataInicial())
                    .dataFinal(a.getDataFinal())
                    .horarioInicial(a.getHorarioInicial())
                    .horarioFinal(a.getHorarioFinal())
                    .professorId(a.getProfessor() == null ? null :a.getProfessor().getId())
                    .cidade(a.getCidade())
                    .estado(a.getEstado())
                    .cep(a.getCep())
                    .cursoId(a.getCurso().getId() == null ? null :a.getCurso().getId())
                    .resumo(a.getResumo())
                    .build();
        }).collect(Collectors.toList());
        return agendas;
    }

    @Override
    public List<AgendaDto> agendasProfessor(Long id) {
        List<AgendaDto> agendas = professorRepository.agendasDoProfessor(id).stream().map(a -> {
            return AgendaDto.builder()
                    .id(a.getId())
                    .dataInicial(a.getDataInicial())
                    .dataFinal(a.getDataFinal())
                    .horarioInicial(a.getHorarioInicial())
                    .horarioFinal(a.getHorarioFinal())
                    .professorId(a.getProfessor() == null ? null :a.getProfessor().getId())
                    .cidade(a.getCidade())
                    .estado(a.getEstado())
                    .cep(a.getCep())
                    .cursoId(a.getCurso().getId() == null ? null :a.getCurso().getId())
                    .resumo(a.getResumo())
                    .build();
        }).collect(Collectors.toList());
        return agendas;
    }

    @Override
    public void adicionarResumo(AgendaDto dto) {
        Professor professor = professorRepository.findById(dto.getProfessorId()).orElseThrow(() -> new RegraNegocioException("Id do professor não encontrado!"));
        Curso curso = cursoRepository.findById(dto.getCursoId()).orElseThrow(() -> new RegraNegocioException("Id do curso não encontrado!"));
        Agenda agenda = new Agenda();
        agenda.setId(dto.getId());
        agenda.setDataInicial(dto.getDataInicial());
        agenda.setDataFinal(dto.getDataFinal());
        agenda.setHorarioInicial(dto.getHorarioInicial());
        agenda.setHorarioFinal(dto.getHorarioFinal());
        agenda.setProfessor(professor);
        agenda.setCidade(dto.getCidade());
        agenda.setEstado(dto.getEstado());
        agenda.setCep(dto.getCep());
        agenda.setCurso(curso);
        agenda.setResumo(dto.getResumo());
        agendaRepository.save(agenda);
    }
}
