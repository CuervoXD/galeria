package com.usta.bibliotecaa.controllers;

import com.usta.bibliotecaa.entities.ArtistaEntity;
import com.usta.bibliotecaa.entities.ObraEntity;
import com.usta.bibliotecaa.models.services.ArtistaService;
import com.usta.bibliotecaa.models.services.ObraService;
import com.usta.bibliotecaa.models.services.ObraServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
public class ArtistaController {

    @Autowired
    private ArtistaService artistaService;
    @Autowired
    private ObraServiceImplement obraServiceImplement;

    @GetMapping(value = "/artista")
    private String listarA(Model model) {
        model.addAttribute("title", "Listado de Artistas");
        model.addAttribute("urlRegistro", "/crearArtista");
        model.addAttribute("artista", artistaService.findAll());
        return "Artistas/listarArtista";
    }

    @GetMapping(value = "/detalleArtista/{id}")
    public String detalleArtista(Model model, @PathVariable(value = "id") Long id) {
        ArtistaEntity artista = artistaService.findById(id);
        Collection<ObraEntity> obras = artista.getObra();
        model.addAttribute("obra", obras);
        model.addAttribute("title", "Detalle de Artista");
        model.addAttribute("detalleA", artistaService.viewDetail(id));
        return "Artistas/detalleArtista";
    }

    //*----------------------------------------------------------*//
    @GetMapping(value = "/crearArtista")
    public String crearArtista(Model model) {
        model.addAttribute("title", "Formulario de Artista");
        model.addAttribute("artista", new ArtistaEntity());
        return "Artistas/crearArtista";
    }

    //*----------------------------------------------------------*//
    @PostMapping(value = "/crearArtista")
    public String guardarArtista(@Valid ArtistaEntity artista, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("title", "Formulario de Artista");
            return "Artistas/crearArtista";
        }
        artistaService.save(artista);
        return "redirect:/artista";
    }

    @GetMapping(value = "/editarArtista/{id}")
    public String editarArtista(Model model,@PathVariable(value = "id") Long idArtista) {
        model.addAttribute("title", "Editar Artista");
        model.addAttribute("artistaEdit", artistaService.findById(idArtista));
        List<ArtistaEntity> artistas = artistaService.findAll();
        if (artistas == null) {
            artistas = new ArrayList<>();

        }
        model.addAttribute("artista", artistas);
        return "Artistas/editarArtista";
    }


    @PostMapping (value = "/editarArtista/{id}")
    public String editArtista(@ModelAttribute("artistaEdit") ArtistaEntity artistaEntity,
                            @PathVariable (value = "id") Long idArtista,
                            BindingResult result) throws IOException {
        if (result.hasErrors()) {
            return "error500";
        }
        ArtistaEntity artistaAuxiliar = artistaService.findById(idArtista);
        artistaAuxiliar.setIdArtista(idArtista);
        artistaAuxiliar.setNombreArtista(artistaEntity.getNombreArtista());
        artistaAuxiliar.setApellidoArtista(artistaEntity.getApellidoArtista());
        artistaAuxiliar.setPaisArtista(artistaEntity.getPaisArtista());
        artistaAuxiliar.setFechaNacimiento(artistaEntity.getFechaNacimiento());

        artistaService.actualizarArtistaEntity(artistaAuxiliar);
        return "redirect:/artista";
    }

    @GetMapping(value = "/eliminarArtista/{id}")
    public String eliminarArtista(@PathVariable(value = "id") Long id)
    { artistaService.deleteById(id); return "redirect:/artista"; }

}
