package br.edu.ifam.ecosemente.ecosemente.repository;

import br.edu.ifam.ecosemente.ecosemente.model.Semente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SementeRepository extends JpaRepository<Semente, Long> {

}
