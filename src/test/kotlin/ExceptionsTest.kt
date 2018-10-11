import org.junit.Test

class ExceptionsTest : TestBase() {

    @Test
    fun mainTest1() {
        val input = "void main()" +
                "{" +
                "return 1;" +
                "}"
        runWithException(input, "Первая функция должна быть main без аргументов, возвращающая int")
    }

    @Test
    fun mainTest2() {
        val input = "int main(int i)" +
                "{" +
                "return 1;" +
                "}"
        runWithException(input, "Первая функция должна быть main без аргументов, возвращающая int")
    }

    @Test
    fun mainTest3() {
        val input = "int main()" +
                "{" +
                "" +
                "}"
        runWithException(input, "Функция main должна возвращать тип int")
    }

    @Test
    fun mainTest4() {
        val input = "int main()" +
                "{" +
                "return ;" +
                "}"
        runWithException(input, "Функция main должна возвращать тип int")
    }

    @Test
    fun mainTest5() {
        val input = "int main()" +
                "{" +
                "main();" +
                "return 1;" +
                "}"
        runWithException(input, "Нельзя вызывать рекурсивно функцию main")
    }

    @Test
    fun blockTest1() {
        val input = "int main()" +
                "{" +
                "j = 0;" +
                "return 1;" +
                "}"
        runWithException(input, "Переменная j не объявлена")
    }

    @Test
    fun blockTest2() {
        val input = "int main()" +
                "{" +
                "int j = 0;" +
                "int j = 2;" +
                "return 1;" +
                "}"
        runWithException(input, "Переменная j уже существует")
    }

    @Test
    fun blockTest3() {
        val input = "int main()" +
                "{" +
                "int j = 2;" +
                "j /= 0;" +
                "return 1;" +
                "}"
        runWithException(input, "Деление на 0")
    }

    @Test
    fun blockTest4() {
        val input = "int main()" +
                "{" +
                "output(1);" +
                "return 1;" +
                "}"
        runWithException(input, "Функция output не объявлена")
    }

    @Test
    fun blockTest5() {
        val input = "int main()" +
                "{" +
                "output(1);" +
                "return 1;" +
                "}" +
                "void output(int i, int j)" +
                "{" +
                "return ;" +
                "}"
        runWithException(input, "Неверное количество параметров для функции output\n" + "Ожидалось 2 аргументов")
    }

    @Test
    fun blockTest6() {
        val input = "int main()" +
                "{" +
                "output(1);" +
                "return 1;" +
                "}" +
                "void output(int i)" +
                "{" +
                "return 1;" +
                "}"
        runWithException(input, "Функция output должна возвращать значение типа void")
    }

    @Test
    fun blockTest7() {
        val input = "int main()" +
                "{" +
                "output(1);" +
                "return 1;" +
                "}" +
                "int output(int i)" +
                "{" +
                "return ;" +
                "}"
        runWithException(input, "Функция output должна возвращать значение типа int")
    }

    @Test
    fun blockTest8() {
        val input = "int main()" +
                "{" +
                "output(1);" +
                "return 1;" +
                "}" +
                "int output(int i)" +
                "{" +
                "" +
                "}"
        runWithException(input, "Функция output должна возвращать значение типа int")
    }

    @Test
    fun arithmeticTest1() {
        val input = "int main()" +
                "{" +
                "print 1 || 2;" +
                "return 1;" +
                "}"
        runWithException(input, "Оператор || не применим к типу int")
    }

    @Test
    fun arithmeticTest2() {
        val input = "int main()" +
                "{" +
                "print 1 && 2;" +
                "return 1;" +
                "}"
        runWithException(input, "Оператор && не применим к типу int")
    }

    @Test
    fun arithmeticTest3() {
        val input = "int main()" +
                "{" +
                "print output(1);" +
                "return 1;" +
                "}"
        runWithException(input, "Функция output не объявлена")
    }

    @Test
    fun arithmeticTest4() {
        val input = "int main()" +
                "{" +
                "print output(1);" +
                "return 1;" +
                "}" +
                "" +
                "void output(int i)" +
                "{" +
                "" +
                "}"
        runWithException(input, "Функция output не возвращает результата")
    }

    @Test
    fun visibilityTest1() {
        val input = "int main(){" +
                "int i = 4;" +
                "fun(i);" +
                "return 1;" +
                "}" +
                "void fun(int j) {" +
                "print i;" +
                "}"
        runWithException(input, "Переменная i не объявлена")
    }

    @Test
    fun visibilityTest2() {
        val input = "int main(){" +
                "int i = 4;" +
                "fun(i);" +
                "print a;" +
                "return 1;" +
                "}" +
                "void fun(int j) {" +
                "int a = 10;" +
                "}"
        runWithException(input, "Переменная a не объявлена")
    }

    @Test
    fun visibilityTest3() {
        val input = """int main(){
            int i = 4;
            fun(i);
            print c;
            return 1;
            }
            void fun(int j) {
            int a = 10;
            fun2(a);
            }
            void fun2(int b)
            {
            int c;
            }""".trimIndent()
        runWithException(input, "Переменная c не объявлена")
    }

    @Test
    fun blockVisibilityTest1() {
        val input = "int main(){" +
                "for(int i = 0; i < 10; i+=1){" +
                "}" +
                "print i;" +
                "return 1;" +
                "}"
        runWithException(input, "Переменная i не объявлена")
    }

    @Test
    fun blockVisibilityTest2() {
        val input = "int main(){" +
                "int j = 9;" +
                "if(j == 9){" +
                "   int i = 10;" +
                "}" +
                "print i;" +
                "return 1;" +
                "}"
        runWithException(input, "Переменная i не объявлена")
    }

    @Test
    fun intValidationTest1() {
        val input = "int main()" +
                "{" +
                "   print 2147483647;" + //max int

                "   print 2147483648;" +//max int + 1 -> exception

                "    return 1;" +
                "}"
        runWithException(input, "For input string: \"2147483648\"")
    }

    @Test
    fun intValidationTest2() {
        val input = "int main()" +
                "{" +
                "   print -2147483648;" + //min int

                "   print -2147483649;" +//min int - 1 -> exception

                "    return 1;" +
                "}"
        runWithException(input, "For input string: \"-2147483649\"")
    }

    @Test
    fun stackOverflowTest() {
        val input = "int main()" +
                "{" +
                "fun();" +
                "return 1;" +
                "}" +
                "void fun(){" +
                "   fun();" +
                "}"
        runWithException(input, "Произошло переполнение стека")
    }
}
