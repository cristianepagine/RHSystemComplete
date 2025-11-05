package com.rhsystem.repository;

import com.rhsystem.model.Candidato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CandidatoRepository extends JpaRepository<Candidato, Long> {
    Optional<Candidato> findByEmail(String email);
    Optional<Candidato> findByCpf(String cpf);
    Boolean existsByEmail(String email);
    Boolean existsByCpf(String cpf);
}
