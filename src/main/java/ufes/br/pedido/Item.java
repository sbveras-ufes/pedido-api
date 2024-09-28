package ufes.br.pedido;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

/**
 *
 * @author clayton
 */

@Embeddable 
public class Item {
   

    private Long id;

    public String getNome() {
        return nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }


    public Item() {
    }

    private String nome;
    private int quantidade;
    private double valorUnitario;
    private String tipo;

    
    // public Item(String nome, int quantidade, double valorUnitario, String tipo) {
    //     this.nome = nome;
    //     this.quantidade = quantidade;
    //     this.valorUnitario = valorUnitario;
    //     this.tipo = tipo;
    // }

    public double getValorTotal() {
        return quantidade * valorUnitario;
    }

    public String getTipo() {
        return tipo;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Item{");
        sb.append("nome='").append(nome).append('\'');
        sb.append(", quantidade=").append(quantidade);
        sb.append(", valorUnitario=").append(valorUnitario);
        sb.append(", tipo='").append(tipo).append('\'');
        sb.append(", valorTotal=").append(getValorTotal());
        sb.append('}');
        return sb.toString();
    }

}
