package ufes.br.pedido.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import ufes.br.pedido.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    
}
