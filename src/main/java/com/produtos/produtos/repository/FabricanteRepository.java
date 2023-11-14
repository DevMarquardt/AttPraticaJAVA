package com.produtos.produtos.repository;

import com.produtos.produtos.model.Fabricante;
import com.produtos.produtos.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FabricanteRepository extends JpaRepository<Fabricante, Long> {
}
