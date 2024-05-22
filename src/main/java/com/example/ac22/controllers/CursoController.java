package com.example.ac22.controllers;

import com.example.ac22.dtos.CursoDto;
import com.example.ac22.dtos.DadosCursoDto;
import com.example.ac22.services.CursoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/curso")
public class CursoController {

    @Autowired
    CursoServiceImpl service;

    @PostMapping
    public void inserir(@RequestBody CursoDto curso) {
        service.inserir(curso);
    }

    @GetMapping
    public List<CursoDto> obterTodos(){
        return service.obterTodos();
    }

    @GetMapping("{id}")
    public DadosCursoDto professoresDoCurso(@PathVariable Long id){
        return service.obterProfessoresPorCurso(id);
    }

}
