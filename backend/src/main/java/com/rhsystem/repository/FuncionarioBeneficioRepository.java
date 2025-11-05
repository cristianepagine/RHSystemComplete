package com.rhsystem.repository;

import com.rhsystem.model.FuncionarioBeneficio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FuncionarioBeneficioRepository extends JpaRepository<FuncionarioBeneficio, Long> {
    List<FuncionarioBeneficio> findByFuncionarioId(Long funcionarioId);
    List<FuncionarioBeneficio> findByBeneficioId(Long beneficioId);
    List<FuncionarioBeneficio> findByFuncionarioIdAndAtivo(Long funcionarioId, Boolean ativo);

    @Query("SELECT SUM(fb.beneficio.valorFuncionario) FROM FuncionarioBeneficio fb WHERE fb.funcionario.id = :funcionarioId AND fb.ativo = true")
    java.math.BigDecimal calcularTotalDescontosBeneficios(Long funcionarioId);
}
