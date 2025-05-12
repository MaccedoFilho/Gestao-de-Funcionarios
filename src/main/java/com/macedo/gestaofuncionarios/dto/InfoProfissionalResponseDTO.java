package com.macedo.gestaofuncionarios.dto;

import com.macedo.gestaofuncionarios.model.StatusFuncionario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InfoProfissionalResponseDTO {
    private Long id;
    private String cargo;
    private LocalDate dataAdmissao;
    private BigDecimal salario;
    private StatusFuncionario status;
}
