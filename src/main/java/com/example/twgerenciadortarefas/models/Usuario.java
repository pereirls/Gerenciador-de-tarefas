package com.example.twgerenciadortarefas.models;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "usr_usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usr_id")
    private Long id;

    @Column(name = "usr_email", length = 100, nullable = false)
    @NotNull(message = "O e-mail é obrigatório")
    @Length(min = 5, max = 100, message = "O e-mail deve conter entre 5 e 100 caracteres")
    private String email;

    @Column(name = "usr_senha", length = 100, nullable = false)
    @NotNull(message = "A senha é obrigatória")
    private String senha;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
