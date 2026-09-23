package com.example.rpg2.controller;

import com.example.rpg2.model.Feiticeiro;
import com.example.rpg2.repository.FeiticeiroRepository;
import com.example.rpg2.service.FeiticeiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feiticeiros")
@CrossOrigin("*")
public class FeiticeiroController {

    @Autowired
    private FeiticeiroRepository repository;

    @Autowired
    private FeiticeiroService service;

    @GetMapping
    public List<Feiticeiro> listarTodos() {
        return repository.findAll();
    }

    @PostMapping
    public Feiticeiro criarFeiticeiro(@RequestBody Feiticeiro feiticeiro) {
        return service.criarFeiticeiro(feiticeiro);
    }

    @PatchMapping("/{id}/kokusen")
    public Feiticeiro acionarKokusen(@PathVariable Long id) {
        return service.dispararKokusen(id);
    }

    @PatchMapping("/{id}/levelup")
    public Feiticeiro subirDeNivel(@PathVariable Long id) {
        return service.levelUp(id);
    }

    @PatchMapping("/{id}/distribuir-pontos")
    public Feiticeiro distribuirPontos(@PathVariable Long id, @RequestBody java.util.Map<String, Integer> pontos) {
        return service.distribuirPontos(
                id,
                pontos.getOrDefault("energia", 0),
                pontos.getOrDefault("liberacao", 0),
                pontos.getOrDefault("eficiencia", 0),
                pontos.getOrDefault("controle", 0),
                pontos.getOrDefault("fisico", 0)
        );
    }

    @GetMapping("/{id}")
    public Feiticeiro buscarPorId(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Feiticeiro não encontrado"));
    }
}