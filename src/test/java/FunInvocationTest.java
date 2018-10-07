import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import response.MyLangException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class FunInvocationTest {
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
    public void printThroughFun() {
        String input = "int main()" +
                "{" +
                "output();" +
                "return 1;" +
                "}" +
                "void output()" +
                "{" +
                "print 1;" +
                "}";
        run(input);
        Assert.assertEquals("1\r\n", outContent.toString());
        outContent.reset();

        input = "int main()" +
                "{" +
                "output();" +
                "output();" +
                "return 1;" +
                "}" +
                "void output()" +
                "{" +
                "print 1;" +
                "}";
        run(input);
        Assert.assertEquals("1\r\n1\r\n", outContent.toString());
        outContent.reset();

        input = "int main()" +
                "{" +
                "output();" +
                "return 1;" +
                "}" +
                "void output()" +
                "{" +
                "print 1;" +
                "return ;" +
                "print 2;" +
                "}";
        run(input);
        Assert.assertEquals("1\r\n", outContent.toString());
        outContent.reset();

        input = "int main()" +
                "{" +
                "output();" +
                "return 1;" +
                "}" +
                "void output()" +
                "{" +
                "print output2(1);" +
                "}" +
                "int output2(int i)" +
                "{" +
                "return i;" +
                "}";
        run(input);
        Assert.assertEquals("1\r\n", outContent.toString());
        outContent.reset();

        input = "int main()" +
                "{" +
                "print output(output2(output3(1)));" +
                "return 1;" +
                "}" +
                "int output(int i)" +
                "{" +
                "return i + 1;" +
                "}" +
                "int output2(int i)" +
                "{" +
                "return i + 1;" +
                "}" +
                "int output3(int i)" +
                "{" +
                "return i;" +
                "}";
        run(input);
        Assert.assertEquals("3\r\n", outContent.toString());
        outContent.reset();
    }
}
