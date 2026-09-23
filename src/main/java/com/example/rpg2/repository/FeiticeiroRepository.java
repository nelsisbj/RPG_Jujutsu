package com.example.rpg2.repository;

import com.example.rpg2.model.Feiticeiro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeiticeiroRepository extends JpaRepository<Feiticeiro, Long> {

}