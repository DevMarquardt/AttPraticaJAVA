package com.produtos.produtos.repository;

import com.produtos.produtos.model.Categoria;
import com.produtos.produtos.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
