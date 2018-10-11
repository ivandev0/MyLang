import org.junit.Assert.*
import org.junit.Test

class FunInvocationTest : TestBase() {

    @Test
    fun printThroughFun1() {
        val input = "int main()" +
                "{" +
                "output();" +
                "return 1;" +
                "}" +
                "void output()" +
                "{" +
                "print 1;" +
                "}"
        run(input)
        assertEquals("1\r\n", outContent.toString())
    }

    @Test
    fun printThroughFun2() {
        val input = "int main()" +
                "{" +
                "output();" +
                "output();" +
                "return 1;" +
                "}" +
                "void output()" +
                "{" +
                "print 1;" +
                "}"
        run(input)
        assertEquals("1\r\n1\r\n", outContent.toString())
    }

    @Test
    fun printThroughFun3() {
        val input = "int main()" +
                "{" +
                "output();" +
                "return 1;" +
                "}" +
                "void output()" +
                "{" +
                "print 1;" +
                "return ;" +
                "print 2;" +
                "}"
        run(input)
        assertEquals("1\r\n", outContent.toString())
    }

    @Test
    fun printThroughFun4() {
        val input = "int main()" +
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
                "}"
        run(input)
        assertEquals("1\r\n", outContent.toString())
    }

    @Test
    fun printThroughFun5() {
        val input = "int main()" +
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
                "}"
        run(input)
        assertEquals("3\r\n", outContent.toString())
    }
}
