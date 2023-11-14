package com.produtos.produtos.service;

import com.produtos.produtos.model.Categoria;
import com.produtos.produtos.repository.CategoriaRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.*;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class CategoriaService {

    private CategoriaRepository categoriaRepository;

    public ResponseEntity<?> criarCategoria(Categoria categoria){
        if (!vefificaExistencia(categoria)){
            categoriaRepository.save(categoria);
            return ResponseEntity.status(HttpStatus.OK).body("Categoria criada!");
        }
        else return verificarCampos(categoria);
    }

    public ResponseEntity<?> deletarCategoria(Long id){
        if (!vefificaExistencia(categoriaRepository.findById(id).get())){
            categoriaRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Categoria deletada!");
        } else if (vefificaExistencia(categoriaRepository.findById(id).get())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Categoria não encontrada");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno no servidor");
        }
    }

    public ResponseEntity<?> buscarCategoria(Long id){
        if (!vefificaExistencia(categoriaRepository.findById(id).get())){
            return ResponseEntity.status(HttpStatus.OK).body("Categoria encontrada! " + categoriaRepository.findById(id).get());
        } else if (!vefificaExistencia(categoriaRepository.findById(id).get())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Categoria não encontrada");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno no servidor");
        }
    }

    public ResponseEntity<?> buscarTodos(){
        if (categoriaRepository.findAll().isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não há categorias cadastradas");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Categorias encontradas " + categoriaRepository.findAll());
    }

    public ResponseEntity<?> editarCategoria(Categoria categoria, Long id){
        if (!vefificaExistencia(categoria)){
            Categoria categoria1 = categoriaRepository.findById(id).get();
            BeanUtils.copyProperties(categoria1, categoria);
            return ResponseEntity.status(HttpStatus.OK).body("Categoria editada!");
        }
        else {
            return verificarCampos(categoria);
        }
    }

    private ResponseEntity<?> verificarCampos(Categoria categoria) {
        if (categoria.getNome() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O campo de nome está inválido/faltando");
        }
        else if (vefificaExistencia(categoria)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("A categoria já existe");
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno no servidor");
        }
    }

    public boolean vefificaExistencia(Categoria categoria){
        for (Categoria categoria1 : categoriaRepository.findAll()) {
            if (categoria1.getNome().equals(categoria.getNome())) {
                return true;
            }
        }
        return false;
    }

}
