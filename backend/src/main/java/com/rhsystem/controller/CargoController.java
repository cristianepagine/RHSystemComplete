package com.rhsystem.controller;

import com.rhsystem.dto.CargoDTO;
import com.rhsystem.service.CargoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cargos")
@RequiredArgsConstructor
@Tag(name = "Cargos", description = "Endpoints para gerenciamento de cargos")
@SecurityRequirement(name = "bearerAuth")
@CrossOrigin(origins = "http://localhost:4200")
public class CargoController {

    private final CargoService cargoService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'RH', 'GESTOR')")
    @Operation(summary = "Listar todos os cargos")
    public ResponseEntity<List<CargoDTO>> getAll() {
        return ResponseEntity.ok(cargoService.findAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'RH', 'GESTOR')")
    @Operation(summary = "Buscar cargo por ID")
    public ResponseEntity<CargoDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(cargoService.findById(id));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'RH')")
    @Operation(summary = "Criar novo cargo")
    public ResponseEntity<CargoDTO> create(@Valid @RequestBody CargoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cargoService.create(dto));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'RH')")
    @Operation(summary = "Atualizar cargo")
    public ResponseEntity<CargoDTO> update(@PathVariable Long id, @Valid @RequestBody CargoDTO dto) {
        return ResponseEntity.ok(cargoService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'RH')")
    @Operation(summary = "Deletar cargo")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        cargoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
