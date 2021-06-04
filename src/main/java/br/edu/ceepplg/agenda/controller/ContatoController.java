package br.edu.ceepplg.agenda.controller;

import br.edu.ceepplg.agenda.model.Contato;
import br.edu.ceepplg.agenda.service.ContatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/contatos")
public class ContatoController {

    @Autowired
    private ContatoService service;

    @GetMapping
    public String home(Model model) {
        model.addAttribute("pagina", "list");
        model.addAttribute("contatos", service.buscarTodos());
        return "contato/list";
    }

    @GetMapping("/novo")
    public String form(Model model, Contato contato) {
        model.addAttribute("pagina", "form");
        return "contato/form";
    }

    @PostMapping
    public String salvar(@Valid Contato contato, BindingResult result) {
        if (result.hasErrors()) {
            return "contato/form";
        }
        service.salvar(contato);
        return "redirect:/contatos";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Optional<Contato> contato = service.buscarPorId(id);
        if (contato.isPresent()) {
            model.addAttribute("contato", contato.get());
            model.addAttribute("pagina", "form");
            return "contato/form";
        }
        return "not-found";
    }

    @GetMapping("/remover/{id}")
    public String remover(@PathVariable Long id) {
        if (service.remover(id)) {
            return "redirect:/contatos";
        }
        return "not-found";
    }

}
