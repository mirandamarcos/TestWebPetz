package pageObjects;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import static org.junit.Assert.assertEquals;

public class Object extends Page {
    public Object(WebDriver driver) {

        super(driver);
    }

    public void preencherPesquisa(String pesquisa)throws InterruptedException {
        Thread.sleep(3000);
        txtPesquisa.sendKeys(pesquisa);


    }
    public void selecionarproduto() throws InterruptedException{
        Thread.sleep(3000);
        selecaoRacao.click();
    }
    public Boolean validarProduto(){
        return validarProduto.isDisplayed();
    }

    public void adcionarCarrinho() throws InterruptedException{
        produtoCarrinho.click();
        Thread.sleep(5000);
    }

    public Boolean validarProdutoIgual(){
        return dadosIdenticos.isDisplayed();
    }
}
