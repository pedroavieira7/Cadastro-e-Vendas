package telas;

import model.Venda;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class Relatorio {
    public static void exibirVendasDetalhadasPorPeriodo(List<Venda> vendas) {
        if (vendas.isEmpty()) {
            System.out.println("Nenhuma venda realizada.");
            return;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        DecimalFormat decimalFormat = new DecimalFormat("#.00");

        System.out.println("==== Relatório de Vendas - Detalhado ====");
        System.out.println("-----------------------------------------");
        System.out.println("Data\t\tNome\t\tQuantidade\tValor Unitário\tValor Total");
        System.out.println("-----------------------------------------");

        double valorTotalVendas = 0;
        int quantidadeTotalVendas = 0;

        for (Venda venda : vendas) {
            System.out.println(dateFormat.format(venda.getData()) + "\t" + venda.getProduto().getNome() + "\t\t" +
                    venda.getQuantidadeVendida() + "\t\t" +
                    venda.getProduto().getValor() + "\t\t" +
                    venda.getValorTotal());

            valorTotalVendas += venda.getValorTotal();
            quantidadeTotalVendas += venda.getQuantidadeVendida();
        }

        System.out.println("-----------------------------------------");
        System.out.println("Valor médio das vendas: " + decimalFormat.format(valorTotalVendas / quantidadeTotalVendas));
    }
}