package com.produtos.produtos.service;

import com.produtos.produtos.model.Produto;
import com.produtos.produtos.repository.ProdutoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProdutoService {

    private ProdutoRepository produtoRepository;

    public void criarProduto(Produto produto){
        produtoRepository.save(produto);
    }

    public void deletarProduto(Integer id){
        produtoRepository.deleteById(id);
    }

    public Produto buscarProduto(Integer id){
        return produtoRepository.findById(id).get();
    }

    public List<Produto> buscarTodos(){
        return produtoRepository.findAll();
    }

    public void editarProduto(Integer id){
        produtoRepository.save(buscarProduto(id));
    }

}
