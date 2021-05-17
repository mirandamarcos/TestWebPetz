package Funcionalidades;
import org.openqa.selenium.WebDriver;
import utils.NavegadorFabrica;
import pageObjects.Object;
import utils.Enum.Navegador;
public class Funcionalidades {
    WebDriver driver;
    Object object;

    public void acessarTela() throws InterruptedException {
        NavegadorFabrica.configurarNavegador(Navegador.CHROME);
        driver = NavegadorFabrica.getDriver();
        NavegadorFabrica.maximizar();
        NavegadorFabrica.acessarPaginaWeb("https://www.petz.com.br");
        object = new Object(driver);
    }
    public void preencher(String pesquisa) throws InterruptedException {
        object.preencherPesquisa(pesquisa);
    }
    public void SelecionarProd(String prod) throws InterruptedException {
        object.selecionarproduto();
    }
    public void validarProduto() throws InterruptedException {
        assert(object.validarProduto());
    }

    public void adcionarProdutoCarrinho() throws InterruptedException {
        object.adcionarCarrinho();
    }

    public void validarProdutoIdentico() throws InterruptedException {
        assert(object.validarProdutoIgual());
    }
}
