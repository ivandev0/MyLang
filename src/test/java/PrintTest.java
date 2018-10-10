import static org.junit.Assert.*;
import org.junit.Test;

public class PrintTest extends TestBase{

    @Test
    public void printNothing() {
        String input = "int main()" +
                "{" +
                "return 1;" +
                "}";
        run(input);
        assertEquals("", outContent.toString());
    }

    @Test
    public void printOneNum() {
        String input = "int main()" +
                "{" +
                "print 1;" +
                "return 1;" +
                "}";
        run(input);
        assertEquals("1\r\n", outContent.toString());
    }

    @Test
    public void printMultipleNum() {
        String input = "int main()" +
                "{" +
                "print 1;" +
                "print 2;" +
                "print 3;" +
                "print 10;" +
                "return 1;" +
                "}";
        run(input);
        assertEquals("1\r\n2\r\n3\r\n10", outContent.toString().trim());
    }

    @Test
    public void printMultipleNumInRaw() {
        String input = "int main()" +
                "{" +
                "print 1, 2, 3, 10;" +
                "print 20;" +
                "return 1;" +
                "}";
        run(input);
        assertEquals("1 2 3 10\r\n20", outContent.toString().trim());
    }

    @Test
    public void printOneString() {
        String input = "int main()" +
                "{" +
                "print \"x\";" +
                "return 1;" +
                "}";
        run(input);
        assertEquals("x\r\n", outContent.toString());
    }

    @Test
    public void printMultipleString() {
        String input = "int main()" +
                "{" +
                "print \"x\";" +
                "print \"x x\";" +
                "print \"abcd\";" +
                "print \"abcd efgh\";" +
                "return 1;" +
                "}";
        run(input);
        assertEquals("x\r\nx x\r\nabcd\r\nabcd efgh", outContent.toString().trim());
    }

    @Test
    public void printMultipleStringInRaw() {
        String input = "int main()" +
                "{" +
                "print \"x\", \"x x\", \"abcd\";" +
                "print \"abcd efgh\";" +
                "return 1;" +
                "}";
        run(input);
        assertEquals("x x x abcd\r\nabcd efgh", outContent.toString().trim());
    }

    @Test
    public void printOneStringAndNum() {
        String input = "int main()" +
                "{" +
                "print \"x\": 1;" +
                "return 1;" +
                "}";
        run(input);
        assertEquals("x 1\r\n", outContent.toString());
    }

    @Test
    public void printMultipleStringAndNum() {
        String input = "int main()" +
                "{" +
                "print \"x\": 1;" +
                "print \"x x\": 2;" +
                "print \"abcd\": 10;" +
                "print \"abcd efgh\": 20;" +
                "return 1;" +
                "}";
        run(input);
        assertEquals("x 1\r\nx x 2\r\nabcd 10\r\nabcd efgh 20", outContent.toString().trim());
    }

    @Test
    public void printMultipleStringAndNumInRaw() {
        String input = "int main()" +
                "{" +
                "print \"x\": 1, \"x x\" : 2, \"abcd\" : 10;" +
                "print \"abcd efgh\" : 20;" +
                "return 1;" +
                "}";
        run(input);
        assertEquals("x 1 x x 2 abcd 10\r\nabcd efgh 20", outContent.toString().trim());
    }

    @Test
    public void printExpression() {
        String input = "int main()" +
                "{" +
                "print 1 + 2;" +
                "print 1 > 2;" +
                "print 2 == 2;" +
                "return 1;" +
                "}";
        run(input);
        assertEquals("3\r\nfalse\r\ntrue\r\n", outContent.toString());
    }

    @Test
    public void printVariable() {
        String input = "int main()" +
                "{" +
                "int j;" +
                "print j;" +
                "j = 3;" +
                "print j;" +
                "print j + 3;" +
                "print j > 2;" +
                "return 1;" +
                "}";
        run(input);
        assertEquals("0\r\n3\r\n6\r\ntrue", outContent.toString().trim());
    }
}
