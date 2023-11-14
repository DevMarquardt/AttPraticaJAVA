package com.produtos.produtos.service;

import com.produtos.produtos.model.Produto;
import com.produtos.produtos.repository.ProdutoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProdutoService {

    private ProdutoRepository produtoRepository;

    public ResponseEntity<?> criarProduto(Produto produto){
        if (!vefificaExistencia(produto)){
            produtoRepository.save(produto);
            return ResponseEntity.status(HttpStatus.OK).body("Produto criado!");
        }
        else if (produto.getNome() == null
                || produto.getPreco() <= 0
                || produto.getEstoque() <= 0
                || produto.getData_validade() == null
                || produto.getDescricao() == null
                || produto.getCodigo_de_barras() == null
                || produto.getPeso() == null
                || produto.getMedida() == null
                || produto.getFabricante() == null
                || produto.getCategorias() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Algum campo está inválido/faltando");
        }
        else if (vefificaExistencia(produto)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("O produto já existe");
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno no servidor");
        }
    }

    public ResponseEntity<?> deletarProduto(Long id){
        if (!vefificaExistencia(produtoRepository.findById(id).get())){
            produtoRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Produto deletado!");
        } else if (vefificaExistencia(produtoRepository.findById(id).get())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno no servidor");
        }
    }

    public ResponseEntity<?> buscarProduto(Long id){
        if (!vefificaExistencia(produtoRepository.findById(id).get())){
            return ResponseEntity.status(HttpStatus.OK).body("Produto encontrado! " + produtoRepository.findById(id).get());
        } else if (!vefificaExistencia(produtoRepository.findById(id).get())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno no servidor");
        }
    }

    public ResponseEntity<?> buscarTodos(){
        if (produtoRepository.findAll().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não há produtos cadastrados");
        }else {
            return ResponseEntity.status(HttpStatus.OK).body("Produtos encontrados " + produtoRepository.findAll());
        }
    }

    public ResponseEntity<?> editarProduto(Produto produto, Long id){
        if (!vefificaExistencia(produto)){
            Produto produto1 = produtoRepository.findById(id).get();
            BeanUtils.copyProperties(produto1, produto);
            return ResponseEntity.status(HttpStatus.OK).body("Produto editado!");
        }
        else if (produto.getNome() == null
                || produto.getPreco() <= 0
                || produto.getEstoque() <= 0
                || produto.getData_validade() == null
                || produto.getDescricao() == null
                || produto.getCodigo_de_barras() == null
                || produto.getPeso() == null
                || produto.getMedida() == null
                || produto.getFabricante() == null
                || produto.getCategorias() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Algum campo está inválido/faltando");
        }
        else if (vefificaExistencia(produto)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("O produto já existe");
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno no servidor");
        }
    }

    public boolean vefificaExistencia(Produto produto){
        for (Produto produto1 : produtoRepository.findAll()) {
            if (produto1.getNome().equals(produto.getNome())) {
                return true;
            }
        }
        return false;
    }

}
