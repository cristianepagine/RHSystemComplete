package com.rhsystem.repository;

import com.rhsystem.model.Beneficio;
import com.rhsystem.model.TipoBeneficio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BeneficioRepository extends JpaRepository<Beneficio, Long> {
    List<Beneficio> findByAtivo(Boolean ativo);
    List<Beneficio> findByTipo(TipoBeneficio tipo);
    List<Beneficio> findByObrigatorio(Boolean obrigatorio);
}
