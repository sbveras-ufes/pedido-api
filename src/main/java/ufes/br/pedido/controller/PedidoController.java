package ufes.br.pedido.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ufes.br.pedido.Cliente;
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
    public ResponseEntity<Map<String, Object>> criarPedido(@RequestBody Pedido pedido, @RequestParam Long clienteId) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        pedido.setCliente(cliente);
        pedidoRepository.save(pedido);
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("status", "Pedido realizado");
        response.put("id",pedido.getId());
        response.put("data",pedido.getData());
        response.put("itens",pedido.getItens());
        response.put("taxa de entrega",pedido.getTaxaEntrega());
        response.put("Valor",pedido.getValorPedido());
        

        return ResponseEntity.ok(response);
    }

    @PostMapping("/{pedidoId}/processar-descontos")
    public ResponseEntity<Map<String, Object>> processarDescontos(@PathVariable Long pedidoId) {
        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        calculadoraDescontoService.processar(pedido);
        pedidoRepository.save(pedido);
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("id", pedido.getId());
        response.put("data", pedido.getData());
        response.put("cupons aplicados", pedido.getCuponsDescontoEntrega());
        response.put("valor do desconto",pedido.getDescontoConcedido());
        response.put("valor do pedido com desconto",pedido.getValorPedido());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{pedidoId}")
    
    public Pedido consultarPedido(@PathVariable Long pedidoId) {
        return pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
                
    }
}


