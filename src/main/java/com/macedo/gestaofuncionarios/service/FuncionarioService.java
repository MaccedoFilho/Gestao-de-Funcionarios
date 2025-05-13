package com.macedo.gestaofuncionarios.service;

import com.macedo.gestaofuncionarios.dto.*;
import com.macedo.gestaofuncionarios.model.Funcionario;
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

    private Funcionario mapRequestDtoToEntity(FuncionarioRequestDTO requestDTO) {
        Funcionario funcionario = new Funcionario();
        funcionario.setNomeCompleto(requestDTO.getNomeCompleto());
        funcionario.setCpf(requestDTO.getCpf());
        funcionario.setDataNascimento(requestDTO.getDataNascimento());
        funcionario.setEmail(requestDTO.getEmail());
        funcionario.setTelefone(requestDTO.getTelefone());

        funcionario.setLogradouro(requestDTO.getEndereco().getLogradouro());
        funcionario.setNumero(requestDTO.getEndereco().getNumero());
        funcionario.setComplemento(requestDTO.getEndereco().getComplemento());
        funcionario.setBairro(requestDTO.getEndereco().getBairro());
        funcionario.setCidade(requestDTO.getEndereco().getCidade());
        funcionario.setUf(requestDTO.getEndereco().getUf());
        funcionario.setCep(requestDTO.getEndereco().getCep());

        funcionario.setCargo(requestDTO.getInfoProfissional().getCargo());
        funcionario.setDataAdmissao(requestDTO.getInfoProfissional().getDataAdmissao());
        funcionario.setSalario(requestDTO.getInfoProfissional().getSalario());
        funcionario.setStatus(requestDTO.getInfoProfissional().getStatus());

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

        EnderecoResponseDTO enderecoDTO = new EnderecoResponseDTO();
        enderecoDTO.setLogradouro(funcionario.getLogradouro());
        enderecoDTO.setNumero(funcionario.getNumero());
        enderecoDTO.setComplemento(funcionario.getComplemento());
        enderecoDTO.setBairro(funcionario.getBairro());
        enderecoDTO.setCidade(funcionario.getCidade());
        enderecoDTO.setUf(funcionario.getUf());
        enderecoDTO.setCep(funcionario.getCep());
        responseDTO.setEndereco(enderecoDTO);

        InfoProfissionalResponseDTO infoDTO = new InfoProfissionalResponseDTO();
        infoDTO.setCargo(funcionario.getCargo());
        infoDTO.setDataAdmissao(funcionario.getDataAdmissao());
        infoDTO.setSalario(funcionario.getSalario());
        infoDTO.setStatus(funcionario.getStatus());
        responseDTO.setInfoProfissional(infoDTO);

        return responseDTO;
    }

    private void atualizarDadosEntityComRequestDto(Funcionario funcionarioExistente, FuncionarioRequestDTO requestDTO) {
        funcionarioExistente.setNomeCompleto(requestDTO.getNomeCompleto());
        funcionarioExistente.setCpf(requestDTO.getCpf());
        funcionarioExistente.setDataNascimento(requestDTO.getDataNascimento());
        funcionarioExistente.setEmail(requestDTO.getEmail());
        funcionarioExistente.setTelefone(requestDTO.getTelefone());

        funcionarioExistente.setLogradouro(requestDTO.getEndereco().getLogradouro());
        funcionarioExistente.setNumero(requestDTO.getEndereco().getNumero());
        funcionarioExistente.setComplemento(requestDTO.getEndereco().getComplemento());
        funcionarioExistente.setBairro(requestDTO.getEndereco().getBairro());
        funcionarioExistente.setCidade(requestDTO.getEndereco().getCidade());
        funcionarioExistente.setUf(requestDTO.getEndereco().getUf());
        funcionarioExistente.setCep(requestDTO.getEndereco().getCep());

        funcionarioExistente.setCargo(requestDTO.getInfoProfissional().getCargo());
        funcionarioExistente.setDataAdmissao(requestDTO.getInfoProfissional().getDataAdmissao());
        funcionarioExistente.setSalario(requestDTO.getInfoProfissional().getSalario());
        funcionarioExistente.setStatus(requestDTO.getInfoProfissional().getStatus());
    }
}
