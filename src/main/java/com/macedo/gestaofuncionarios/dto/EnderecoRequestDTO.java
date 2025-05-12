package com.macedo.gestaofuncionarios.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoRequestDTO {
    @NotBlank(message = "O logradouro não pode estar em branco.")
    @Size(max = 100, message = "O logradouro deve ter no máximo 100 caracteres.")
    private String logradouro;

    @Size(max = 20, message = "O número deve ter no máximo 20 caracteres.")
    private String numero;

    @Size(max = 100, message = "O complemento deve ter no máximo 100 caracteres.")
    private String complemento;

    @NotBlank(message = "O bairro não pode estar em branco.")
    @Size(max = 100, message = "O bairro deve ter no máximo 100 caracteres.")
    private String bairro;

    @NotBlank(message = "A cidade não pode estar em branco.")
    @Size(max = 100, message = "A cidade deve ter no máximo 100 caracteres.")
    private String cidade;

    @NotBlank(message = "A UF não pode estar em branco.")
    @Size(min = 2, max = 2, message = "A UF deve ter exatamente 2 caracteres.")
    private String uf;

    @NotBlank(message = "O CEP não pode estar em branco.")
    @Pattern(regexp = "\\d{8}", message = "O CEP deve conter exatamente 8 dígitos numéricos.")
    private String cep;
}
