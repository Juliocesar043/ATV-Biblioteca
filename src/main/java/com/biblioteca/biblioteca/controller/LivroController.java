package com.biblioteca.biblioteca.controller;

import com.biblioteca.biblioteca.model.Livro;
import com.biblioteca.biblioteca.repository.LivroRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping({"/livro", "/livros"})
public class LivroController {
    private final LivroRepository repository;

    public LivroController(LivroRepository repository) {
        this.repository = repository;
    }

    @GetMapping({"", "/listar"})
    public String listarLivros(Model model) {
        model.addAttribute("livros", repository.findAll());
        return "livros/lista";
    }

    @GetMapping("/novo")
    public String novoLivro(Model model) {
        model.addAttribute("livro", new Livro());
        return "livros/form";
    }

    @PostMapping
    public String cadastrarLivro(@ModelAttribute Livro livro) {
        repository.save(livro);
        return "redirect:/livros/listar";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        repository.deleteById(id);
        return "redirect:/livros/listar";
    }

}
