package com.produtos.produtos.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Fabricante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_fabricante;

    @Column(nullable = false)
    private String nome;

    private String descricao;

    @OneToMany(mappedBy = "fabricante", cascade = CascadeType.PERSIST)
    private List<Produto> produtos;
}