package steps;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import Funcionalidades.Funcionalidades;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utils.Enum.Status;
import utils.NavegadorFabrica;
import utils.ReportExtentReports;

public class stepDefinitions {
    WebDriver driver;
    Funcionalidades func = new Funcionalidades();

    @Dado("que tenha acessado a tela do ecommerce")
    public void que_tenha_acessado_a_tela_site() {
        try {

            func.acessarTela();
            ReportExtentReports.adicionarLog(Status.PASS,"Dado que tenha acessado a tela do ecommerce", true);
        }
        catch(Throwable erro) {
            ReportExtentReports.adicionarLog(Status.FAIL,"Erro: " + erro, true);
        }
    }
    @E("deve inserir um texto no input para consulta do produto {string} e retornar com sucesso")
    public void preencher_informacao(String informacao) {
        try {
            func.preencher(informacao);
            ReportExtentReports.adicionarLog(Status.PASS,"E deve inserir um texto no input para consulta do produto "+informacao+"e retornar com sucesso", true);
        }
        catch(Throwable erro) {
            ReportExtentReports.adicionarLog(Status.FAIL,"Erro: " + erro, true);
        }

    }
    @E("deve selecionar a ração {string} e retornar com sucesso")
    public void deve_selecionar_a_racao(String prod) {
        try {
            func.SelecionarProd(prod);
            ReportExtentReports.adicionarLog(Status.PASS,"E deve selecionar a ração "+prod+"e retornar com sucesso true",true);
        }
        catch(Throwable erro) {
            ReportExtentReports.adicionarLog(Status.FAIL,"Erro: " + erro, true);
        }
    }

    @E("Validar o nome do produto, fornecedor, preço normal e preço para assinante")
    public void ValidacaoDoProduto() {
        try {
            func.validarProduto();
            ReportExtentReports.adicionarLog(Status.PASS,"E Validar o nome do produto, fornecedor, preço normal e preço para assinante", true);
        }
        catch(Throwable erro) {
            ReportExtentReports.adicionarLog(Status.FAIL,"Erro: " + erro, true);
        }

    }

    @E("Inserir o produto no carrinho de compras")
    public void InserirProdutoCarrinho() {
        try {
            func.adcionarProdutoCarrinho();
            ReportExtentReports.adicionarLog(Status.PASS,"Quando Inserir o produto no carrinho de compras", true);
        }
        catch(Throwable erro) {
            ReportExtentReports.adicionarLog(Status.FAIL,"Erro: " + erro, true);
        }

    }

    @E("deve validar se os dados do item 3 continuam idênticos")
    public void deve_validar_se_os_dados_do_item_3_continuam_identicos() {
        try {
            func.validarProdutoIdentico();
            ReportExtentReports.adicionarLog(Status.PASS,"Entao deve validar se os dados do item 3 continuam idênticos", true);
        }
        catch(Throwable erro) {
            ReportExtentReports.adicionarLog(Status.FAIL,"Erro: " + erro, true);
        }

    }
    @Before
    public static void adicionarTesteNoReport(Scenario scenario) {
        String nomeTeste = scenario.getName();
        ReportExtentReports.adicionarTeste("PETZ WEB", nomeTeste);
    }
    @After
    public static void finalizarTesteNoReport() {
        ReportExtentReports.finalizarTeste();
        NavegadorFabrica.fecharNavegador();
    }
}

