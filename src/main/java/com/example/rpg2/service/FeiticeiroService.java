package com.example.rpg2.service;

import com.example.rpg2.model.Feiticeiro;
import com.example.rpg2.repository.FeiticeiroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeiticeiroService {

    @Autowired
    private FeiticeiroRepository repository;

    public Feiticeiro criarFeiticeiro(Feiticeiro feiticeiro) {
        int vidaMaxima = 100 + (feiticeiro.getVigor() * 10);
        feiticeiro.setVida(vidaMaxima);

        int energiaMaxima = 100 + (feiticeiro.getEnergiaAtributo() * 10);
        feiticeiro.setEnergiaAtributo(energiaMaxima);

        return repository.save(feiticeiro);
    }
    public Feiticeiro levelUp(Long id){
        Feiticeiro feiticeiro = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Feiticeiro não encontrado"));

        feiticeiro.setNivel(feiticeiro.getNivel() + 1);
        feiticeiro.setPontos_atributo(feiticeiro.getPontos_atributo() + 2);

        return repository.save(feiticeiro);
    }

    public Feiticeiro dispararKokusen(Long id) {
        Feiticeiro feiticeiro = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Feiticeiro não encontrado"));


        feiticeiro.setKokusen(feiticeiro.getKokusen() + 1);

        int pa = 0;
        for(int i = 1; i <= 20; i++){
            pa += i;
            if(pa == feiticeiro.getKokusen()) feiticeiro.setPontos_atributo(feiticeiro.getPontos_atributo() + 2);
            if(pa >= feiticeiro.getKokusen()) break;
        }

        return repository.save(feiticeiro);
    }

    public Feiticeiro distribuirPontos(Long id, int ptsEnergia, int ptsLiberacao, int ptsEficiencia, int ptsControle, int ptsFisico) {
        Feiticeiro feiticeiro = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Feiticeiro não encontrado"));

        int totalGasto = ptsEnergia + ptsLiberacao + ptsEficiencia + ptsControle + ptsFisico;

        if (totalGasto > feiticeiro.getPontos_atributo()) {
            throw new RuntimeException("Pontos insuficientes! Tentou gastar " + totalGasto + " mas só tem " + feiticeiro.getPontos_atributo());
        }

        feiticeiro.setTrilhaEnergia(feiticeiro.getTrilhaEnergia() + ptsEnergia);
        feiticeiro.setTrilhaLiberacao(feiticeiro.getTrilhaLiberacao() + ptsLiberacao);
        feiticeiro.setTrilhaEficiencia(feiticeiro.getTrilhaEficiencia() + ptsEficiencia);
        feiticeiro.setTrilhaControle(feiticeiro.getTrilhaControle() + ptsControle);
        feiticeiro.setTrilhaFisico(feiticeiro.getTrilhaFisico() + ptsFisico);

        feiticeiro.setPontos_atributo(feiticeiro.getPontos_atributo() - totalGasto);

        return repository.save(feiticeiro);
    }
}