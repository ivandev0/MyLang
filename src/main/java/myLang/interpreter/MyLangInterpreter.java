package myLang.interpreter;

import myLang.block.BlockContext;
import myLang.response.MyLangException;
import myLang.response.Response;
import myLangParser.MyLangBaseVisitor;
import myLangParser.MyLangParser;

import java.util.LinkedList;
import java.util.stream.Collectors;

/**
 * Основной класс интерпретатор - точка входа.
 *
 * @author Ivan Kylchik
 */
public class MyLangInterpreter {

    public static LinkedList<Function> functions;

    /**
     * Единственнй метод класса {@link MyLangInterpreter}.
     * <p>
     * Находит все методы объявленные в коде, испоьзуя класс {@link FunctionVisitor},
     * и добавляет их в статический список {@code functions}.
     * <p>
     * Вызывает обработчик блока функции описанный в классе {@link FunBodyVisitor}.
     *
     * @param ctx корень синтаксического дерева
     * @throws MyLangException
     * <or>
     *     <li>Перавя функция в файле не main</li>
     *     <li>Функция main принимает аргументы</li>
     *     <li>Функция main возвращает не int</li>
     *     <li>Все принятые исключения из вызванных классов</li>
     * </or>
     */
    public void visitCompilationUnit(MyLangParser.CompilationUnitContext ctx) throws MyLangException {
        FunctionVisitor functionVisitor = new FunctionVisitor();
        functions = ctx.funDeclaration()
                .stream()
                .map(function -> function.accept(functionVisitor))
                .collect(Collectors.toCollection(LinkedList::new));

        if(functions.size() == 0){
            System.exit(0);
        }

        if (!functions.getFirst().name.equals("main") || functions.getFirst().args.size() != 0 || !functions.getFirst().resultType.equals("int")){
            throw new MyLangException("Первая функция должна быть main без аргументов, возвращающая int");
        }

        new FunBodyVisitor().visitBlock(functions.getFirst().ctx.block());
        functions.clear();
    }

    /**
     * Класс содержит единственный метод {@code visitFunDeclaration} наследованный от {@link MyLangBaseVisitor}
     */
    class FunctionVisitor extends MyLangBaseVisitor<Function> {
        /**
         * Пробегает по всех блокам и парсит аргументы, блок  и возвращаемый тип.
         * @param ctx лист дерева с объявлением метода.
         * @return объяект класса {@link Function} содержащий всю необходимую информацию о методе.
         */
        @Override
        public Function visitFunDeclaration(MyLangParser.FunDeclarationContext ctx) {
            String resultType = ctx.getChild(0).getText();
            String name = ctx.getChild(1).getText();

            FunArgsVisitor funArgsVisitor = new FunArgsVisitor();
            LinkedList<FunArgs> funArgs = new LinkedList<>();
            if(ctx.funCallArgs() != null) {
                funArgs = ctx.funCallArgs().funArgs()
                        .stream()
                        .map(args -> args.accept(funArgsVisitor))
                        .collect(Collectors.toCollection(LinkedList::new));
            }

            return new Function(name, resultType, funArgs, ctx);
        }
    }

    /**
     * Класс содержит единственный метод {@code visitFunArgs} наследованный от {@link MyLangBaseVisitor}
     */
    private class FunArgsVisitor extends MyLangBaseVisitor<FunArgs> {
        /**
         * Парсит аргумент метода.
         *
         * @param ctx лист с объявлением аргументов
         * @return объект класса {@link FunArgs} содержащий всю информацию об аргументе.
         */
        @Override
        public FunArgs visitFunArgs(MyLangParser.FunArgsContext ctx) {
            return new FunArgs(ctx.types().getText(), ctx.ID().getText());
        }


    }

    /**
     * Класс содержит единственный метод {@code visitBlock} наследованный от {@link MyLangBaseVisitor}
     */
    private class FunBodyVisitor{

        /**
         * Парсит блок метода.
         *
         * @param ctx лист содержащий блок метода
         * @throws MyLangException
         * <or>
         *     <li>Функция main не содержит оператора return, возвращающего int</li>
         *     <li>Все принятые исключения из вызванных классов</li>
         * </or>
         */
        void visitBlock(MyLangParser.BlockContext ctx) throws MyLangException{
            Response response = new BlockContext().handler(ctx);
            if(response.getResponse() == null || response.getResponse().equals("")){
                throw new MyLangException("Функция main должна возвращать тип int");
            }
        }

    }
}

