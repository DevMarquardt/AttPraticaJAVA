package com.produtos.produtos.controller;

import com.produtos.produtos.model.Fabricante;
import com.produtos.produtos.service.FabricanteService;
import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/fabricante")
@AllArgsConstructor
public class FabricanteController {
    private FabricanteService fabricanteService;

    @PostMapping
    public ResponseEntity<?> criarFabricante(@RequestBody Fabricante fabricante) {
        return  fabricanteService.criarFabricante(fabricante);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editarFabricante(@RequestBody Fabricante fabricante, @PathVariable Long id) {
        return fabricanteService.editarFabricante(fabricante, id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarFabricante(@PathVariable Long id) {
        return fabricanteService.buscarFabricante(id);
    }

    @GetMapping
    public ResponseEntity<?> buscarTodos() {
        return fabricanteService.buscarTodos();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarFabricante(@PathVariable Long id) {
        return fabricanteService.deletarFabricante(id);
    }
}
