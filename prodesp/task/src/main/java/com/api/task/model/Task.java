package com.api.task.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private Long userId; // Relacionamento com o usuário
    private String endereco;
    private String localNascimento;
    //*********************************************************************
    private String nomeAtribuidoTarefa;
    private String emailAtribuidoTarefa;
}
