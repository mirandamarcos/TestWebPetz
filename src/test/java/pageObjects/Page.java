package pageObjects;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
public class Page {

    WebDriver driver;

    public Page(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
    @FindBy(xpath ="//input[@id='search']")
    WebElement txtPesquisa;

    @FindBy(xpath="//div[contains(text(),' Golden Power Training para Cães Adultos Sabor Frango e Arroz - 15kg')]")
    WebElement selecaoRacao;

    @FindBy(xpath = "//main[@class='container-produto']")
    WebElement validarProduto;

    @FindBy(id = "adicionarAoCarrinho")
    WebElement produtoCarrinho;

    @FindBy(xpath = "//table[@class='table table-sacola']")
    WebElement dadosIdenticos;
}


