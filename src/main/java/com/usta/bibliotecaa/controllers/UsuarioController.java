package com.usta.bibliotecaa.controllers;

import com.usta.bibliotecaa.entities.RolEntity;
import com.usta.bibliotecaa.entities.UsuarioEntity;
import com.usta.bibliotecaa.models.services.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

@Controller
public class UsuarioController {


    private UsuarioService usuarioService;

//    @GetMapping(value = "/register")
//    public String crearUsuario(Model model) {
//        model.addAttribute("usuario", new UsuarioEntity());
//        model.addAttribute("title", "Registro de Usuario");
//        return "register";
//    }
//
//    @PostMapping
//    public String register(@ModelAttribute("usuario")@Valid UsuarioEntity usuario,
//                           BindingResult result, Model model, @RequestParam("claveUsuario2") String claveUsuario2, SessionStatus status) {
//
//        if (result.hasErrors()) {
//            model.addAttribute("title", "Registro de Usuario");
//            return "register";
//        }
//        if (!usuario.getClave().equals(claveUsuario2)) {
//            result.rejectValue("clave", "error.usuario", "Las claves no coinciden");
//            model.addAttribute("title", "Registro de Usuario");
//            return "register";
//        }
//        String pass = new BCryptPasswordEncoder().encode(usuario.getClave());
//        usuario.setClave(pass);
//        RolEntity rollector = new RolEntity();
//        rollector.setIdRol(2L);
//
//        usuarioService.save(usuario);
//        status.setComplete();
//        return "redirect:/login";
//    }
}
