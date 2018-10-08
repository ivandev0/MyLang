import myLang.interpreter.MyLangInterpreter;
import myLangParser.MyLangLexer;
import myLangParser.MyLangParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import myLang.response.MyLangException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ExpressionsTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    private void run(String input){
        MyLangLexer lexer = new MyLangLexer(CharStreams.fromString(input));

        CommonTokenStream tokens = new CommonTokenStream(lexer);
        MyLangParser parser = new MyLangParser(tokens);
        try {
            new MyLangInterpreter().visitCompilationUnit(parser.compilationUnit());
        } catch (MyLangException e) {
            e.printStackTrace();
            Assert.fail();
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
    public void arithmeticTest() {
        String input = "int main()" +
                "{" +
                "print 1 + 2;" +
                "print 4 * 3;" +
                "print 4 / 2;" +
                "print 5 / 2;" +
                "print 6 - 3;" +
                "print 1 + 2 * 3;" +
                "print (1 + 2) * 3;" +
                "print (1 - (3 + 4)) * 1;" +
                "print 1 - 10;" +
                "return 1;" +
                "}";
        run(input);
        Assert.assertEquals("3\r\n12\r\n2\r\n2\r\n3\r\n7\r\n9\r\n-6\r\n-9\r\n", outContent.toString());
    }

    @Test
    public void logicTest() {
        String input = "int main()" +
                "{" +
                "print 1 + 2 == 3;" +
                "print 4 * 3 > 12;" +
                "print 4 / 2 <= 2;" +
                "print 5 / 2 >= 2;" +
                "print 6 - 3 < 10;" +
                "print 1 + 2 * 3 != (1 + 2) * 3;" +
                "print (1 + 2) * 3 != 9;" +
                "print (1 - (3 + 4)) * 1 < -1;" +
                "print 1 - 10 > -20;" +
                "return 1;" +
                "}";
        run(input);
        Assert.assertEquals("true\r\nfalse\r\ntrue\r\ntrue\r\ntrue\r\ntrue\r\nfalse\r\ntrue\r\ntrue\r\n", outContent.toString());
    }

    @Test
    public void longExpressions() {
        String input = "int main()" +
                "{" +
                "print 1 + 2 == 3 && 1 > 2;" +
                "print 4 * 3 > 12 || 15 > 1;" +
                "print 4 / 2 <= 2 && 1 >= 1 && 4 >= 1;" +
                "return 1;" +
                "}";
        run(input);
        Assert.assertEquals("false\r\ntrue\r\ntrue\r\n", outContent.toString());
    }
}
