package myLang.block;

import myLang.ContextHandler;
import myLang.expression.ExpressionContext;
import myLang.interpreter.FunArgs;
import myLang.interpreter.Variable;
import myLang.response.*;
import myLangParser.MyLangParser;

import java.util.*;

public class BlockContext implements ContextHandler<MyLangParser.BlockContext> {

    static private LinkedList<Set<Variable>> localStorage = new LinkedList<>();
    static private Deque<LinkedList<Set<Variable>>> stack = new LinkedList<>();

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

    public static <T> void add(String name, T value) throws MyLangException{
        if(!localStorage.getLast().add(new Variable<>(name, value))){
            throw new MyLangException("Переменная " + name + " уже существует");
        }
    }

    public static <T> void update(String name, T newValue) throws MyLangException{
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
    }

    private static void createNestedStorage(){
        localStorage.addLast(new HashSet<>());
    }

    private static void deleteNestedStorage() {
        localStorage.removeLast();
    }

    public static void pushStack(){
        stack.addFirst(new LinkedList<Set<Variable>>(localStorage));
        localStorage.clear();
        createNestedStorage();
    }

    public static void popStack(){
        deleteNestedStorage();
        localStorage = stack.poll();
    }

    public static void associateNamesWithValues(LinkedList<FunArgs> names, List<MyLangParser.ExpressionContext> values) throws MyLangException{
        for (int i = 0; i < values.size(); i++){
            add(names.get(i).getName(), new ExpressionContext().handler(values.get(i)).getResponse());
        }
    }

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

