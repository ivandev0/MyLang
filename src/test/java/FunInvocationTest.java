import static org.junit.Assert.*;
import org.junit.Test;

public class FunInvocationTest extends TestBase {

    @Test
    public void printThroughFun1() {
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
        assertEquals("1\r\n", outContent.toString());
    }
    @Test
    public void printThroughFun2() {
        String input = "int main()" +
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
        assertEquals("1\r\n1\r\n", outContent.toString());
    }
    @Test
    public void printThroughFun3() {
        String input = "int main()" +
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
        assertEquals("1\r\n", outContent.toString());
    }
    @Test
    public void printThroughFun4() {
        String input = "int main()" +
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
        assertEquals("1\r\n", outContent.toString());
    }
    @Test
    public void printThroughFun5() {
        String input = "int main()" +
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
        assertEquals("3\r\n", outContent.toString());
    }
}
