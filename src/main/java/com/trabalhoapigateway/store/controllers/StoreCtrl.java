package com.trabalhoapigateway.store.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/")
public class StoreCtrl {
 
  @GetMapping("")
  public String store() {
    return "store online";
  }
}
