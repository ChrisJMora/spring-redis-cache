package com.pokemon.demo.controllers;

import com.pokemon.demo.model.Pokemon;
import com.pokemon.demo.services.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class PokemonController {

    @Autowired
    PokemonService pokemonService;

    @GetMapping("/pokemons")
    public String pokemonListDetail(Model model) {
        // Busca al pokemon en función de su id
        List<Pokemon> pokemonList = pokemonService.getThemAll();
        model.addAttribute("pokemonList", pokemonList);
        return "pokemons";
    }

    @GetMapping("/pokemon")
    public String pokemonDetail(@RequestParam(name="id", required=false, defaultValue="1") int id, Model model) {
        // Busca al pokemon en función de su id
        Optional<Pokemon> optionalPokemon = pokemonService.getPokemonById(id);
        if (optionalPokemon.isPresent())
        {
            Pokemon pokemon = optionalPokemon.get();
            model.addAttribute("national_number",pokemon.getNational_number());
            model.addAttribute("name", pokemon.getName());
            model.addAttribute("height", pokemon.getHeight());
            model.addAttribute("weight", pokemon.getWeight());
        }
        return "pokemon";
    }

}