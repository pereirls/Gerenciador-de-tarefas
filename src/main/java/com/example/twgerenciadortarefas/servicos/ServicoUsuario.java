package com.example.twgerenciadortarefas.servicos;

import com.example.twgerenciadortarefas.models.Usuario;
import com.example.twgerenciadortarefas.repositories.RepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ServicoUsuario {

    @Autowired
    private RepositorioUsuario repositorioUsuario;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public Usuario econtrarPorEmail(String email) {
        return repositorioUsuario.findByEmail(email);
    }

    public void salvar(Usuario usuario) {
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        repositorioUsuario.save(usuario);
    }
}
