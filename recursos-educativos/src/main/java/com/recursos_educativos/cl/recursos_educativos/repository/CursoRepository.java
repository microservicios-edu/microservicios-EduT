package com.recursos_educativos.cl.recursos_educativos.repository;

import com.recursos_educativos.cl.recursos_educativos.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
}

