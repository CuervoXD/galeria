package com.usta.bibliotecaa.controllers;


import com.usta.bibliotecaa.entities.ExposicionEntity;
import com.usta.bibliotecaa.models.DAO.ObraDao;
import com.usta.bibliotecaa.models.services.CategoriaService;
import com.usta.bibliotecaa.models.services.ExposicionService;
import com.usta.bibliotecaa.models.services.ObraServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ExposicionController {
    @Autowired
    private ExposicionService exposicionService;
    @Autowired
    private CategoriaService categoriaService;
    @Autowired
    private ObraServiceImplement obraServiceImplement;

    @GetMapping(value = "/exposicion")
    public String listarExposiciones(Model model) {
        model.addAttribute("title", "Listado de Exposiciones");
        model.addAttribute("exposiciones", exposicionService.findAll());
        model.addAttribute("urlRegistro", "/crearExposicion");
        return "Exposiciones/listarExposicion";
    }

    @RequestMapping(value = "/detalleExposicion/{id}")
    public String detalleExposicion(@PathVariable(value = "id") Long idExposicion, Model model) {
        ExposicionEntity exposicion = exposicionService.findById(idExposicion);
        if (exposicion == null) {
            return "error404";
        }
        model.addAttribute("title", "Detalle de Exposición");
        model.addAttribute("exposicion", exposicion);
        model.addAttribute("obras", obraServiceImplement.findAll());
        return "Exposiciones/detalleExposicion";
    }

    @GetMapping(value = "/crearExposicion")
    public String mostrarFormularioCrear(Model model) {
        model.addAttribute("title", "Registrar Exposición");
        model.addAttribute("exposicion", new ExposicionEntity());
        model.addAttribute("categoria", categoriaService.findAll());
        model.addAttribute("obras", obraServiceImplement.findAll());
        return "Exposiciones/crearExposicion";
    }


    @PostMapping(value = "/crearExposicion")
    public String crearExposicion(@Valid @ModelAttribute("exposicion") ExposicionEntity exposicion,
                                  BindingResult result,
                                  @RequestParam(value = "obras") List<Long> obraIds) {
        if (result.hasErrors()) {
            return "Exposiciones/crearExposicion";
        }
        exposicion.setObras(new ArrayList<>());
        for (Long obraId : obraIds) {
            exposicion.getObras().add(obraServiceImplement.findById(obraId));
        }
        exposicionService.save(exposicion);
        return "redirect:/exposicion";
    }
    @GetMapping(value = "/editarExposicion/{id}")
    public String mostrarFormularioEditar(@PathVariable(value = "id") Long idExposicion, Model model) {
        ExposicionEntity exposicion = exposicionService.findById(idExposicion);
        if (exposicion == null) {
            return "error404";
        }
        model.addAttribute("title", "Editar Exposición");
        model.addAttribute("exposicion", exposicion);
        return "Exposiciones/editarExposicion";
    }

    // Editar una exposición existente
    @PostMapping(value = "/editarExposicion/{id}")
    public String editarExposicion(@Valid @ModelAttribute("exposicion") ExposicionEntity exposicion,
                                   BindingResult result,
                                   @PathVariable(value = "id") Long idExposicion,
                                   @RequestParam(value = "obra") List<Long> obraIds) {
        if (result.hasErrors()) {
            return "Exposiciones/editarExposicion";
        }
        ExposicionEntity exposicionExistente = exposicionService.findById(idExposicion);
        exposicionExistente.setTituloExpo(exposicion.getTituloExpo());
        exposicionExistente.setDescripcionExpo(exposicion.getDescripcionExpo());
        exposicionExistente.setFechaInicio(exposicion.getFechaInicio());
        exposicionExistente.setFechaFin(exposicion.getFechaFin());

        exposicionExistente.getObras().clear();
        for (Long obraId : obraIds) {
            exposicionExistente.getObras().add(obraServiceImplement.findById(obraId));
        }
        exposicionService.actualizarExposicionEntity(exposicionExistente);
        return "redirect:/exposicion";
    }

    @RequestMapping(value = "/eliminarExposicion/{id}")
    public String eliminarExposicion(@PathVariable(value = "id") Long idExposicion) {
        if (idExposicion > 0) {
            ExposicionEntity exposicion = exposicionService.findById(idExposicion);
            if (exposicion != null) {
                exposicion.getObras().clear();
                exposicionService.delete(idExposicion);
            }
        }
        return "redirect:/exposicion";
    }

}
