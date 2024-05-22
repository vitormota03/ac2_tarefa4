package com.example.ac22.repositories;

import com.example.ac22.models.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
    @Query("select c from Curso c left join fetch c.professores p where c.id = :id ")
    Optional<Curso> findCursoProfessorFetchCursos(@Param("id") Long id);
}
