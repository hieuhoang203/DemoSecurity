package org.example.demosecurity.controller;

import org.example.demosecurity.entity.Pokemon;
import org.example.demosecurity.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/pokemon")
public class PokemonController {

    @Autowired
    private PokemonService pokemonService;

    @GetMapping(value = "/get-all")
    public ResponseEntity<List<Pokemon>> getAllPokemon() {
        return new ResponseEntity<>(this.pokemonService.getAllPokemon(), HttpStatus.OK);
    }

    @PostMapping(value = "/new")
    public ResponseEntity<Pokemon> save(@RequestBody Pokemon pokemon) {
        return new ResponseEntity<>(this.pokemonService.addPokemon(pokemon), HttpStatus.OK);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<Pokemon> update(@PathVariable Integer id, @RequestBody Pokemon pokemon) {
        return new ResponseEntity<>(this.pokemonService.updatePokemon(id, pokemon), HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Optional<Pokemon>> delete(@PathVariable Integer id) {
        return new ResponseEntity<>(this.pokemonService.deletePokemon(id), HttpStatus.OK);
    }

    @GetMapping(value = "/find/{id}")
    public ResponseEntity<Optional<Pokemon>> find(@PathVariable Integer id) {
        return new ResponseEntity<>(this.pokemonService.findPokemonById(id), HttpStatus.OK);
    }

}
