package br.edu.ceepplg.agenda.controller;


import br.edu.ceepplg.agenda.model.Contato;
import br.edu.ceepplg.agenda.service.ContatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/contatos")
public class ContatoController {

    @Autowired
    private ContatoService service;

    @GetMapping("/cadastro")
    public String form(Model model, Contato contato) {
        model.addAttribute("contato", contato);
        return "contato/form";
    }

    @PostMapping
    public String salvar(Contato contato) {
        service.salvar(contato);
        return "redirect:/contatos";
    }

    @GetMapping
    public String listar(Model model) {
        List<Contato> contatos = service.buscarTodos();
        model.addAttribute("contatos", contatos);
        return "contato/list";
    }

}
