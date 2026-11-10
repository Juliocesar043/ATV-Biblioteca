package com.biblioteca.biblioteca.controller;

import org.springframework.ui.Model;
import com.biblioteca.biblioteca.model.Categoria;
import com.biblioteca.biblioteca.repository.CategoriaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {
    private final CategoriaRepository repository;

    public CategoriaController(CategoriaRepository repository) {
        this.repository = repository;
    }

    @GetMapping({"/novo", ""})
    public String novaCategoria(Model model) {
        model.addAttribute("categoria", new Categoria());
        return "categorias/form";
    }

    @GetMapping("/listar")
    public String listarCategorias(Model model) {
        model.addAttribute("categorias", repository.findAll());
        return "categorias/lista";
    }

    @PostMapping({"", "/"})
    public String cadastrarCategoria(@ModelAttribute Categoria categoria) {
        repository.save(categoria);
        return "redirect:/categorias/listar";
    }

    @GetMapping("/excluir/{id}")
    public String excluirCategoria(@PathVariable Long id) {
        repository.deleteById(id);
        return "redirect:/categorias/listar";
    }
}
