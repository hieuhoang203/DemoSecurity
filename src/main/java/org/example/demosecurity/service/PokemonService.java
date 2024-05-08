package org.example.demosecurity.service;

import org.example.demosecurity.entity.Pokemon;
import org.example.demosecurity.repository.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PokemonService {

    @Autowired
    private PokemonRepository repository;

    public List<Pokemon> getAllPokemon() {
        return repository.findAll();
    }

    public Optional<Pokemon> findPokemonById(Integer id) {
        return repository.findById(id);
    }

    public Pokemon addPokemon(Pokemon pokemon) {
        this.repository.save(pokemon);
        return pokemon;
    }

    public Pokemon updatePokemon(Integer id, Pokemon pokemon) {
        Pokemon oldPokemon = Pokemon.builder()
                .id(id)
                .name(pokemon.getName())
                .gender(pokemon.getGender())
                .age(pokemon.getAge())
                .build();
        this.repository.save(oldPokemon);
        return pokemon;
    }

    public Optional<Pokemon> deletePokemon(Integer id) {
        this.repository.deleteById(id);
        return this.repository.findById(id);
    }

}
