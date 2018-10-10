import static org.junit.Assert.*;
import org.junit.Test;

public class StatementsTest extends TestBase{
    @Test
    public void assignmentTest() {
        String input = "int main()" +
                "{" +
                "int i = 0;" +
                "print i+=4;" +
                "print i*=4;" +
                "print i-=6;" +
                "print i/=2;" +
                "print i = 30;" +
                "print i;" +
                "return 1;" +
                "}";
        run(input);
        assertEquals("4\r\n16\r\n10\r\n5\r\n30\r\n30\r\n", outContent.toString());
    }

    @Test
    public void longAssignmentTest() {
        String input = "int main()" +
                "{" +
                "int i = 0, j = 0;" +
                "print i = j = 3;" +
                "print i, j;" +
                "print i *= j += 4;" +
                "print j;" +
                "print i -= j = 2;" +
                "print j;" +
                "return 1;" +
                "}";
        run(input);
        assertEquals("3\r\n3 3\r\n21\r\n7\r\n19\r\n2\r\n", outContent.toString());
    }

    @Test
    public void visibilityTest() {
        String input = "int main(){" +
                "int i = 90;" +
                "if(2 > 1){" +
                "   int a = 1;" +
                "   if(3 > 2) {" +
                "       print a, i;" +
                "   }" +
                "}" +
                "return 1;" +
                "}";
        run(input);
        assertEquals("1 90\r\n", outContent.toString());
    }

    @Test
    public void ifThenTestBlock() {
        String input = "int main(){" +
                "int i = 90;" +
                "int j = 1;" +
                "if(j < i){" +
                "   int a = -1;" +
                "   print j;" +
                "   print i;" +
                "   print a;" +
                "}" +
                "return 1;" +
                "}";
        run(input);
        assertEquals("1\r\n90\r\n-1\r\n", outContent.toString());
    }

    @Test
    public void ifThenTestWithoutBlock() {
        String input = "int main(){" +
                "int i = 90;" +
                "int j = 1;" +
                "if(j > i)" +
                "   print j;" +
                "   print i;" +
                "return 1;" +
                "}";
        run(input);
        assertEquals("90\r\n", outContent.toString());
    }

    @Test
    public void ifThenElseTest() {
        String input = "int main(){" +
                "int i = 90;" +
                "int j = 1;" +
                "if(j > i) {" +
                "   print \"if block\";" +
                "} else {" +
                "   print \"else block\";" +
                "}" +
                "if(j < i) {" +
                "   print \"if block\";" +
                "} else {" +
                "   print \"else block\";" +
                "}" +
                "return 1;" +
                "}";
        run(input);
        assertEquals("else block\r\nif block\r\n", outContent.toString());
    }

    @Test
    public void ifThenElseCascade() {
        String input = "int main(){" +
                "int i = 90;" +
                "int j = 1;" +
                "if(j > i) {" +
                "   print \"if block\";" +
                "} else if (i > 100){" +
                "   print \"else1 block\";" +
                "} else if(i < 100) {" +
                "   print \"else2 block\";" +
                "}" +
                "return 1;" +
                "}";
        run(input);
        assertEquals("else2 block\r\n", outContent.toString());
    }

    @Test
    public void forLoopTest1() {
        String input = "int main(){" +
                "for(int i = 0; i < 5; i+=1){" +
                "   print i;" +
                "}" +
                "return 1;" +
                "}";
        run(input);
        assertEquals("0\r\n1\r\n2\r\n3\r\n4\r\n", outContent.toString());
    }

    @Test
    public void forLoopTest2() {
        String input = "int main(){" +
                "for(int i = 0, j = 5; i < 5 && j < 8; i+=1, j += 2){" +
                "   print i, j;" +
                "}" +
                "return 1;" +
                "}";
        run(input);
        assertEquals("0 5\r\n1 7\r\n", outContent.toString());
    }

    @Test
    public void localVariableTest() {
        String input = "int main(){" +
                "int i = 10;" +
                "print i;" +
                "fun();" +
                "print i;" +
                "return 1;" +
                "}" +
                "void fun(){" +
                "   int i = 20;" +
                "   print i;" +
                "}";
        run(input);
        assertEquals("10\r\n20\r\n10\r\n", outContent.toString());
    }
}
