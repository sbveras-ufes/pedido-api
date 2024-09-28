package ufes.br.pedido;

/**
 *
 * @author clayton
 */
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private LocalDate data;
    
    public Long getId() {
        return id;
    }


    @ManyToOne
    private Cliente cliente;
    private final double taxaEntrega = 10.00;

    @ElementCollection
    private List<Item> itens;
    
    public Pedido() {
        
        this.cuponsDescontoEntrega = new ArrayList<>();
    }

    @ElementCollection
    private List<CupomDescontoEntrega> cuponsDescontoEntrega;

    // public Pedido(LocalDate data, Cliente cliente) {
    //     this.data = data;
    //     this.cliente = cliente;
    //     this.itens = new ArrayList<>();
    //     this.cuponsDescontoEntrega = new ArrayList<>();
    // }

    public void adicionarItem(Item item) {
        itens.add(item);
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public double getValorPedido() {
        double valorTotal = 0.0;
        for (Item item : itens) {
            valorTotal += item.getValorTotal();
        }
        return (valorTotal - getDescontoConcedido());
    }

    public Cliente getCliente() {
        return cliente;
    }

    public List<Item> getItens() {
        return itens;
    }

    public double getTaxaEntrega() {
        return taxaEntrega - getDescontoConcedido();
    }

    public void aplicarDesconto(CupomDescontoEntrega desconto) {
        double valorComDesconto = taxaEntrega - getDescontoConcedido() - desconto.getValorDesconto();
        if (valorComDesconto < 0) {
            desconto = new CupomDescontoEntrega("Parcial - " + desconto.getNomeMetodo(), desconto.getValorDesconto() + valorComDesconto);
        }
        cuponsDescontoEntrega.add(desconto);
    }

    public double getDescontoConcedido() {
        double descontoTotal = 0.0;
        for (CupomDescontoEntrega desconto : cuponsDescontoEntrega) {
            descontoTotal += desconto.getValorDesconto();
        }
        return descontoTotal;
    }

    public List<CupomDescontoEntrega> getCuponsDescontoEntrega() {
        return cuponsDescontoEntrega;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Pedido{");
        sb.append("data=").append(data);
        sb.append(", cliente=").append(cliente);
        sb.append(", taxaEntrega=").append(taxaEntrega);
        sb.append(", valorPedido=").append(getValorPedido());
        sb.append(", descontoConcedido=").append(getDescontoConcedido());
        sb.append(", itens=").append(itens);
        sb.append(", cuponsDescontoEntrega=").append(cuponsDescontoEntrega);
        sb.append('}');
        return sb.toString();
    }

    public LocalDate getData() {
        return data;
    }

}
