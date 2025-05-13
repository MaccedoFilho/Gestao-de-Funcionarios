package com.macedo.gestaofuncionarios.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.macedo.gestaofuncionarios.model.StatusFuncionario;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "funcionarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String nomeCompleto;

    @Column(nullable = false, unique = true, length = 11)
    private String cpf;

    @Column(nullable = false)
    private LocalDate dataNascimento;

    @Column(length = 100, unique = true)
    private String email;

    @Column(length = 20)
    private String telefone;

    @Column(length = 100)
    private String logradouro;
    @Column(length = 20)
    private String numero;
    @Column(length = 100)
    private String complemento;
    @Column(length = 100)
    private String bairro;
    @Column(length = 100)
    private String cidade;
    @Column(length = 2)
    private String uf;
    @Column(length = 8)
    private String cep;

    @Column(nullable = false, length = 100)
    private String cargo;

    @Column(nullable = false)
    private LocalDate dataAdmissao;

    @Column(precision = 10, scale = 2)
    private BigDecimal salario;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private StatusFuncionario status;
}
