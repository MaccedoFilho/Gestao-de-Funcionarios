package com.macedo.gestaofuncionarios.dto;

import com.macedo.gestaofuncionarios.model.StatusFuncionario;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InfoProfissionalRequestDTO {
    @NotBlank(message = "O cargo não pode estar em branco.")
    @Size(max = 100, message = "O cargo deve ter no máximo 100 caracteres.")
    private String cargo;

    @NotNull(message = "A data de admissão não pode ser nula.")
    @PastOrPresent(message = "A data de admissão não pode ser uma data futura.")
    private LocalDate dataAdmissao;

    @NotNull(message = "O salário não pode ser nulo.")
    @DecimalMin(value = "0.0", inclusive = false, message = "O salário deve ser maior que zero.")
    @Digits(integer = 8, fraction = 2, message = "Formato de salário inválido.")
    private BigDecimal salario;

    @NotNull(message = "O status não pode ser nulo.")
    private StatusFuncionario status;
}
