package com.trabalhoapigateway.store.utils;

public class ExceptionMessageJson extends Exception {
    public ExceptionMessageJson(String message) {
      super("{\"error\": \"" + message + "\"}");
  }
}

