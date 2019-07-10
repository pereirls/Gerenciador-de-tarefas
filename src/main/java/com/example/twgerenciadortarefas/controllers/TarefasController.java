package com.example.twgerenciadortarefas.controllers;

import com.example.twgerenciadortarefas.models.Tarefa;
import com.example.twgerenciadortarefas.models.Usuario;
import com.example.twgerenciadortarefas.repositories.RepositorioTarefa;
import com.example.twgerenciadortarefas.servicos.ServicoUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;

@Controller
@RequestMapping("/tarefas")
public class TarefasController {

    @Autowired
    private RepositorioTarefa repositorioTarefa;

    @Autowired
    private ServicoUsuario servicoUsuario;

    @GetMapping("/listar")
    public ModelAndView listar(HttpServletRequest request) {

        ModelAndView mv = new ModelAndView();
        mv.setViewName("tarefas/listar");
        String emailUsuario = request.getUserPrincipal().getName() ;
        mv.addObject("tarefas", repositorioTarefa.carregarTarefasPorUsuario(emailUsuario));
        return mv;
    }

    @GetMapping("/inserir")
    public ModelAndView inserir() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("tarefas/inserir");
        mv.addObject("tarefa", new Tarefa());
        return mv;
    }

    @PostMapping("/inserir")
    public ModelAndView inserir(@Valid Tarefa tarefa, BindingResult result, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        if(tarefa.getDataExpiracao()==null || tarefa.getDataExpiracao().before(new Date())){
            result.rejectValue("dataExpiracao","tarefa.dataExpiracaoInvalida",
                    "Data de expiração vazia ou menor que a data atual");
        }
        if(!result.hasErrors()){
            String emailUsuario = request.getUserPrincipal().getName() ;
            Usuario usuarioLogado = servicoUsuario.econtrarPorEmail(emailUsuario);
            tarefa.setUsuario(usuarioLogado);
            mv.setViewName( "redirect:/tarefas/listar");
            repositorioTarefa.save(tarefa);
        }else{
            mv.setViewName("tarefas/inserir");
            mv.addObject(tarefa);
        }
        return mv;
    }

    @GetMapping("/alterar/{id}")
    public ModelAndView alterar(@PathVariable("id") Long id) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("tarefas/alterar");
        Tarefa tarefa = repositorioTarefa.getOne(id);
        mv.addObject("tarefa", tarefa);
        return mv;
    }

    @PostMapping("/alterar")
    public ModelAndView alterar(@Valid Tarefa tarefa, BindingResult result) {
        ModelAndView mv = new ModelAndView();
        if(tarefa.getDataExpiracao()==null || tarefa.getDataExpiracao().before(new Date())){
            result.rejectValue("dataExpiracao","tarefa.dataExpiracaoInvalida",
                    "Data de expiração vazia ou menor que a data atual");
        }
        if(!result.hasErrors()){
            mv.setViewName( "redirect:/tarefas/listar");
            repositorioTarefa.save(tarefa);
        }else{
            mv.setViewName("tarefas/alterar");
            mv.addObject(tarefa);
        }
        return mv;
    }

    @GetMapping("/excluir/{id}")
    public String ecluir(@PathVariable("id") Long id) {
        repositorioTarefa.deleteById(id);
        return "redirect:/tarefas/listar";
    }

    @GetMapping("/concluir/{id}")
    public String concluit(@PathVariable("id") Long id) {
        Tarefa tarefa = repositorioTarefa.getOne(id);
        tarefa.setConcluida(true);
        repositorioTarefa.save(tarefa);
        return "redirect:/tarefas/listar";
    }
}
