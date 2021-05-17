package utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import utils.Enum.Navegador;
public class NavegadorFabrica {
    private static WebDriver driver;

    public static WebDriver getDriver() {
        return driver;
    }
    public static WebDriver configurarNavegador(Navegador tipoNavegador) {
        switch (tipoNavegador) {
            case CHROME:
                ChromeOptions options= new ChromeOptions();
                options.addArguments("--disable-extensions");
                options.addArguments("--disable-notifications");
                System.setProperty("webdriver.chrome.driver", "src/test/java/drivers/chromedriver.exe");
                driver = new ChromeDriver(options);
                break;
            default:
                driver = null;
                break;
        }
        return driver;
    }

    public static void acessarPaginaWeb(String url) {
        driver.get(url);
    }
    public static void fecharNavegador() {
        driver.close();
    }

    public static void maximizar() {
        driver.manage().window().maximize();
    }
}