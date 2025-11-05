package com.rhsystem.controller;

import com.rhsystem.dto.DepartamentoDTO;
import com.rhsystem.service.DepartamentoService;
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
@RequestMapping("/api/departamentos")
@RequiredArgsConstructor
@Tag(name = "Departamentos", description = "Endpoints para gerenciamento de departamentos")
@SecurityRequirement(name = "bearerAuth")
@CrossOrigin(origins = "http://localhost:4200")
public class DepartamentoController {

    private final DepartamentoService departamentoService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'RH', 'GESTOR')")
    @Operation(summary = "Listar todos os departamentos")
    public ResponseEntity<List<DepartamentoDTO>> getAll() {
        return ResponseEntity.ok(departamentoService.findAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'RH', 'GESTOR')")
    @Operation(summary = "Buscar departamento por ID")
    public ResponseEntity<DepartamentoDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(departamentoService.findById(id));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'RH')")
    @Operation(summary = "Criar novo departamento")
    public ResponseEntity<DepartamentoDTO> create(@Valid @RequestBody DepartamentoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(departamentoService.create(dto));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'RH')")
    @Operation(summary = "Atualizar departamento")
    public ResponseEntity<DepartamentoDTO> update(@PathVariable Long id, @Valid @RequestBody DepartamentoDTO dto) {
        return ResponseEntity.ok(departamentoService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'RH')")
    @Operation(summary = "Deletar departamento")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        departamentoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
