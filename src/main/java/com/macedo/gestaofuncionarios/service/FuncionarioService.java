package com.macedo.gestaofuncionarios.service;

import com.macedo.gestaofuncionarios.dto.*;
import com.macedo.gestaofuncionarios.model.Funcionario;
import com.macedo.gestaofuncionarios.model.StatusFuncionario;
import com.macedo.gestaofuncionarios.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;

    @Autowired
    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    @Transactional
    public FuncionarioResponseDTO criarFuncionario(FuncionarioRequestDTO requestDTO) {
        Funcionario funcionario = mapRequestDtoToEntity(requestDTO);
        Funcionario funcionarioSalvo = funcionarioRepository.save(funcionario);
        return mapEntityToResponseDto(funcionarioSalvo);
    }

    @Transactional(readOnly = true)
    public List<FuncionarioResponseDTO> listarTodosFuncionarios() {
        List<Funcionario> funcionarios = funcionarioRepository.findAll();
        return funcionarios.stream()
                .map(this::mapEntityToResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public FuncionarioResponseDTO buscarFuncionarioPorId(Long id) {
        Funcionario funcionario = funcionarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado com o ID: " + id));
        return mapEntityToResponseDto(funcionario);
    }

    @Transactional
    public FuncionarioResponseDTO atualizarFuncionario(Long id, FuncionarioRequestDTO requestDTO) {
        Funcionario funcionarioExistente = funcionarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado com o ID: " + id));

        atualizarDadosEntityComRequestDto(funcionarioExistente, requestDTO);

        Funcionario funcionarioAtualizado = funcionarioRepository.save(funcionarioExistente);
        return mapEntityToResponseDto(funcionarioAtualizado);
    }

    @Transactional
    public void deletarFuncionario(Long id) {
        if (!funcionarioRepository.existsById(id)) {
            throw new RuntimeException("Funcionário não encontrado com o ID: " + id);
        }
        funcionarioRepository.deleteById(id);
    }

    private void mapDtoToEntityFields(Funcionario funcionario, FuncionarioRequestDTO requestDTO) {
        funcionario.setNomeCompleto(requestDTO.getNomeCompleto());
        funcionario.setCpf(requestDTO.getCpf());
        funcionario.setDataNascimento(requestDTO.getDataNascimento());
        funcionario.setEmail(requestDTO.getEmail());
        funcionario.setTelefone(requestDTO.getTelefone());

        EnderecoRequestDTO enderecoDTO = requestDTO.getEndereco();
        if (enderecoDTO != null) {
            funcionario.setLogradouro(enderecoDTO.getLogradouro());
            funcionario.setNumero(enderecoDTO.getNumero());
            funcionario.setComplemento(enderecoDTO.getComplemento());
            funcionario.setBairro(enderecoDTO.getBairro());
            funcionario.setCidade(enderecoDTO.getCidade());
            funcionario.setUf(enderecoDTO.getUf());
            funcionario.setCep(enderecoDTO.getCep());
        }

        InfoProfissionalRequestDTO infoDTO = requestDTO.getInfoProfissional();
        if (infoDTO != null) {
            funcionario.setCargo(infoDTO.getCargo());
            funcionario.setDataAdmissao(infoDTO.getDataAdmissao());
            funcionario.setSalario(infoDTO.getSalario());
            funcionario.setStatus(infoDTO.getStatus());
        }
    }

    private Funcionario mapRequestDtoToEntity(FuncionarioRequestDTO requestDTO) {
        Funcionario funcionario = new Funcionario();
        mapDtoToEntityFields(funcionario, requestDTO);
        return funcionario;
    }

    private FuncionarioResponseDTO mapEntityToResponseDto(Funcionario funcionario) {
        FuncionarioResponseDTO responseDTO = new FuncionarioResponseDTO();
        responseDTO.setId(funcionario.getId());
        responseDTO.setNomeCompleto(funcionario.getNomeCompleto());
        responseDTO.setCpf(funcionario.getCpf());
        responseDTO.setDataNascimento(funcionario.getDataNascimento());
        responseDTO.setEmail(funcionario.getEmail());
        responseDTO.setTelefone(funcionario.getTelefone());

        EnderecoResponseDTO enderecoResponseDTO = new EnderecoResponseDTO();
        enderecoResponseDTO.setLogradouro(funcionario.getLogradouro());
        enderecoResponseDTO.setNumero(funcionario.getNumero());
        enderecoResponseDTO.setComplemento(funcionario.getComplemento());
        enderecoResponseDTO.setBairro(funcionario.getBairro());
        enderecoResponseDTO.setCidade(funcionario.getCidade());
        enderecoResponseDTO.setUf(funcionario.getUf());
        enderecoResponseDTO.setCep(funcionario.getCep());
        responseDTO.setEndereco(enderecoResponseDTO);

        InfoProfissionalResponseDTO infoResponseDTO = new InfoProfissionalResponseDTO();
        infoResponseDTO.setCargo(funcionario.getCargo());
        infoResponseDTO.setDataAdmissao(funcionario.getDataAdmissao());
        infoResponseDTO.setSalario(funcionario.getSalario());
        infoResponseDTO.setStatus(funcionario.getStatus());
        responseDTO.setInfoProfissional(infoResponseDTO);

        return responseDTO;
    }

    private void atualizarDadosEntityComRequestDto(Funcionario funcionarioExistente, FuncionarioRequestDTO requestDTO) {
        mapDtoToEntityFields(funcionarioExistente, requestDTO);
    }
}
