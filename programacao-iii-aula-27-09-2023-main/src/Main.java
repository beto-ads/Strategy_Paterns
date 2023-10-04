import br.edu.umfg.estrategia.Carrinho;
import br.edu.umfg.estrategia.MeioPagamentoCieloEstrategia;
import br.edu.umfg.estrategia.MeioPagamentoDinheiroEstrategia;
import br.edu.umfg.estrategia.Produto;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Produto produto1 = new Produto("0001",
                "Cola cola 350ml",3.59);
        Produto produto2 = new Produto("0002",
                "X-salada",15.99);
        Carrinho carrinho = new Carrinho();

        carrinho.adicionarProduto(produto1);
        carrinho.adicionarProduto(produto2);

        carrinho.pagar(new MeioPagamentoCieloEstrategia("0202",
                "785247893", "699", "10/2032" ));

        carrinho.pagar(new MeioPagamentoDinheiroEstrategia());
    }
}