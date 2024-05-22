package com.example.ac22.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DadosCursoDto {
    private Long id;
    private String descricao;
    private String cargaHoraria;
    private String objetivo;
    private String ementa;
    private List<ProfessorDtoInfo> professores;
    private List<Long> agendas;
}
