package utils;
/**
 * Classe responsavel por criar e manipular o arquivo de report
 *
 * @author Ricardo Oliveira
 */
public class Enum {
    public enum Status{
        DEBUG,
        ERROR,
        FAIL,
        FATAL,
        INFO,
        PASS,
        SKIP,
        WARNING
    }

    public enum Navegador{
        CHROME,
        IE,
        FIREFOX
    }
}