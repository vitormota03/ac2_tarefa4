package com.example.ac22.services;

import com.example.ac22.dtos.ProfessorDto;

import java.util.List;

public interface ProfessorService {
    void inserir(ProfessorDto dto);
    List<ProfessorDto> obterTodos();
}
