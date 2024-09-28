package ufes.br.pedido;

import java.time.LocalDate;

import ufes.br.pedido.service.CalculadoraDescontoService;

public class Principal {

    public static void main(String[] args) {

        CalculadoraDescontoService calculadoraDesconto = new CalculadoraDescontoService();

        // Cenário 1: Desconto concedido total é menor que 10
        Cliente cliente1 = new Cliente("João", "Prata", 50.0, "Rua A", "Centro", "Vitória");
        Pedido pedido1 = new Pedido(LocalDate.now(), cliente1);
        Item itemAlimentacao = new Item("Pizza", 1, 20, "Alimentação");
        pedido1.adicionarItem(itemAlimentacao);

        calculadoraDesconto.processar(pedido1);
        System.out.println("Cenário 1: Desconto total concedido: " + pedido1.getDescontoConcedido());

        // Cenário 2: Vários cupons de descontos são concedidos, mas o total ultrapassa 10, e o último é usado apenas parcialmente
        Cliente cliente2 = new Cliente("Maria", "Ouro", 150.0, "Rua B", "Bairro Rico", "Vitória");
        Pedido pedido2 = new Pedido(LocalDate.now(), cliente2);
        Item itemEducacao = new Item("Livro", 10, 50, "Educação");
        Item itemLazer = new Item("Brinquedo", 20, 15, "Lazer");
        pedido2.adicionarItem(itemAlimentacao); 
        pedido2.adicionarItem(itemEducacao);
        pedido2.adicionarItem(itemLazer);

        calculadoraDesconto.processar(pedido2);

        System.out.println("Cenário 2: Desconto total concedido: " + pedido2.getDescontoConcedido());
    }
}
