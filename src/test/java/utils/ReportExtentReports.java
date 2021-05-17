package utils;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Instant;

import javax.imageio.ImageIO;

import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
/**
 * Classe responsavel por criar e manipular o arquivo de report
 *
 * @author Ricardo Oliveira
 */
public class ReportExtentReports {
    public static ExtentReports extent;
    public static ExtentTest teste;
    public static ExtentTest suiteTeste;
    public static ExtentHtmlReporter htmlReporter;
    public static Boolean reportInicializado = false;

    /**
     * Inicializa o report (apenas uma vez)
     *
     * @param diretorioReport Diretorio aonde sera armazenado o report (caso nao
     *                        exista, o diretorio sera criado)
     * @param nomeReport      Nome do report HTML (sem extensao)
     */
    public synchronized static void iniciarReport(String diretorioReport, String nomeReport) {

        // Verifica se o report já foi inicializado
        if (!reportInicializado) {
            extent = new ExtentReports();
            File diretorioEvidencia = new File(diretorioReport);
            if (!diretorioEvidencia.exists()) {
                diretorioEvidencia.mkdirs();
            }
            htmlReporter = new ExtentHtmlReporter(diretorioReport + nomeReport + ".html");
            htmlReporter.setAppendExisting(true);
            htmlReporter.config().setDocumentTitle("Report de execucao");
            htmlReporter.config().setReportName("Automação");
            extent.attachReporter(htmlReporter);
            reportInicializado = true;

        }

    }

    /**
     * Finaliza o teste
     */
    public static void finalizarTeste() {
        extent.flush();
    }

    /**
     * Salva os dados do report
     */
    public static void finalizarReport() {
        extent.flush();
    }

    /**
     * Adiciona suite
     *
     * @param nomeClasseTeste
     * @param descricaoTeste
     */
    public static void adicionarSuiteTeste(String nomeClasseTeste, String descricaoTeste) {
        suiteTeste = extent.createTest(nomeClasseTeste, descricaoTeste);
    }

    /**
     * Adiciona teste na suite
     *
     * @param nomeClasseTeste
     * @param nomeTeste
     */
    public static void adicionarTeste(String nomeClasseTeste, String nomeTeste) {
        teste = suiteTeste.createNode(nomeTeste);
    }

    /**
     * Adiciona status e log de registro no caso de teste
     *
     * @param status    Enum do Status (caso o status seja FAIL, o teste irá falhar)
     * @param descricao Descricao do log
     */
    public static void adicionarLog(Enum.Status status, String descricao, Boolean evidencia) {
        try {
            if(evidencia) {
                teste.log(conversorStatus(status), descricao,
                        MediaEntityBuilder.createScreenCaptureFromPath(salvarScreenShot()).build());
            }
            else {
                teste.log(conversorStatus(status), descricao);
            }
            if (status == Enum.Status.FAIL) {
                Assert.fail(descricao);
            }
        }
        catch(Exception erro) {
            System.out.println("Erro ao adicionar log no relatório: " + erro);
        }

    }

    /**
     * Converte o status do enum para o status que é utilizado pela biblioteca
     * ExtentReports
     *
     * @param status Enum do status a ser convertido
     * @return Status convertido
     */
    private static Status conversorStatus(Enum.Status status) {

        Status reportStatus = null;
        switch (status) {
            case DEBUG:
                reportStatus = Status.DEBUG;
                break;
            case ERROR:
                reportStatus = Status.ERROR;
                break;
            case FAIL:
                reportStatus = Status.FAIL;
                break;
            case FATAL:
                reportStatus = Status.FATAL;
                break;
            case INFO:
                reportStatus = Status.INFO;
                break;
            case PASS:
                reportStatus = Status.PASS;
                break;
            case SKIP:
                reportStatus = Status.SKIP;
                break;
            case WARNING:
                reportStatus = Status.WARNING;
                break;
        }

        return reportStatus;

    }

    /**
     * Salvar screenShot e retorna o nome no formato para ser utilizado para anexo no ExtentReports
     * @return String Nome do arquivo gerado
     */
    public static String salvarScreenShot(){
        String evidenciaId = Long.toString(Instant.now().toEpochMilli());
        String formatoImagem = "jpeg";
        String nomeCompletoEvidencia = "target/evidencia/" + evidenciaId + "." + formatoImagem;
        evidenciarTelaNavegador(formatoImagem, nomeCompletoEvidencia).toString();
        return evidenciaId + "." + formatoImagem;
    }
    /**
     * Tira o PrintScreen do navegador e salva a imagem em um arquivo
     * @param formato
     * @param caminhoArquivo
     * @return File Retorna o arquivo do PrintScreen
     */
    public static File evidenciarTelaNavegador(String formato, String caminhoArquivo) {
        File file = null;

        try {
            file = ((TakesScreenshot) NavegadorFabrica.getDriver()).getScreenshotAs(OutputType.FILE);
            BufferedImage in = ImageIO.read(file);
            BufferedImage in_reduzida = diminuirImagem(in, 1);
            ImageIO.write(in_reduzida, formato, new File(caminhoArquivo));

        } catch (IOException e) {
            adicionarLog(Enum.Status.FAIL, "Erro ao evidenciar a tela no arquivo: " + caminhoArquivo + ". Erro: " + e, false);
        }
        return file;
    }

    /**
     * Diminui a imagem de acordo com a porcentagem informada
     * @param img BufferedImage a ser diminuida
     * @param porcentagem Porcentagem da nova imagem em relação à original
     * @return Imagem BufferedImage reduzida
     */
    private static BufferedImage diminuirImagem(BufferedImage img, double porcentagem) {

        int scaledWidth = (int) (img.getWidth() * porcentagem);
        int scaledHeight = (int) (img.getHeight() * porcentagem);

        Image tmp = img.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);

        BufferedImage imagemReduzida = new BufferedImage(scaledWidth, scaledHeight, BufferedImage.SCALE_DEFAULT);
        Graphics2D g2d = imagemReduzida.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return imagemReduzida;
    }
}