package com.produtos.produtos.service;

import com.produtos.produtos.model.Fabricante;
import com.produtos.produtos.repository.FabricanteRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.*;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class FabricanteService {

    private FabricanteRepository fabricanteRepository;

    public ResponseEntity<?> criarFabricante(Fabricante fabricante){
        if (!vefificaExistencia(fabricante)){
            fabricanteRepository.save(fabricante);
            return ResponseEntity.status(HttpStatus.OK).body("Fabricante criada!");
        }
        else return verificarCampos(fabricante);
    }

    public ResponseEntity<?> deletarFabricante(Long id){
        if (!vefificaExistencia(fabricanteRepository.findById(id).get())){
            fabricanteRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Fabricante deletada!");
        } else if (vefificaExistencia(fabricanteRepository.findById(id).get())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fabricante não encontrada");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno no servidor");
        }
    }

    public ResponseEntity<?> buscarFabricante(Long id){
        if (!vefificaExistencia(fabricanteRepository.findById(id).get())){
            return ResponseEntity.status(HttpStatus.OK).body("Fabricante encontrada! " + fabricanteRepository.findById(id).get());
        } else if (!vefificaExistencia(fabricanteRepository.findById(id).get())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fabricante não encontrada");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno no servidor");
        }
    }

    public ResponseEntity<?> buscarTodos(){
        if (fabricanteRepository.findAll().isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não há fabricantes cadastradas");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Fabricantes encontradas " + fabricanteRepository.findAll());
    }

    public ResponseEntity<?> editarFabricante(Fabricante fabricante, Long id){
        if (!vefificaExistencia(fabricante)){
            Fabricante fabricante1 = fabricanteRepository.findById(id).get();
            BeanUtils.copyProperties(fabricante1, fabricante);
            return ResponseEntity.status(HttpStatus.OK).body("Fabricante editada!");
        }
        else {
            return verificarCampos(fabricante);
        }
    }

    private ResponseEntity<?> verificarCampos(Fabricante fabricante) {
        if (fabricante.getNome() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O campo de nome está inválido/faltando");
        }
        else if (vefificaExistencia(fabricante)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("A fabricante já existe");
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno no servidor");
        }
    }

    public boolean vefificaExistencia(Fabricante fabricante){
        for (Fabricante fabricante1 : fabricanteRepository.findAll()) {
            if (fabricante1.getNome().equals(fabricante.getNome())) {
                return true;
            }
        }
        return false;
    }

}
