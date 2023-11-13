package com.produtos.produtos.controller;

import com.produtos.produtos.model.Produto;
import com.produtos.produtos.service.ProdutoService;
import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
@AllArgsConstructor
public class ProdutoController {
    private ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<?> criarProduto(@RequestBody Produto produto) {
        Produto produto1 = produtoService.buscarProduto(produto.getId());
        if (produto1 != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("O produto já existe");
        } else {
            produtoService.criarProduto(produto);
            return ResponseEntity.status(HttpStatus.CREATED).body(produto);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editarProduto(@PathVariable Integer id) {
        if (produtoService.buscarProduto(id) != null) {
            produtoService.editarProduto(id);
            return ResponseEntity.status(HttpStatus.OK).body("Produto editado com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarProduto(@PathVariable Integer id) {
        Produto produto = produtoService.buscarProduto(id);
        if (produto != null) {
            return ResponseEntity.status(HttpStatus.OK).body(produto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
        }
    }

    @GetMapping
    public List<Produto> buscarTodos() {
        return produtoService.buscarTodos();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarProduto(@PathVariable Integer id) {
        if (produtoService.buscarProduto(id) != null) {
            produtoService.deletarProduto(id);
            return ResponseEntity.status(HttpStatus.OK).body("Produto deletado com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
        }
    }
}
