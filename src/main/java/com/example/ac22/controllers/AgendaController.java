package com.example.ac22.controllers;

import com.example.ac22.dtos.AgendaDto;
import com.example.ac22.services.AgendaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/agenda")
public class AgendaController {

    @Autowired
    AgendaServiceImpl service;

    @PostMapping
    public void inserir(@RequestBody AgendaDto agenda){
        service.inserir(agenda);
    }

    @GetMapping
    public List<AgendaDto> obterTodos(){
        return service.obterTodos();
    }

    @GetMapping("{id}/{data}")
    public List<AgendaDto> agendasProfessorPorData(@PathVariable Long id, @PathVariable LocalDate data){
        return service.agendaProfessorPorData(id, data);
    }

    @GetMapping("{id}")
    public List<AgendaDto> agendasProfessor(@PathVariable Long id){
        return service.agendasProfessor(id);
    }

    @PutMapping
    public void adicionarResumo(@RequestBody AgendaDto agendaDto){
        service.adicionarResumo(agendaDto);
    }
}
