package com.rhsystem.service;

import com.rhsystem.dto.DepartamentoDTO;
import com.rhsystem.exception.ResourceNotFoundException;
import com.rhsystem.model.Departamento;
import com.rhsystem.model.Funcionario;
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
public class DepartamentoService {

    private final DepartamentoRepository departamentoRepository;
    private final FuncionarioRepository funcionarioRepository;
    private final ModelMapper modelMapper;

    @Transactional(readOnly = true)
    public List<DepartamentoDTO> findAll() {
        return departamentoRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public DepartamentoDTO findById(Long id) {
        Departamento departamento = departamentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Departamento não encontrado com id: " + id));
        return convertToDTO(departamento);
    }

    @Transactional
    public DepartamentoDTO create(DepartamentoDTO dto) {
        Departamento departamento = modelMapper.map(dto, Departamento.class);
        Departamento saved = departamentoRepository.save(departamento);
        return convertToDTO(saved);
    }

    @Transactional
    public DepartamentoDTO update(Long id, DepartamentoDTO dto) {
        Departamento departamento = departamentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Departamento não encontrado com id: " + id));

        departamento.setNome(dto.getNome());
        departamento.setDescricao(dto.getDescricao());
        departamento.setGestorId(dto.getGestorId());

        Departamento updated = departamentoRepository.save(departamento);
        return convertToDTO(updated);
    }

    @Transactional
    public void delete(Long id) {
        if (!departamentoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Departamento não encontrado com id: " + id);
        }
        departamentoRepository.deleteById(id);
    }

    private DepartamentoDTO convertToDTO(Departamento departamento) {
        DepartamentoDTO dto = modelMapper.map(departamento, DepartamentoDTO.class);
        if (departamento.getGestorId() != null) {
            funcionarioRepository.findById(departamento.getGestorId())
                    .ifPresent(gestor -> dto.setGestorNome(gestor.getNome()));
        }
        return dto;
    }
}
