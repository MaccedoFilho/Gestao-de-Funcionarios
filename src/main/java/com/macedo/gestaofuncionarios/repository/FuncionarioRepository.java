package com.macedo.gestaofuncionarios.repository;

import com.macedo.gestaofuncionarios.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    Optional<Funcionario> findByCpf(String cpf);
    Optional<Funcionario> findByEmail(String email);
    List<Funcionario> findByCargoIgnoreCase(String cargo);
    List<Funcionario> findByStatus(com.macedo.gestaofuncionarios.model.StatusFuncionario status);
}
