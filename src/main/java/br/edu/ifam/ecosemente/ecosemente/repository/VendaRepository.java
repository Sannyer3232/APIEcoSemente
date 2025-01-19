package br.edu.ifam.ecosemente.ecosemente.repository;

import br.edu.ifam.ecosemente.ecosemente.model.Venda;
import jakarta.persistence.Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Long> {
}
