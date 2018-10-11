import myLang.block.BlockContext;
import myLang.interpreter.MyLangInterpreter;
import myLang.response.MyLangException;
import myLangParser.MyLangLexer;
import myLangParser.MyLangParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class TestBase {
    final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    static {
        System.setProperty("line.separator","\r\n");
    }

    void run(String input){
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

    void runWithException(String input, String errorMsg){
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
        /*Class<?> blockContextClass = getClass().getClassLoader().loadClass(BlockContext.class.getCanonicalName());

        Object foo = blockContextClass.newInstance();*/
        BlockContext.clear();
        System.setOut(originalOut);
    }
}
