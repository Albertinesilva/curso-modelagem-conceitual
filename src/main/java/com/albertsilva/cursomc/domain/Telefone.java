package com.albertsilva.cursomc.domain;

import java.io.Serializable;

import jakarta.persistence.Entity;

@Entity(name = "telefone")
public class Telefone implements Serializable {

  private String numero;
}
