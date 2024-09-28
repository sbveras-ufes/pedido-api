package ufes.br.pedido.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ufes.br.pedido.Cliente;
import ufes.br.pedido.Item;
import ufes.br.pedido.Pedido;
import ufes.br.pedido.repository.ClienteRepository;
import ufes.br.pedido.repository.PedidoRepository;
import ufes.br.pedido.service.CalculadoraDescontoService;


@RestController
@RequestMapping("/pedidos")

public class PedidoController {
    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private CalculadoraDescontoService calculadoraDescontoService;

    @PostMapping
    public Pedido criarPedido(@RequestBody Pedido pedido, @RequestParam Long clienteId) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        pedido.setCliente(cliente);
    
        return pedidoRepository.save(pedido);
    }

    @PostMapping("/{pedidoId}/processar-descontos")
    public Pedido processarDescontos(@PathVariable Long pedidoId) {
        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        calculadoraDescontoService.processar(pedido);
        return pedidoRepository.save(pedido);
    }

    @GetMapping("/{pedidoId}")
    
    public Pedido consultarPedido(@PathVariable Long pedidoId) {
        return pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
                
    }
}


