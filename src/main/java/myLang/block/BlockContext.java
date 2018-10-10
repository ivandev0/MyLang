package myLang.block;

import myLang.ContextHandler;
import myLang.expression.ExpressionContext;
import myLang.interpreter.FunArgs;
import myLang.interpreter.Variable;
import myLang.response.*;
import myLangParser.MyLangParser;

import java.util.*;

/**
 * Данный класс расширяет интерфейс {@link ContextHandler}
 * и реализует основной обработчик всех блоков, заключенных в фигурные скобки { }.
 * <p>
 * В данном класе хранятся следующие статичные поля:
 * <ol>
 *     <li> {@code LinkedList<Set<Variable>> localStorage}
 *     Содержит список Set из Variable. Каждый Set представляет собой вложенный блок.</li>
 *     <li>{@code Deque<LinkedList<Set<Variable>>> stack}
 *     Содержит списки блоков и необходим для переключения и восстановления контекста.</li>
 * </ol>
 */
public class BlockContext implements ContextHandler<MyLangParser.BlockContext> {

    static private LinkedList<Set<Variable>> localStorage = new LinkedList<>();
    static private Deque<LinkedList<Set<Variable>>> stack = new LinkedList<>();

    /**
     * Возвращает значение переменной по имени, если она была объявлена.
     *
     * @param name имя переменной
     * @return значение переменной
     * @throws MyLangException если переменной не существует
     */
    public static Variable get(String name) throws MyLangException{
        Variable var = localStorage
                .stream()
                .flatMap(Collection::stream)
                .filter(obj -> obj.getName().equals(name))
                .findFirst()
                .orElse(null);
        if(var == null){
            throw new MyLangException("Переменная " + name + " не объявлена");
        }
        return var;
    }

    /**
     * Добавляет новую переменную в список.
     *
     * @param name имя переменной
     * @param value значение переменной
     * @param <T> тип переменной
     * @throws MyLangException если переменная была объявлена ранее
     */
    public static <T> void add(String name, T value) throws MyLangException{
        if(!localStorage.getLast().add(new Variable<>(name, value))){
            throw new MyLangException("Переменная " + name + " уже существует");
        }
    }

    /**
     * Обновляет значение переменной на новое.
     *
     * @param name имя переменной
     * @param newValue новое значение переменной
     * @param <T> тип переменной
     * @return возвращает {@code newValue} для конвееризации
     * @throws MyLangException если переменной не существует.
     */
    public static <T> T update(String name, T newValue) throws MyLangException{
        Variable var = localStorage
                .stream()
                .flatMap(Collection::stream)
                .filter(obj -> obj.getName().equals(name))
                .findFirst()
                .orElse(null);

        if(var == null){
            throw new MyLangException("Переменная " + name + " не объявлена");
        }
        var.updateValue(newValue);
        return newValue;
    }

    /**
     * Создает новый Set в {@code localStorage} при инициализации нового блока.
     */
    public static void createNestedStorage(){
        localStorage.addLast(new HashSet<>());
    }

    /**
     * Удаляет последний созданный Set в {@code localStorage} при выходе из блока.
     */
    public static void deleteNestedStorage() {
        localStorage.removeLast();
    }

    /**
     * Добавляет контекст текущей программы в стэк. Вызывается при вызове метода.
     * Здесь же реализуется сопоставление передаваемых и ожидаемых аргументов.
     *
     * @param names список ожидаемых аргументов
     * @param values список передаваемых аргументов
     * @throws MyLangException пробрасывается из внутреннего обработчика {@code handler}
     */
    public static void pushStack(LinkedList<FunArgs> names, List<MyLangParser.ExpressionContext> values) throws MyLangException{
        createNestedStorage();
        for (int i = 0; i < values.size(); i++){
            add(names.get(i).getName(), new ExpressionContext().handler(values.get(i)).getResponse());
        }
        Set<Variable> set = localStorage.getLast();
        localStorage.removeLast();

        stack.addFirst(new LinkedList<>(localStorage));
        localStorage.clear();

        localStorage.add(set);
    }

    /**
     * Восстанавливает контекст прерванного метода.
     */
    public static void popStack(){
        deleteNestedStorage();
        localStorage = stack.poll();
    }

    /**
     * Данный метод используется при работе с тестами. Он очищает {@code localStorage} и {@code stack}.
     */
    public static void clear(){
        localStorage.clear();
        stack.clear();
    }

    /**
     * Основной блок обработчик блока метода. В цикле шаг за шагом вызывается каждая строка и обрабатывается.
     * При встрече с оператором {@code return} цикл завершает работу и возврашает результат оператора return.
     *
     * @param ctx обрабатываемый лист.
     * @return результат оператора return, если он был.
     * @throws MyLangException пробрасывается из внутреннего обработчика {@code handler}
     */
    @Override
    public Response handler(MyLangParser.BlockContext ctx) throws MyLangException {
        Response response = new EmptyResponse();
        if(ctx.blockStatements() != null){
            createNestedStorage();
            for (MyLangParser.BlockStatementsContext blockStatement : ctx.blockStatements()) {
                response = new BlockStatementsContext().handler(blockStatement);
                if(response instanceof ReturnResponse){
                    break;
                }
            }
            deleteNestedStorage();
        }

        return response;
    }
}

class BlockStatementsContext implements ContextHandler<MyLangParser.BlockStatementsContext> {

    @Override
    public Response handler(MyLangParser.BlockStatementsContext ctx) throws MyLangException {
        return defaultHandler(ctx);
    }

}

