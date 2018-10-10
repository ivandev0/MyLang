import org.junit.Test;

public class ExceptionsTest extends TestBase{

    @Test
    public void mainTest1() {
        String input = "void main()" +
                "{" +
                "return 1;" +
                "}";
        runWithException(input, "Первая функция должна быть main без аргументов, возвращающая int");
    }
    @Test
    public void mainTest2() {
        String input = "int main(int i)" +
                "{" +
                "return 1;" +
                "}";
        runWithException(input, "Первая функция должна быть main без аргументов, возвращающая int");
    }
    @Test
    public void mainTest3() {
        String input = "int main()" +
                "{" +
                "" +
                "}";
        runWithException(input, "Функция main должна возвращать тип int");
    }
    @Test
    public void mainTest4() {
        String input = "int main()" +
                "{" +
                "return ;" +
                "}";
        runWithException(input, "Функция main должна возвращать тип int");
    }

    @Test
    public void mainTest5() {
        String input = "int main()" +
                "{" +
                "main();" +
                "return 1;" +
                "}";
        runWithException(input, "Нельзя вызывать рекурсивно функцию main");
    }

    @Test
    public void blockTest1() {
        String input = "int main()" +
                "{" +
                "j = 0;" +
                "return 1;" +
                "}";
        runWithException(input, "Переменная j не объявлена");
    }

    @Test
    public void blockTest2() {
        String input = "int main()" +
                "{" +
                "int j = 0;" +
                "int j = 2;" +
                "return 1;" +
                "}";
        runWithException(input, "Переменная j уже существует");
    }
    @Test
    public void blockTest3() {
        String input = "int main()" +
                "{" +
                "int j = 2;" +
                "j /= 0;" +
                "return 1;" +
                "}";
        runWithException(input, "Деление на 0");
    }
    @Test
    public void blockTest4() {
        String input = "int main()" +
                "{" +
                "output(1);" +
                "return 1;" +
                "}";
        runWithException(input, "Функция output не объявлена");
    }
    @Test
    public void blockTest5() {
        String input = "int main()" +
                "{" +
                "output(1);" +
                "return 1;" +
                "}" +
                "void output(int i, int j)" +
                "{" +
                "return ;" +
                "}";
        runWithException(input, "Неверное количество параметров для функции output\n" +
                "Ожидалось 2 аргументов");
    }
    @Test
    public void blockTest6() {
        String input = "int main()" +
                "{" +
                "output(1);" +
                "return 1;" +
                "}" +
                "void output(int i)" +
                "{" +
                "return 1;" +
                "}";
        runWithException(input, "Функция output должна возвращать значение типа void");
    }
    @Test
    public void blockTest7() {
        String input = "int main()" +
                "{" +
                "output(1);" +
                "return 1;" +
                "}" +
                "int output(int i)" +
                "{" +
                "return ;" +
                "}";
        runWithException(input, "Функция output должна возвращать значение типа int");
    }
    @Test
    public void blockTest8() {
        String input = "int main()" +
                "{" +
                "output(1);" +
                "return 1;" +
                "}" +
                "int output(int i)" +
                "{" +
                "" +
                "}";
        runWithException(input, "Функция output должна возвращать значение типа int");
    }

    @Test
    public void arithmeticTest1() {
        String input = "int main()" +
                "{" +
                "print 1 || 2;" +
                "return 1;" +
                "}";
        runWithException(input, "Оператор || не применим к типу int");
    }
    @Test
    public void arithmeticTest2() {
        String input = "int main()" +
                "{" +
                "print 1 && 2;" +
                "return 1;" +
                "}";
        runWithException(input, "Оператор && не применим к типу int");
    }
    @Test
    public void arithmeticTest3() {
        String input = "int main()" +
                "{" +
                "print output(1);" +
                "return 1;" +
                "}";
        runWithException(input, "Функция output не объявлена");
    }
    @Test
    public void arithmeticTest4() {
        String input = "int main()" +
                "{" +
                "print output(1);" +
                "return 1;" +
                "}" +
                "" +
                "void output(int i)" +
                "{" +
                "" +
                "}";
        runWithException(input, "Функция output не возвращает результата");
    }

    @Test
    public void visibilityTest1() {
        String input = "int main(){" +
                "int i = 4;" +
                "fun(i);" +
                "return 1;" +
                "}" +
                "void fun(int j) {" +
                "print i;" +
                "}";
        runWithException(input, "Переменная i не объявлена");
    }

    @Test
    public void visibilityTest2() {
        String input = "int main(){" +
                "int i = 4;" +
                "fun(i);" +
                "print a;" +
                "return 1;" +
                "}" +
                "void fun(int j) {" +
                "int a = 10;" +
                "}";
        runWithException(input, "Переменная a не объявлена");
    }

    @Test
    public void visibilityTest3() {
        String input = "int main(){" +
                "int i = 4;" +
                "fun(i);" +
                "print c;" +
                "return 1;" +
                "}" +
                "void fun(int j) {" +
                "int a = 10;" +
                "fun2(a);" +
                "}" +
                "void fun2(int b)" +
                "{" +
                "int c;" +
                "}";
        runWithException(input, "Переменная c не объявлена");
    }

    @Test
    public void blockVisibilityTest1() {
        String input = "int main(){" +
                "for(int i = 0; i < 10; i+=1){" +
                "}" +
                "print i;" +
                "return 1;"+
                "}";
        runWithException(input, "Переменная i не объявлена");
    }

    @Test
    public void blockVisibilityTest2() {
        String input = "int main(){" +
                "int j = 9;" +
                "if(j == 9){" +
                "   int i = 10;" +
                "}" +
                "print i;" +
                "return 1;"+
                "}";
        runWithException(input, "Переменная i не объявлена");
    }

    @Test
    public void intValidationTest1() {
        String input = "int main()" +
                "{" +
                "   print 2147483647;" + //max int
                "   print 2147483648;" +//max int + 1 -> exception
                "    return 1;" +
                "}";
        runWithException(input, "For input string: \"2147483648\"");
    }

    @Test
    public void intValidationTest2() {
        String input = "int main()" +
                "{" +
                "   print -2147483648;" + //min int
                "   print -2147483649;" +//min int - 1 -> exception
                "    return 1;" +
                "}";
        runWithException(input, "For input string: \"-2147483649\"");
    }
}
