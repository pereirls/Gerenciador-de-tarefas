package com.example.twgerenciadortarefas.controllers;

import com.example.twgerenciadortarefas.models.Usuario;
import com.example.twgerenciadortarefas.servicos.ServicoUsuario;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class ContaController {

    @Autowired
    private ServicoUsuario servicoUsuario;

    @GetMapping("/login")
    public String login() {
        return "conta/login";
    }

    @GetMapping("/registration")
    public ModelAndView registrar() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("conta/registrar");
        mv.addObject("usuario", new Usuario());
        return mv;
    }

    @PostMapping("/registration")
    public ModelAndView registrar(@Valid Usuario usuario,
                                  BindingResult result) {

        ModelAndView mv = new ModelAndView();
        Usuario usr = servicoUsuario.econtrarPorEmail(usuario.getEmail());
        if(usr!=null) {
            result.rejectValue("email", "", "Usuario já cadastrado");
        }
        if(result.hasErrors()) {
            mv.setViewName("conta/registrar");
            mv.addObject("usuario", usuario);
        }else {
            servicoUsuario.salvar(usuario);
            mv.setViewName("redirect:/login");
        }
        return mv;
    }
}
