package com.example.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import com.example.entities.Player;

@RestResource(path="players", rel="player")
public interface PlayerRepository extends CrudRepository<Player, Long> {

}
