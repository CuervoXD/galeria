package com.usta.bibliotecaa.controllers;

import com.usta.bibliotecaa.entities.ArtistaEntity;
import com.usta.bibliotecaa.entities.ObraEntity;
import com.usta.bibliotecaa.models.services.ArtistaService;
import com.usta.bibliotecaa.models.services.ObraService;
import jakarta.persistence.EntityNotFoundException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ObraController {
    @Autowired
    private ObraService obraService;
    @Autowired
    private ArtistaService artistaService;
    @GetMapping(value="/obra")
    public String listarO(Model model) {
        model.addAttribute("title", "Listado de Obras");
        model.addAttribute("urlRegistro", "/crearObra");
        model.addAttribute("obra", obraService.findAll());
        return "Obras/listarObra";
    }

    @RequestMapping(value="/detalleObra/{id}")
    public String detalleObra(Model model, @PathVariable(value = "id") Long idObra) {
        model.addAttribute("title", "Detalle de Obra");
        model.addAttribute("obraDetail", obraService.viewDetail(idObra));
        return "Obras/detalleObra";
    }

    @GetMapping(value = "/crearObra")
    public String listarFormObra(Model model) {
        model.addAttribute("title", "Registrar Obra");
        model.addAttribute("obraEdit", new ObraEntity());
        List<ArtistaEntity> listaArtistas = artistaService.findAll();
        if (listaArtistas == null){
            listaArtistas = new ArrayList<>();
        }
        model.addAttribute("artistas", listaArtistas);
        return "Obras/crearObra";
    }


    @PostMapping(value = "/crearObra")
    public String guardarObra(@Valid ObraEntity obra, @RequestParam(value = "foto") MultipartFile foto,
                               BindingResult result, @RequestParam List<Long> artistas) {
        String nombreImagen = guardarImagen(foto);
        if (result.hasErrors()) {
            return "error500";
        }
        obra.setFotoObra(nombreImagen);

        List<ArtistaEntity> artistasSeleccionados = new ArrayList<>();
        for (Long idArtista : artistas) {
            ArtistaEntity artista = artistaService.findById(idArtista);
            if(artista != null){
                artistasSeleccionados.add(artista);
            }
        }
        obra.setArtistas(artistasSeleccionados);
        obraService.save(obra);
        return "redirect:/obra";
    }

    private String guardarImagen(MultipartFile imagen) {
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost("https://api.imgbb.com/1/upload");
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.addTextBody("key", "1917a7b7ed3f10d7e52eaa851c8e6fcd", ContentType.TEXT_PLAIN);
            builder.addBinaryBody("image", imagen.getInputStream(),
                    ContentType.DEFAULT_BINARY, imagen.getOriginalFilename());
            HttpEntity multipart = builder.build();
            httpPost.setEntity(multipart);
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity responseEntity = response.getEntity();

            if (response.getStatusLine().getStatusCode() == 200 ) {
                String responseString = EntityUtils.toString(responseEntity);
                JSONObject jsonResponse = new JSONObject(responseString);
                boolean success =  jsonResponse.getBoolean("success");
                if (success) {
                    JSONObject data = jsonResponse.getJSONObject("data");
                    return data.getString("url");
                }else {
                    String errorMessage = jsonResponse.optString("Error desconocido");
                    System.out.println("Error al cargar la imagen " +errorMessage);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    @RequestMapping(value = "/eliminarObra/{id}")
    public String eliminarById(@PathVariable(value = "id") Long id) throws IOException {
        if (id > 0){
            ObraEntity obra = obraService.findById(id);
            if (obra == null){

                throw new EntityNotFoundException("La obra con ID " + id + " no existe.");
            }
            obra.getArtistas().clear();
            obraService.save(obra);
            obraService.delete(id);
        }else {
            return "redirect:/error500";
        }
        return "redirect:/obra";
    }


    @GetMapping (value = "/editarObra/{id}")
    public String editarObra(Model model, @PathVariable(value = "id") Long idObra ) {
        model.addAttribute("title","Editar Obra");
        model.addAttribute("obraDetail", obraService.findById(idObra));
        model.addAttribute( "imagen", obraService.findById(idObra).getFotoObra());
        List<ArtistaEntity> artistas = artistaService.findAll();
        model.addAttribute("artistas", artistas);
        return "Obras/editarObra";
    }

    /*************************/    /*************************/    /*************************/

    @PostMapping (value = "/editarObra/{id}")
    public String editObra(@ModelAttribute("obraEditar") ObraEntity obraEntity, @PathVariable (value = "id")
    Long idObra, @RequestParam(value = "foto") MultipartFile foto,
                            @RequestParam (value = "artistas") List<Long> artistasIds, BindingResult result) throws IOException {

        if (result.hasErrors()) {
            return "error500";
        }
        ObraEntity obraAuxiliar = obraService.findById(idObra);
        obraAuxiliar.setIdObra(idObra);
        obraAuxiliar.setTituloObra(obraEntity.getTituloObra());
        obraAuxiliar.setDescripcionObra(obraEntity.getDescripcionObra());
        obraAuxiliar.setFechaPub(obraEntity.getFechaPub());
        obraAuxiliar.setTecnicaObra(obraEntity.getTecnicaObra());
        obraAuxiliar.setFotoObra(obraEntity.getFotoObra());

        String imagen = obraAuxiliar.getFotoObra();
        String nombreImagen = guardarImagen(foto);
        if (nombreImagen.isBlank() || nombreImagen.isEmpty() || nombreImagen == null) {
            obraAuxiliar.setFotoObra(imagen);
        }else {
            obraAuxiliar.setFotoObra(nombreImagen);
        }
        obraAuxiliar.getArtistas().clear();
        if (artistasIds != null){
            for (Long artistaId : artistasIds) {
                ArtistaEntity artista = artistaService.findById(artistaId);
                if (artista != null){
                    obraAuxiliar.getArtistas().add(artista);
                }
            }
        }
        obraService.actualizarObraEntity(obraAuxiliar);
        return "redirect:/obra";
    }

}
