package com.example.ac22.repositories;

import com.example.ac22.models.Agenda;
import com.example.ac22.models.Professor;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {

    @Query("select a from Agenda a where a.professor.id = :id")
    List<Agenda> agendasDoProfessor(@Param("id") Long id);
}
