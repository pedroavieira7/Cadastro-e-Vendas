package model;

import java.util.Date;

public class Venda {
    private Date data;
    private Produto produto;
    private int quantidadeVendida;

    public Venda(Date data, Produto produto, int quantidadeVendida) {
        this.data = data;
        this.produto = produto;
        this.quantidadeVendida = quantidadeVendida;
    }

    public Date getData() {
        return data;
    }

    public Produto getProduto() {
        return produto;
    }

    public int getQuantidadeVendida() {
        return quantidadeVendida;
    }

    public double getValorTotal() {
        return quantidadeVendida * produto.getValor();
    }
}
