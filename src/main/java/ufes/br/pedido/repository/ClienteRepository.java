package ufes.br.pedido.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ufes.br.pedido.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
