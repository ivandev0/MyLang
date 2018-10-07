import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import response.MyLangException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ExceptionsTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    private void run(String input, String errorMsg){
        MyLangLexer lexer = new MyLangLexer(CharStreams.fromString(input));

        CommonTokenStream tokens = new CommonTokenStream(lexer);
        MyLangParser parser = new MyLangParser(tokens);
        try {
            new MyLangInterpreter().visitCompilationUnit(parser.compilationUnit());
            Assert.fail();
        } catch (MyLangException e) {
            //e.printStackTrace();
            Assert.assertEquals(errorMsg, e.getMessage());
        }
    }

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    public void mainTest() {
        String input = "void main()" +
                "{" +
                "return 1;" +
                "}";
        run(input, "Первая функция должна быть main без аргументов, возвращающая int");

        input = "int main(int i)" +
                "{" +
                "return 1;" +
                "}";
        run(input, "Первая функция должна быть main без аргументов, возвращающая int");

        input = "int main()" +
                "{" +
                "" +
                "}";
        run(input, "Функция main должна возвращать тип int");

        input = "int main()" +
                "{" +
                "return ;" +
                "}";
        run(input, "Функция main должна возвращать тип int");
    }

    @Test
    public void blockTest() {
        String input = "int main()" +
                "{" +
                "j = 0;" +
                "return 1;" +
                "}";
        run(input, "Переменная j не объявлена");

        input = "int main()" +
                "{" +
                "int j = 0;" +
                "int j = 2;" +
                "return 1;" +
                "}";
        run(input, "Переменная j уже существует");

        input = "int main()" +
                "{" +
                "int j = 2;" +
                "j /= 0;" +
                "return 1;" +
                "}";
        run(input, "Деление на 0");

        input = "int main()" +
                "{" +
                "output(1);" +
                "return 1;" +
                "}";
        run(input, "Функция output не объявлена");

        input = "int main()" +
                "{" +
                "output(1);" +
                "return 1;" +
                "}" +
                "void output(int i, int j)" +
                "{" +
                "return ;" +
                "}";
        run(input, "Неверное количество параметров для функции output\n" +
                "Ожидалось 2 аргументов");

        input = "int main()" +
                "{" +
                "output(1);" +
                "return 1;" +
                "}" +
                "void output(int i)" +
                "{" +
                "return 1;" +
                "}";
        run(input, "Функция output должна возвращать значение типа void");

        input = "int main()" +
                "{" +
                "output(1);" +
                "return 1;" +
                "}" +
                "int output(int i)" +
                "{" +
                "return ;" +
                "}";
        run(input, "Функция output должна возвращать значение типа int");

        input = "int main()" +
                "{" +
                "output(1);" +
                "return 1;" +
                "}" +
                "int output(int i)" +
                "{" +
                "" +
                "}";
        run(input, "Функция output должна возвращать значение типа int");
    }

    @Test
    public void arithmeticTest() {
        String input = "int main()" +
                "{" +
                "print 1 || 2;" +
                "return 1;" +
                "}";
        run(input, "Оператор || не применим к типу int");

        input = "int main()" +
                "{" +
                "print 1 && 2;" +
                "return 1;" +
                "}";
        run(input, "Оператор && не применим к типу int");

        input = "int main()" +
                "{" +
                "print output(1);" +
                "return 1;" +
                "}";
        run(input, "Функция output не объявлена");

        input = "int main()" +
                "{" +
                "print output(1);" +
                "return 1;" +
                "}" +
                "" +
                "void output(int i)" +
                "{" +
                "" +
                "}";
        run(input, "Функция output не возвращает результата");
    }
}
