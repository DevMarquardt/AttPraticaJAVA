package com.produtos.produtos.controller;

import com.produtos.produtos.model.Categoria;
import com.produtos.produtos.model.Fabricante;
import com.produtos.produtos.service.CategoriaService;
import com.produtos.produtos.service.FabricanteService;
import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/categoria")
@AllArgsConstructor
public class CategoriaController {
    private CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<?> criarCategoria(@RequestBody Categoria categoria) {
        return  categoriaService.criarCategoria(categoria);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editarCategoria(@RequestBody Categoria categoria, @PathVariable Long id) {
        return categoriaService.editarCategoria(categoria, id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarCategoria(@PathVariable Long id) {
        return categoriaService.buscarCategoria(id);
    }

    @GetMapping
    public ResponseEntity<?> buscarTodos() {
        return categoriaService.buscarTodos();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarCategoria(@PathVariable Long id) {
        return categoriaService.deletarCategoria(id);
    }
}
