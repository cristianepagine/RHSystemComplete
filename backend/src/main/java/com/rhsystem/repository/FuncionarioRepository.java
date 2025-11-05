package com.rhsystem.repository;

import com.rhsystem.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    Optional<Funcionario> findByCpf(String cpf);
    Boolean existsByCpf(String cpf);
    List<Funcionario> findByStatus(Boolean status);
    List<Funcionario> findByDepartamentoId(Long departamentoId);
    List<Funcionario> findByCargoId(Long cargoId);

    @Query("SELECT COUNT(f) FROM Funcionario f WHERE f.status = true")
    Long countAtivos();

    @Query("SELECT COUNT(f) FROM Funcionario f WHERE f.status = false")
    Long countInativos();
}
