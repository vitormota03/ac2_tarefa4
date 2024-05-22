package com.example.ac22.services;

import com.example.ac22.dtos.AgendaDto;

import java.time.LocalDate;
import java.util.List;

public interface AgendaService {
    void inserir(AgendaDto dto);
    List<AgendaDto> obterTodos();
    List<AgendaDto> agendaProfessorPorData(Long id, LocalDate data);
    List<AgendaDto> agendasProfessor(Long id);
    void adicionarResumo(AgendaDto resumo);
}
