package com.trabalhoapigateway.store.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trabalhoapigateway.store.services.PecaServices;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trabalhoapigateway.store.domain.Componente;
import com.trabalhoapigateway.store.domain.Peca;
import com.trabalhoapigateway.store.utils.ExceptionMessage;
import com.trabalhoapigateway.store.utils.ExceptionMessageJson;
import com.trabalhoapigateway.store.utils.VersionApi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(VersionApi.v1 + "/pecas")
public class PecaCtrl {

  @Autowired
  private PecaServices pecaServices;
  ObjectMapper objectMapper = new ObjectMapper();

  public PecaCtrl(PecaServices pecaService) {
    this.pecaServices = pecaService;
  }

  @PostMapping("")
  public ResponseEntity<?> createPeca(@RequestBody Peca body) {
    UUID id = UUID.randomUUID();
    var peca = new Peca(id, body.getCodigo(), body.getNome());
    try {
      this.pecaServices.addPeca(peca);
      return ResponseEntity.status(HttpStatus.CREATED).body(peca);
    } catch (Exception e) {
      String m = new ExceptionMessageJson(e.getMessage()).getMessage();
      try {
        ExceptionMessage errorResponse = objectMapper.readValue(m, ExceptionMessage.class);
        return ResponseEntity.status(HttpStatus.CONFLICT)
            .body(errorResponse);
      } catch (JsonProcessingException jsonException) {
        throw new RuntimeException(jsonException);
      }
    }
  }

  @GetMapping("")
  public ResponseEntity<List<Peca>> getAllPecas() {
    List<Peca> pecas = this.pecaServices.getPecas();
    return new ResponseEntity<List<Peca>>(pecas, HttpStatus.OK);
  }

  @GetMapping("/{codigo}")
  public ResponseEntity<?> getPeca(@PathVariable(value = "codigo") Integer codigo) {

    try {
      Peca body = this.pecaServices.getPecaById(codigo);
      return ResponseEntity.status(HttpStatus.OK).body(body);

    } catch (Exception e) {
      String m = new ExceptionMessageJson(e.getMessage()).getMessage();
      try {
        ExceptionMessage errorResponse = objectMapper.readValue(m, ExceptionMessage.class);
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(errorResponse);
      } catch (JsonProcessingException jsonException) {
        throw new RuntimeException(jsonException);
      }
    }
  }

  @PostMapping("/{codigo}/componentes")
  public ResponseEntity<?> createComponente(@PathVariable(value = "codigo") Integer codigo,
      @RequestBody Componente body) {

    Componente componente = new Componente(UUID.randomUUID(), codigo, body.getSku(), body.getDescricao(),
        body.getPreco(), body.getQuantidade());

    try {
      this.pecaServices.addComponenteToPeca(codigo, componente);
    } catch (Exception e) {
      String m = new ExceptionMessageJson(e.getMessage()).getMessage();
      try {
        ExceptionMessage errorResponse = objectMapper.readValue(m, ExceptionMessage.class);
        return ResponseEntity.status(HttpStatus.CONFLICT)
            .body(errorResponse);
      } catch (JsonProcessingException jsonException) {
        throw new RuntimeException(jsonException);
      }
    }

    return new ResponseEntity<Componente>(componente, HttpStatus.CREATED);
  }

  @GetMapping("/{codigo}/componentes")
  public ResponseEntity<?> getComponente(@PathVariable(value = "codigo") Integer codigo) {
    try {
      var componentes = this.pecaServices.getComponentesByIdPeca(codigo);
      return new ResponseEntity<>(componentes, HttpStatus.OK);
    } catch (Exception e) {
      String m = new ExceptionMessageJson(e.getMessage()).getMessage();
      try {
        ExceptionMessage errorResponse = objectMapper.readValue(m, ExceptionMessage.class);
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(errorResponse);
      } catch (JsonProcessingException jsonException) {
        throw new RuntimeException(jsonException);
      }
    }

  }
}
