package com.rhsystem.service;

import com.rhsystem.dto.RegistroPontoDTO;
import com.rhsystem.exception.ResourceNotFoundException;
import com.rhsystem.model.Funcionario;
import com.rhsystem.model.RegistroPonto;
import com.rhsystem.repository.FuncionarioRepository;
import com.rhsystem.repository.RegistroPontoRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RegistroPontoService {

    private final RegistroPontoRepository registroPontoRepository;
    private final FuncionarioRepository funcionarioRepository;
    private final ModelMapper modelMapper;

    @Transactional(readOnly = true)
    public List<RegistroPontoDTO> findAll() {
        return registroPontoRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public RegistroPontoDTO findById(Long id) {
        RegistroPonto registro = registroPontoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Registro de ponto não encontrado com id: " + id));
        return convertToDTO(registro);
    }

    @Transactional(readOnly = true)
    public List<RegistroPontoDTO> findByFuncionarioId(Long funcionarioId) {
        return registroPontoRepository.findByFuncionarioId(funcionarioId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<RegistroPontoDTO> findByPeriodo(Long funcionarioId, LocalDateTime inicio, LocalDateTime fim) {
        return registroPontoRepository.findByFuncionarioIdAndDataHoraBetween(funcionarioId, inicio, fim)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public RegistroPontoDTO create(RegistroPontoDTO dto) {
        Funcionario funcionario = funcionarioRepository.findById(dto.getFuncionarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Funcionário não encontrado"));

        RegistroPonto registro = new RegistroPonto();
        registro.setFuncionario(funcionario);
        registro.setDataHora(dto.getDataHora() != null ? dto.getDataHora() : LocalDateTime.now());
        registro.setTipo(dto.getTipo());

        RegistroPonto saved = registroPontoRepository.save(registro);
        return convertToDTO(saved);
    }

    @Transactional
    public void delete(Long id) {
        if (!registroPontoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Registro de ponto não encontrado com id: " + id);
        }
        registroPontoRepository.deleteById(id);
    }

    private RegistroPontoDTO convertToDTO(RegistroPonto registro) {
        RegistroPontoDTO dto = new RegistroPontoDTO();
        dto.setId(registro.getId());
        dto.setFuncionarioId(registro.getFuncionario().getId());
        dto.setFuncionarioNome(registro.getFuncionario().getNome());
        dto.setDataHora(registro.getDataHora());
        dto.setTipo(registro.getTipo());
        return dto;
    }
}
