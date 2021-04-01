package com.tracker.link.controllers;

import com.tracker.link.dto.ErrorDTO;
import com.tracker.link.dto.LinkDTO;
import com.tracker.link.exceptionhandler.LinkInvalidURL;
import com.tracker.link.exceptionhandler.LinkNotFound;
import com.tracker.link.services.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/link")
public class LinkController {

    @Autowired
    private LinkService linkService;

    @PostMapping("/createlink")
    public ResponseEntity<Integer> createLink(@RequestBody LinkDTO linkDTO, @RequestParam String password) throws LinkInvalidURL {
        if (password == null) {
            password = "";
        }
        linkDTO.setPassword(password);
        Integer linkId = this.linkService.createLink(linkDTO);
        return ResponseEntity.ok(linkId);
    }

    @PostMapping("/{idLink}")
    public RedirectView getRedirection(@PathVariable Integer idLink) throws LinkNotFound {
        String linkUrl = this.linkService.getRedirection(idLink);
        RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();
        return new RedirectView(linkUrl);
    }

    @GetMapping("/metrics/{idLink}")
    public Integer getStats(@PathVariable Integer idLink) throws LinkNotFound {
        return this.linkService.getStats(idLink);
    }

    @ExceptionHandler(LinkNotFound.class)
    public ResponseEntity<ErrorDTO> linkNotFoundException(LinkNotFound errorException) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setName("Link No Encontrado");
        errorDTO.setDescription("El Link: " + errorException.getMessage() + " no existe");
        return new ResponseEntity<>(errorDTO, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(LinkInvalidURL.class)
    public ResponseEntity<ErrorDTO> linkInvalidURLExceptio(LinkInvalidURL errorException) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setName("Link Invalido");
        errorDTO.setDescription("La URL : " + errorException.getMessage() + " no es correcta");
        return ResponseEntity.badRequest().body(errorDTO);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorDTO> handleMissingParams(MissingServletRequestParameterException errorException) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setName("Parametro Invalido");
        errorDTO.setDescription("El Parametro : " + errorException.getParameterName() + " de tipo " + errorException.getParameterType() + " no es correcta");
        return ResponseEntity.badRequest().body(errorDTO);
       }
}
