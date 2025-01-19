package br.edu.ifam.ecosemente.ecosemente.repository;

import br.edu.ifam.ecosemente.ecosemente.model.ItemVenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemVendaRepository extends JpaRepository<ItemVenda, Long> {
}
