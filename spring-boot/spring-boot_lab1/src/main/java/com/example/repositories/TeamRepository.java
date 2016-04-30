package com.example.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import com.example.entities.Team;

@RestResource(path="teams", rel="team")
public interface TeamRepository extends CrudRepository<Team, Long> {

}
