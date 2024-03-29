package com.example.twgerenciadortarefas.repositories;

import com.example.twgerenciadortarefas.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioUsuario extends JpaRepository<Usuario, Long> {

    Usuario findByEmail(String Email);


}
