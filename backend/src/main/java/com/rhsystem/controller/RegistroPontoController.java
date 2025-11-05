package com.rhsystem.controller;

import com.rhsystem.dto.RegistroPontoDTO;
import com.rhsystem.service.RegistroPontoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/registros-ponto")
@RequiredArgsConstructor
@Tag(name = "Registro de Ponto", description = "Endpoints para gerenciamento de registro de ponto")
@SecurityRequirement(name = "bearerAuth")
@CrossOrigin(origins = "http://localhost:4200")
public class RegistroPontoController {

    private final RegistroPontoService registroPontoService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'RH', 'GESTOR')")
    @Operation(summary = "Listar todos os registros de ponto")
    public ResponseEntity<List<RegistroPontoDTO>> getAll() {
        return ResponseEntity.ok(registroPontoService.findAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'RH', 'GESTOR', 'FUNCIONARIO')")
    @Operation(summary = "Buscar registro de ponto por ID")
    public ResponseEntity<RegistroPontoDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(registroPontoService.findById(id));
    }

    @GetMapping("/funcionario/{funcionarioId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'RH', 'GESTOR', 'FUNCIONARIO')")
    @Operation(summary = "Listar registros de ponto por funcionário")
    public ResponseEntity<List<RegistroPontoDTO>> getByFuncionario(@PathVariable Long funcionarioId) {
        return ResponseEntity.ok(registroPontoService.findByFuncionarioId(funcionarioId));
    }

    @GetMapping("/funcionario/{funcionarioId}/periodo")
    @PreAuthorize("hasAnyRole('ADMIN', 'RH', 'GESTOR', 'FUNCIONARIO')")
    @Operation(summary = "Listar registros de ponto por funcionário e período")
    public ResponseEntity<List<RegistroPontoDTO>> getByPeriodo(
            @PathVariable Long funcionarioId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fim) {
        return ResponseEntity.ok(registroPontoService.findByPeriodo(funcionarioId, inicio, fim));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'RH', 'GESTOR', 'FUNCIONARIO')")
    @Operation(summary = "Registrar ponto")
    public ResponseEntity<RegistroPontoDTO> create(@Valid @RequestBody RegistroPontoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(registroPontoService.create(dto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'RH')")
    @Operation(summary = "Deletar registro de ponto")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        registroPontoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
