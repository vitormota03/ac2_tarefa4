package com.example.ac22.repositories;

import com.example.ac22.models.Agenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AgendaRepository extends JpaRepository<Agenda, Long> {
    @Query("select a from Agenda a where a.dataInicial <= :data and a.dataFinal >= :data and a.professor.id = :id")
    List<Agenda> agendasProfessorPorData(@Param("id") Long id,
            @Param("data") LocalDate data);

   
}
