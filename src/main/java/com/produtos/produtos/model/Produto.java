package com.produtos.produtos.model;

import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.Min;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private String nome;
    @Min(1)
    private Double preco;
    @Min(0)
    private Integer estoque;
    @Column(nullable = false)
    private String data_validade;
    private String descricao;
    private Long codigo_de_barras;
    private Double peso;
    private Double medida;
    private String fabricante;
    private String categoria;



}
