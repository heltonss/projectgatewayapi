package com.trabalhoapigateway.store.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.trabalhoapigateway.store.domain.Componente;
import com.trabalhoapigateway.store.utils.VersionApi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@RestController
@RequestMapping(VersionApi.v1 + "/pecas")
public class PecaCtrl {

    @PostMapping("")
    public String createPeca(@RequestBody String body) {
        return "Post";
    }

    @GetMapping("")
    public String getAllPecas() {
        return "GetAll";
    }

    @GetMapping("/{codigo}")
    public String getPeca(@PathVariable(value = "codigo") String codigo) {
        System.out.println(codigo);
        return codigo;
    }

    @PostMapping("/{codigo}/componentes")
    public ResponseEntity<Componente> createComponente(@PathVariable(value = "codigo") String codigo, @RequestBody Componente body) {
        System.out.println(codigo);
        return new ResponseEntity<Componente>(body, HttpStatus.CREATED);
    }

    @GetMapping("/{codigo}/componentes")
    public ResponseEntity<String> getComponente(@PathVariable(value = "codigo") String codigo) {
        return new ResponseEntity<>(codigo, HttpStatus.OK);
    }
}
