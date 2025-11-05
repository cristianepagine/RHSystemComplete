package com.rhsystem.controller;

import com.rhsystem.dto.FeriasDTO;
import com.rhsystem.model.StatusFerias;
import com.rhsystem.service.FeriasService;
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
@RequestMapping("/api/ferias")
@RequiredArgsConstructor
@Tag(name = "Férias", description = "Endpoints para gerenciamento de férias")
@SecurityRequirement(name = "bearerAuth")
@CrossOrigin(origins = "http://localhost:4200")
public class FeriasController {

    private final FeriasService feriasService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'RH', 'GESTOR')")
    @Operation(summary = "Listar todas as férias")
    public ResponseEntity<List<FeriasDTO>> getAll() {
        return ResponseEntity.ok(feriasService.findAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'RH', 'GESTOR', 'FUNCIONARIO')")
    @Operation(summary = "Buscar férias por ID")
    public ResponseEntity<FeriasDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(feriasService.findById(id));
    }

    @GetMapping("/funcionario/{funcionarioId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'RH', 'GESTOR', 'FUNCIONARIO')")
    @Operation(summary = "Listar férias por funcionário")
    public ResponseEntity<List<FeriasDTO>> getByFuncionario(@PathVariable Long funcionarioId) {
        return ResponseEntity.ok(feriasService.findByFuncionarioId(funcionarioId));
    }

    @GetMapping("/status/{status}")
    @PreAuthorize("hasAnyRole('ADMIN', 'RH', 'GESTOR')")
    @Operation(summary = "Listar férias por status")
    public ResponseEntity<List<FeriasDTO>> getByStatus(@PathVariable StatusFerias status) {
        return ResponseEntity.ok(feriasService.findByStatus(status));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'RH', 'GESTOR', 'FUNCIONARIO')")
    @Operation(summary = "Solicitar férias")
    public ResponseEntity<FeriasDTO> create(@Valid @RequestBody FeriasDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(feriasService.create(dto));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'RH', 'GESTOR')")
    @Operation(summary = "Atualizar férias")
    public ResponseEntity<FeriasDTO> update(@PathVariable Long id, @Valid @RequestBody FeriasDTO dto) {
        return ResponseEntity.ok(feriasService.update(id, dto));
    }

    @PutMapping("/{id}/aprovar")
    @PreAuthorize("hasAnyRole('ADMIN', 'RH', 'GESTOR')")
    @Operation(summary = "Aprovar férias")
    public ResponseEntity<FeriasDTO> aprovar(@PathVariable Long id) {
        return ResponseEntity.ok(feriasService.aprovar(id));
    }

    @PutMapping("/{id}/rejeitar")
    @PreAuthorize("hasAnyRole('ADMIN', 'RH', 'GESTOR')")
    @Operation(summary = "Rejeitar férias")
    public ResponseEntity<FeriasDTO> rejeitar(@PathVariable Long id) {
        return ResponseEntity.ok(feriasService.rejeitar(id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'RH', 'GESTOR')")
    @Operation(summary = "Deletar férias")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        feriasService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
