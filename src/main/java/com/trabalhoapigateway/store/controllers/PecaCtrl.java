package com.trabalhoapigateway.store.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trabalhoapigateway.store.services.PecaServices;
import com.trabalhoapigateway.store.domain.Componente;
import com.trabalhoapigateway.store.domain.Peca;
import com.trabalhoapigateway.store.utils.VersionApi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(VersionApi.v1 + "/pecas")
public class PecaCtrl {

    @Autowired
    private PecaServices pecaServices;

    public PecaCtrl(PecaServices pecaService) {
        this.pecaServices = pecaService;
    }

    @PostMapping("")
    public ResponseEntity<Peca> createPeca(@RequestBody Peca body) {
        UUID id = UUID.randomUUID();
        UUID codigo = UUID.randomUUID();
        var peca =  new Peca(id, codigo, body.getNome());
        try {
            this.pecaServices.addPeca(peca);
        } catch (Exception e) {
            return new ResponseEntity<Peca>(peca, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Peca>(peca, HttpStatus.CREATED);
    }
   

    @GetMapping("")
    public ResponseEntity<List<Peca>> getAllPecas() {
        List<Peca> pecas = this.pecaServices.getPecas();
        return new ResponseEntity<List<Peca>>(pecas, HttpStatus.OK);
    }

    @GetMapping("/{codigo}")
    public String getPeca(@PathVariable(value = "codigo") String codigo) {
        System.out.println(codigo);
        return codigo;
    }

    @PostMapping("/{codigo}/componentes")
    public ResponseEntity<Componente> createComponente(@PathVariable(value = "codigo") String codigo,
            @RequestBody Componente body) {
        System.out.println(codigo);
        return new ResponseEntity<Componente>(body, HttpStatus.CREATED);
    }

    @GetMapping("/{codigo}/componentes")
    public ResponseEntity<String> getComponente(@PathVariable(value = "codigo") String codigo) {
        return new ResponseEntity<>(codigo, HttpStatus.OK);
    }
}
