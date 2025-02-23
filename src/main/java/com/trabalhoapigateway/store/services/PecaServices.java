package com.trabalhoapigateway.store.services;

import java.util.ArrayList;
import java.util.List;

import com.trabalhoapigateway.store.domain.Componente;
import com.trabalhoapigateway.store.domain.Peca;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class PecaServices {
  private List<Peca> pecas = new ArrayList<>();
  private List<Componente> componentes = new ArrayList<>();

  public boolean addComponenteToPeca(Integer pecaCodigo, Componente componente) {
    Optional<Peca> pecaOptional = pecas.stream()
        .filter(peca -> peca.getCodigo().equals(pecaCodigo))
        .findFirst();

    if (pecaOptional.isPresent()) {
      componentes.add(componente);
      return true;
    } else {
      return false;
    }
  }

  public PecaServices() {
    // Mock data for Peca
    Integer codeA = 001;
    Integer codeB = 002;

    Peca peca1 = new Peca(UUID.randomUUID(), codeA, "Peca A");
    Peca peca2 = new Peca(UUID.randomUUID(), codeB, "Peca B");
    pecas.add(peca1);
    pecas.add(peca2);

    // Mock data for Componente
    Componente componente1 = new Componente(UUID.randomUUID(), codeA, "SKU001", "Descricao A", 10.0, 5);
    Componente componente2 = new Componente(UUID.randomUUID(), codeB, "SKU002", "Descricao B", 20.0, 10);
    componentes.add(componente1);
    componentes.add(componente2);
  }

  public List<Peca> getPecas() {
    return pecas;
  }

  public List<Componente> getComponentes() {
    return componentes;
  }

  public void addPeca(Peca peca) {
    pecas.stream()
        .filter(p -> p.getCodigo().equals(peca.getCodigo()))
        .findFirst()
        .ifPresentOrElse(
            p -> {
              throw new IllegalArgumentException("Peça já existe.");
            },
            () -> {
              pecas.add(peca);
            });

    
  }
}
