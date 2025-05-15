package com.macedo.gestaofuncionarios.controller;

import com.macedo.gestaofuncionarios.dto.FuncionarioRequestDTO;
import com.macedo.gestaofuncionarios.dto.FuncionarioResponseDTO;
import com.macedo.gestaofuncionarios.service.FuncionarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/funcionarios")
@Tag(name = "Funcionários", description = "Endpoints para Gerenciamento de Funcionários")
public class FuncionarioController {
    private final FuncionarioService funcionarioService;

    @Autowired
    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @PostMapping
    @Operation(summary = "Cria um novo funcionário")
    public ResponseEntity<FuncionarioResponseDTO> criarFuncionario(@Valid @RequestBody FuncionarioRequestDTO requestDTO) {
        FuncionarioResponseDTO responseDTO = funcionarioService.criarFuncionario(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping
    @Operation(summary = "Lista todos os funcionários")
    public ResponseEntity<List<FuncionarioResponseDTO>> listarTodosFuncionarios() {
        List<FuncionarioResponseDTO> responseDTOList = funcionarioService.listarTodosFuncionarios();
        return ResponseEntity.ok(responseDTOList);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca um funcionário pelo ID")
    public ResponseEntity<FuncionarioResponseDTO> buscarFuncionarioPorId(@PathVariable Long id) {
        FuncionarioResponseDTO responseDTO = funcionarioService.buscarFuncionarioPorId(id);
        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um funcionário existente")
    public ResponseEntity<FuncionarioResponseDTO> atualizarFuncionario(@PathVariable Long id, @Valid @RequestBody FuncionarioRequestDTO requestDTO) {
        FuncionarioResponseDTO responseDTO = funcionarioService.atualizarFuncionario(id, requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um funcionário pelo ID")
    public ResponseEntity<Void> deletarFuncionario(@PathVariable Long id) {
        funcionarioService.deletarFuncionario(id);
        return ResponseEntity.noContent().build();
    }
}
