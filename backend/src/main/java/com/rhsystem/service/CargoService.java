package com.rhsystem.service;

import com.rhsystem.dto.CargoDTO;
import com.rhsystem.exception.ResourceNotFoundException;
import com.rhsystem.model.Cargo;
import com.rhsystem.repository.CargoRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CargoService {

    private final CargoRepository cargoRepository;
    private final ModelMapper modelMapper;

    @Transactional(readOnly = true)
    public List<CargoDTO> findAll() {
        return cargoRepository.findAll().stream()
                .map(cargo -> modelMapper.map(cargo, CargoDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CargoDTO findById(Long id) {
        Cargo cargo = cargoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cargo não encontrado com id: " + id));
        return modelMapper.map(cargo, CargoDTO.class);
    }

    @Transactional
    public CargoDTO create(CargoDTO dto) {
        Cargo cargo = modelMapper.map(dto, Cargo.class);
        Cargo saved = cargoRepository.save(cargo);
        return modelMapper.map(saved, CargoDTO.class);
    }

    @Transactional
    public CargoDTO update(Long id, CargoDTO dto) {
        Cargo cargo = cargoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cargo não encontrado com id: " + id));

        cargo.setNome(dto.getNome());
        cargo.setNivel(dto.getNivel());
        cargo.setSalarioBase(dto.getSalarioBase());

        Cargo updated = cargoRepository.save(cargo);
        return modelMapper.map(updated, CargoDTO.class);
    }

    @Transactional
    public void delete(Long id) {
        if (!cargoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Cargo não encontrado com id: " + id);
        }
        cargoRepository.deleteById(id);
    }
}
