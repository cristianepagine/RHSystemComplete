package com.rhsystem.service;

import com.rhsystem.dto.FeriasDTO;
import com.rhsystem.exception.ResourceNotFoundException;
import com.rhsystem.model.Ferias;
import com.rhsystem.model.Funcionario;
import com.rhsystem.model.StatusFerias;
import com.rhsystem.repository.FeriasRepository;
import com.rhsystem.repository.FuncionarioRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FeriasService {

    private final FeriasRepository feriasRepository;
    private final FuncionarioRepository funcionarioRepository;
    private final ModelMapper modelMapper;

    @Transactional(readOnly = true)
    public List<FeriasDTO> findAll() {
        return feriasRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public FeriasDTO findById(Long id) {
        Ferias ferias = feriasRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Férias não encontrada com id: " + id));
        return convertToDTO(ferias);
    }

    @Transactional(readOnly = true)
    public List<FeriasDTO> findByFuncionarioId(Long funcionarioId) {
        return feriasRepository.findByFuncionarioId(funcionarioId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<FeriasDTO> findByStatus(StatusFerias status) {
        return feriasRepository.findByStatus(status).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public FeriasDTO create(FeriasDTO dto) {
        Funcionario funcionario = funcionarioRepository.findById(dto.getFuncionarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Funcionário não encontrado"));

        if (dto.getDataInicio().isAfter(dto.getDataFim())) {
            throw new IllegalArgumentException("Data de início não pode ser posterior à data de fim");
        }

        Ferias ferias = new Ferias();
        ferias.setFuncionario(funcionario);
        ferias.setDataInicio(dto.getDataInicio());
        ferias.setDataFim(dto.getDataFim());
        ferias.setStatus(StatusFerias.PENDENTE);
        ferias.setDiasSolicitados(dto.getDiasSolicitados());
        ferias.setObservacao(dto.getObservacao());

        Ferias saved = feriasRepository.save(ferias);
        return convertToDTO(saved);
    }

    @Transactional
    public FeriasDTO update(Long id, FeriasDTO dto) {
        Ferias ferias = feriasRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Férias não encontrada com id: " + id));

        ferias.setDataInicio(dto.getDataInicio());
        ferias.setDataFim(dto.getDataFim());
        ferias.setStatus(dto.getStatus());
        ferias.setDiasSolicitados(dto.getDiasSolicitados());
        ferias.setObservacao(dto.getObservacao());

        Ferias updated = feriasRepository.save(ferias);
        return convertToDTO(updated);
    }

    @Transactional
    public FeriasDTO aprovar(Long id) {
        Ferias ferias = feriasRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Férias não encontrada com id: " + id));

        ferias.setStatus(StatusFerias.APROVADA);
        Ferias updated = feriasRepository.save(ferias);
        return convertToDTO(updated);
    }

    @Transactional
    public FeriasDTO rejeitar(Long id) {
        Ferias ferias = feriasRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Férias não encontrada com id: " + id));

        ferias.setStatus(StatusFerias.REJEITADA);
        Ferias updated = feriasRepository.save(ferias);
        return convertToDTO(updated);
    }

    @Transactional
    public void delete(Long id) {
        if (!feriasRepository.existsById(id)) {
            throw new ResourceNotFoundException("Férias não encontrada com id: " + id);
        }
        feriasRepository.deleteById(id);
    }

    private FeriasDTO convertToDTO(Ferias ferias) {
        FeriasDTO dto = new FeriasDTO();
        dto.setId(ferias.getId());
        dto.setFuncionarioId(ferias.getFuncionario().getId());
        dto.setFuncionarioNome(ferias.getFuncionario().getNome());
        dto.setDataInicio(ferias.getDataInicio());
        dto.setDataFim(ferias.getDataFim());
        dto.setStatus(ferias.getStatus());
        dto.setDiasSolicitados(ferias.getDiasSolicitados());
        dto.setObservacao(ferias.getObservacao());
        return dto;
    }
}
