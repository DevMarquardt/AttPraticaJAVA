package com.produtos.produtos.controller;

import com.produtos.produtos.model.Produto;
import com.produtos.produtos.service.ProdutoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProdutoController {
    private ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<Produto> criarProduto(@RequestBody Produto produto){
        for (Produto produto1: produtoService.buscarTodos()) {
            if (produto1.getNome().equals(produto.getNome())){
                return ResponseEntity.status(HttpStatus.CONFLICT).body(produto);
            }else{
                produtoService.criarProduto(produto);
            }
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(produto);
    }

    @PutMapping("/{id}")
    public void editarProduto(@PathVariable Integer id){
        produtoService.editarProduto(id);
    }

    @GetMapping("/{id}")
    public Produto buscarProduto(@PathVariable Integer id){
        return produtoService.buscarProduto(id);
    }

    @GetMapping
    public List<Produto> buscarTodos(){
        return produtoService.buscarTodos();
    }

    @DeleteMapping("/{id}")
    public void deletarProduto(@PathVariable Integer id){
        produtoService.deletarProduto(id);
    }
}
