package br.edu.ifam.ecosemente.ecosemente.repository;

import br.edu.ifam.ecosemente.ecosemente.model.Comprador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CompradorRepository extends JpaRepository<Comprador, Long> {

    @Query("select c from Comprador c where c.cpfCnpj =:cpfCnpj")
    Comprador findByCpfCnpj(@Param("cpfCnpj") String cpfCnpj);
}
