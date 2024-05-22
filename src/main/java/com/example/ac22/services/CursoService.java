package com.example.ac22.services;

import com.example.ac22.dtos.CursoDto;
import com.example.ac22.dtos.DadosCursoDto;

import java.util.List;

public interface CursoService {
    void inserir(CursoDto cursoDto);
    List<CursoDto> obterTodos();
    DadosCursoDto obterProfessoresPorCurso(Long id);
}
