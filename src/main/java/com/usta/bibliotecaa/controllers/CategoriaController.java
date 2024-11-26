package com.usta.bibliotecaa.controllers;

import com.usta.bibliotecaa.entities.CategoriaEntity;
import com.usta.bibliotecaa.models.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    // Listar categorías
    @GetMapping(value = "/categoria")
    public String listarCategorias(Model model) {
        model.addAttribute("title", "Listado de Categorías");
        model.addAttribute("categorias", categoriaService.findAll());
        model.addAttribute("urlRegistro", "/crearCategoria");
        return "Categorias/listarCategorias";
    }

    // Ver detalles de una categoría
    @RequestMapping(value = "/detalleCategoria/{id}")
    public String detalleCategoria(@PathVariable(value = "id") Long idCategoria, Model model) {
        model.addAttribute("title", "Detalle de Categoría");
        model.addAttribute("categoria", categoriaService.findById(idCategoria));
        return "Categorias/detalleCategoria";
    }

    // Mostrar formulario para crear categoría
    @GetMapping(value = "/crearCategoria")
    public String mostrarFormularioCrear(Model model) {
        model.addAttribute("title", "Registrar Categoría");
        model.addAttribute("categoria", new CategoriaEntity());
        return "Categorias/crearCategoria";
    }

    // Crear una nueva categoría
    @PostMapping(value = "/crearCategoria")
    public String crearCategoria(@Valid @ModelAttribute("categoria") CategoriaEntity categoria, BindingResult result) {
        if (result.hasErrors()) {
            return "Categorias/crearCategoria";
        }
        categoriaService.save(categoria);
        return "redirect:/categoria";
    }

    // Mostrar formulario para editar una categoría
    @GetMapping(value = "/editarCategoria/{id}")
    public String mostrarFormularioEditar(@PathVariable(value = "id") Long idCategoria, Model model) {
        CategoriaEntity categoria = categoriaService.findById(idCategoria);
        if (categoria == null) {
            return "error404";
        }
        model.addAttribute("title", "Editar Categoría");
        model.addAttribute("categoria", categoria);
        return "Categorias/editarCategoria";
    }

    // Editar una categoría existente
    @PostMapping(value = "/editarCategoria/{id}")
    public String editarCategoria(@Valid @ModelAttribute("categoria") CategoriaEntity categoria, BindingResult result,
                                  @PathVariable(value = "id") Long idCategoria) {
        if (result.hasErrors()) {
            return "Categorias/editarCategoria";
        }
        categoria.setIdCategoria(idCategoria);
        categoriaService.actualizarCategoriaEntity(categoria);
        return "redirect:/categoria";
    }

    // Eliminar una categoría
    @RequestMapping(value = "/eliminarCategoria/{id}")
    public String eliminarCategoria(@PathVariable(value = "id") Long idCategoria) {
        if (idCategoria > 0) {
            categoriaService.deleteById(idCategoria);
        }
        return "redirect:/categoria";
    }
}
