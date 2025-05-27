package com.macedo.gestaofuncionarios.repository;

import com.macedo.gestaofuncionarios.dto.EnderecoRequestDTO;
import com.macedo.gestaofuncionarios.dto.InfoProfissionalRequestDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FuncionarioRequestDTO {

    @NotBlank(message = "o nome completo não pode estar em branco.")
    @Size(min = 3, max = 150, message = "O nome completo deve ter entre 3 e 150 caracteres.")
    private String nome;

    @NotBlank(message = "O CPF não pode estar em branco.")
    @Pattern(regexp = "\\d{11}", message = "O CPF deve conter exatamente 11 dígitos numéricos.")
    private String cpf;

    @NotNull(message = "A data de nascimento não pode ser nula.")
    @Past(message = "A data de nascimento deve ser uma data no passado.")
    private LocalDate dataNascimento;

    @NotBlank(message = "O email não pode estar em branco.")
    @Email(message = "O formato do email é inválido.")
    @Size(max = 100, message = "O email deve ter no máximo 100 caracteres.")
    private String email;

    @Size(max = 20, message = "O telefone deve ter no máximo 20 caracteres.")
    private String telefone;

    @NotNull(message = "Os dados de endereço não podem ser nulos.")
    @Valid
    private EnderecoRequestDTO endereco;

    @NotNull(message = "As informações profissionais não podem ser nulas.")
    @Valid
    private InfoProfissionalRequestDTO infoProfissional;

}
