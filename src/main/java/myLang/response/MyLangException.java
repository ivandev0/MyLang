package myLang.response;

/**
 * Класс исключения, созданный для унификации всех ошибок.
 *
 * @author Ivan Kylchik
 */
public class MyLangException extends Exception {
    public MyLangException(String message) {
        super(message);
    }
}
