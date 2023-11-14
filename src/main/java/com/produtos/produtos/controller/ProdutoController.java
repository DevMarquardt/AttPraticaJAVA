package com.produtos.produtos.controller;

import com.produtos.produtos.model.Produto;
import com.produtos.produtos.service.ProdutoService;
import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produto")
@AllArgsConstructor
public class ProdutoController {
    private ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<?> criarProduto(@RequestBody Produto produto) {
        return  produtoService.criarProduto(produto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editarProduto(@RequestBody Produto produto, @PathVariable Long id) {
        return produtoService.editarProduto(produto, id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarProduto(@PathVariable Long id) {
        return produtoService.buscarProduto(id);
    }

    @GetMapping
    public ResponseEntity<?> buscarTodos() {
        return produtoService.buscarTodos();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarProduto(@PathVariable Long id) {
        return produtoService.deletarProduto(id);
    }
}
