package com.rhsystem.service;

import com.rhsystem.dto.FuncionarioDTO;
import com.rhsystem.exception.ResourceNotFoundException;
import com.rhsystem.model.Cargo;
import com.rhsystem.model.Departamento;
import com.rhsystem.model.Funcionario;
import com.rhsystem.repository.CargoRepository;
import com.rhsystem.repository.DepartamentoRepository;
import com.rhsystem.repository.FuncionarioRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;
    private final CargoRepository cargoRepository;
    private final DepartamentoRepository departamentoRepository;
    private final ModelMapper modelMapper;

    @Transactional(readOnly = true)
    public List<FuncionarioDTO> findAll() {
        return funcionarioRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public FuncionarioDTO findById(Long id) {
        Funcionario funcionario = funcionarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Funcionário não encontrado com id: " + id));
        return convertToDTO(funcionario);
    }

    @Transactional(readOnly = true)
    public List<FuncionarioDTO> findByStatus(Boolean status) {
        return funcionarioRepository.findByStatus(status).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public FuncionarioDTO create(FuncionarioDTO dto) {
        if (funcionarioRepository.existsByCpf(dto.getCpf())) {
            throw new IllegalArgumentException("CPF já está cadastrado");
        }

        Funcionario funcionario = convertToEntity(dto);
        Funcionario saved = funcionarioRepository.save(funcionario);
        return convertToDTO(saved);
    }

    @Transactional
    public FuncionarioDTO update(Long id, FuncionarioDTO dto) {
        Funcionario funcionario = funcionarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Funcionário não encontrado com id: " + id));

        if (!funcionario.getCpf().equals(dto.getCpf()) &&
            funcionarioRepository.existsByCpf(dto.getCpf())) {
            throw new IllegalArgumentException("CPF já está cadastrado");
        }

        funcionario.setNome(dto.getNome());
        funcionario.setCpf(dto.getCpf());
        funcionario.setRg(dto.getRg());
        funcionario.setDataNascimento(dto.getDataNascimento());
        funcionario.setDataAdmissao(dto.getDataAdmissao());
        funcionario.setSalario(dto.getSalario());
        funcionario.setStatus(dto.getStatus());
        funcionario.setFotoUrl(dto.getFotoUrl());
        funcionario.setUsuarioId(dto.getUsuarioId());

        if (dto.getCargoId() != null) {
            Cargo cargo = cargoRepository.findById(dto.getCargoId())
                    .orElseThrow(() -> new ResourceNotFoundException("Cargo não encontrado"));
            funcionario.setCargo(cargo);
        }

        if (dto.getDepartamentoId() != null) {
            Departamento departamento = departamentoRepository.findById(dto.getDepartamentoId())
                    .orElseThrow(() -> new ResourceNotFoundException("Departamento não encontrado"));
            funcionario.setDepartamento(departamento);
        }

        Funcionario updated = funcionarioRepository.save(funcionario);
        return convertToDTO(updated);
    }

    @Transactional
    public void delete(Long id) {
        if (!funcionarioRepository.existsById(id)) {
            throw new ResourceNotFoundException("Funcionário não encontrado com id: " + id);
        }
        funcionarioRepository.deleteById(id);
    }

    private FuncionarioDTO convertToDTO(Funcionario funcionario) {
        FuncionarioDTO dto = modelMapper.map(funcionario, FuncionarioDTO.class);
        if (funcionario.getCargo() != null) {
            dto.setCargoId(funcionario.getCargo().getId());
            dto.setCargoNome(funcionario.getCargo().getNome());
        }
        if (funcionario.getDepartamento() != null) {
            dto.setDepartamentoId(funcionario.getDepartamento().getId());
            dto.setDepartamentoNome(funcionario.getDepartamento().getNome());
        }
        return dto;
    }

    private Funcionario convertToEntity(FuncionarioDTO dto) {
        Funcionario funcionario = modelMapper.map(dto, Funcionario.class);

        if (dto.getCargoId() != null) {
            Cargo cargo = cargoRepository.findById(dto.getCargoId())
                    .orElseThrow(() -> new ResourceNotFoundException("Cargo não encontrado"));
            funcionario.setCargo(cargo);
        }

        if (dto.getDepartamentoId() != null) {
            Departamento departamento = departamentoRepository.findById(dto.getDepartamentoId())
                    .orElseThrow(() -> new ResourceNotFoundException("Departamento não encontrado"));
            funcionario.setDepartamento(departamento);
        }

        return funcionario;
    }
}
