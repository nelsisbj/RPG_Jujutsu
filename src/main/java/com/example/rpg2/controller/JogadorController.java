package com.example.rpg2.controller;

import com.example.rpg2.model.Jogador;
import com.example.rpg2.repository.JogadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jogadores")
public class JogadorController {

    @Autowired
    private JogadorRepository repository;

    @GetMapping
    public List<Jogador> listarTodos() {
        return repository.findAll();
    }

    @PostMapping
    public Jogador criarJogador(@RequestBody Jogador jogador) {
        return repository.save(jogador);
    }
}