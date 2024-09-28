package ufes.br.pedido.service;

import ufes.br.pedido.moduloDescontoEntrega.IFormaCalculoDescontoEntrega;
import ufes.br.pedido.CupomDescontoEntrega;
import ufes.br.pedido.Pedido;
import ufes.br.pedido.moduloDescontoEntrega.FormaDescontoTaxaPorBairro;
// Importe outras estratégias de desconto conforme necessário
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import ufes.br.pedido.moduloDescontoEntrega.FormaDescontoTaxaPorTipoCliente;
import ufes.br.pedido.moduloDescontoEntrega.FormaDescontoTipoItem;
import ufes.br.pedido.moduloDescontoEntrega.FormaDescontoValorPedido;

@Service
public class CalculadoraDescontoService {

    private List<IFormaCalculoDescontoEntrega> formasDesconto;

    public CalculadoraDescontoService() {
        this.formasDesconto = new ArrayList<>();
        formasDesconto.add(new FormaDescontoTaxaPorBairro());
        formasDesconto.add(new FormaDescontoTaxaPorTipoCliente());
        formasDesconto.add(new FormaDescontoTipoItem());
        formasDesconto.add(new FormaDescontoValorPedido(200));
    }

    public void processar(Pedido pedido) {
        for (IFormaCalculoDescontoEntrega forma : formasDesconto) {
            if (forma.seAplica(pedido)) {
                Map<String, Double> descontoData = forma.calcularDesconto(pedido);

                for (String nomeDesconto : descontoData.keySet()) {
                    double descontoProposto = descontoData.get(nomeDesconto);
                    double descontoConcedido = pedido.getDescontoConcedido();
                    double descontoMaximoPermitido = 10 - descontoConcedido;

                    double descontoFinal = Math.min(descontoProposto, descontoMaximoPermitido);

                    String nomeFinal = descontoFinal < descontoProposto ? "Parcial - " + nomeDesconto : nomeDesconto;
                    pedido.aplicarDesconto(new CupomDescontoEntrega(nomeFinal, descontoFinal));
                }

                if (pedido.getDescontoConcedido() >= 10) {
                    break;
                }
            }
        }
    }
}
