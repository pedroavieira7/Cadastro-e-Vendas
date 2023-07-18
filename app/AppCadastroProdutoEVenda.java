package app;

import model.Produto;
import model.Venda;
import telas.Relatorio;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class AppCadastroProdutoEVenda {
    private List<Produto> produtos;
    private List<Venda> vendas;

    public AppCadastroProdutoEVenda() {
        produtos = new ArrayList<>();
        vendas = new ArrayList<>();
    }

    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
    }

    public Produto buscarProdutoPorCodigo(int codigo) {
        for (Produto produto : produtos) {
            if (produto.getCodigo() == codigo) {
                return produto;
            }
        }
        return null;
    }

    public void realizarVenda() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Informe a data da venda (DD/MM/AAAA):");
        String dataString = scanner.nextLine();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date data;
        try {
            data = dateFormat.parse(dataString);
        } catch (Exception e) {
            System.out.println("Data inválida.");
            return;
        }

        System.out.println("Informe o código do produto:");
        int codigo = scanner.nextInt();

        Produto produto = buscarProdutoPorCodigo(codigo);
        if (produto == null) {
            System.out.println("Produto não encontrado.");
            return;
        }

        System.out.println("Informe a quantidade vendida:");
        int quantidadeVendida = scanner.nextInt();

        if (quantidadeVendida > produto.getQuantidadeEmEstoque()) {
            System.out.println("Quantidade insuficiente em estoque.");
            return;
        }

        produto.setQuantidadeEmEstoque(produto.getQuantidadeEmEstoque() - quantidadeVendida);

        Venda venda = new Venda(data, produto, quantidadeVendida);
        vendas.add(venda);

        System.out.println("Venda realizada com sucesso.");
    }

    public void exibirProdutos() {
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }

        DecimalFormat decimalFormat = new DecimalFormat("#.00");

        System.out.println("==== Listagem de Produtos ====");
        System.out.println("Código\tNome\t\tValor\tQuantidade");
        System.out.println("-----------------------------------------");

        for (Produto produto : produtos) {
            System.out.println(produto.getCodigo() + "\t" + produto.getNome() + "\t\t" +
                    decimalFormat.format(produto.getValor()) + "\t" + produto.getQuantidadeEmEstoque());
        }

        System.out.println("-----------------------------------------");

        double valorTotal = 0;
        double valorMaximo = Double.MIN_VALUE;
        double valorMinimo = Double.MAX_VALUE;

        for (Produto produto : produtos) {
            valorTotal += produto.getValor();
            if (produto.getValor() > valorMaximo) {
                valorMaximo = produto.getValor();
            }
            if (produto.getValor() < valorMinimo) {
                valorMinimo = produto.getValor();
            }
        }

        System.out.println("Valor médio: " + decimalFormat.format(valorTotal / produtos.size()));
        System.out.println("Valor máximo: " + decimalFormat.format(valorMaximo));
        System.out.println("Valor mínimo: " + decimalFormat.format(valorMinimo));
    }

    public static void main(String[] args) {
        AppCadastroProdutoEVenda cadastro = new AppCadastroProdutoEVenda();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("===== MENU =====");
            System.out.println("1 - Incluir produto");
            System.out.println("2 - Consultar produto");
            System.out.println("3 - Listagem de produtos");
            System.out.println("4 - Vendas por período - detalhado");
            System.out.println("5 - Realizar venda");
            System.out.println("0 - Sair");
            System.out.println("================");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("Informe o código do produto:");
                    int codigo = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Informe o nome do produto:");
                    String nome = scanner.nextLine();

                    System.out.println("Informe o valor do produto:");
                    double valor = scanner.nextDouble();
                    scanner.nextLine();

                    System.out.println("Informe a quantidade em estoque:");
                    int quantidadeEmEstoque = scanner.nextInt();
                    scanner.nextLine();

                    Produto produto = new Produto(codigo, nome, valor, quantidadeEmEstoque);
                    cadastro.adicionarProduto(produto);
                    System.out.println("Produto cadastrado com sucesso.");
                    break;
                case 2:
                    cadastro.exibirProdutos();
                    break;
                case 3:
                    cadastro.exibirProdutos();
                    break;
                case 4:
                    Relatorio.exibirVendasDetalhadasPorPeriodo(cadastro.vendas);
                    break;
                case 5:
                    cadastro.realizarVenda();
                    break;
                case 0:
                    System.out.println("Encerrando o programa.");
                    System.exit(0);
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}