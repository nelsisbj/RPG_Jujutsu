package com.example.rpg2.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "feiticeiros")
@Data
public class Feiticeiro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String grau;

    @Column(name = "tecnica_amaldicoada")
    private String tecnicaAmaldicoada;

    private int nivel = 0;
    private int kokusen = 0;

    @ManyToOne
    @JoinColumn(name = "jogador_id")
    @JsonBackReference
    private Jogador jogador;


    private int forca = 1;
    private int agilidade = 1;
    private int resistencia = 1;
    private int vigor = 1;

    @Column(name = "energia_atributo")
    private int energiaAtributo = 1;

    private int vida;

    private int pontos_atributo;

    @Column(name = "trilha_energia")
    private int trilhaEnergia = 0;

    @Column(name = "trilha_liberacao")
    private int trilhaLiberacao = 0;

    @Column(name = "trilha_eficiencia")
    private int trilhaEficiencia = 0;

    @Column(name = "trilha_controle")
    private int trilhaControle = 0;

    @Column(name = "trilha_fisico")
    private int trilhaFisico = 0;
}