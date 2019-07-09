package com.example.twgerenciadortarefas.repositories;

import com.example.twgerenciadortarefas.models.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioTarefa extends JpaRepository<Tarefa, Long> {
}
