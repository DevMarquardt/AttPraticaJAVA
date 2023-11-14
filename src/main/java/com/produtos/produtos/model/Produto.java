package com.produtos.produtos.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String nome;

    @Column(nullable = false)
    private Double preco;

    @Column(nullable = false)
    private Integer estoque;

    @Column(nullable = false, length = 10)
    private String data_validade;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private Long codigo_de_barras;

    @Column(nullable = false)
    private Double peso;

    @Column(nullable = false)
    private Double medida;

    @ManyToOne
    private Fabricante fabricante;

    @ManyToMany(mappedBy = "produtos", cascade = CascadeType.PERSIST)
    private List<Categoria> categorias;
}