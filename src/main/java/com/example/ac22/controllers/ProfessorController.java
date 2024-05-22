package com.example.ac22.controllers;

import com.example.ac22.dtos.ProfessorDto;
import com.example.ac22.services.ProfessorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/professor")
public class ProfessorController {

    @Autowired
    ProfessorServiceImpl service;

    @PostMapping
    public void inserir(@RequestBody ProfessorDto professor){
        service.inserir(professor);
    }

    @GetMapping
    public List<ProfessorDto> obterTodos(){
        return service.obterTodos();
    }
}
