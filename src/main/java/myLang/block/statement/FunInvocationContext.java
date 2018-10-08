package myLang.block.statement;

import myLang.block.BlockContext;
import myLang.ContextHandler;
import myLang.interpreter.Function;
import myLang.interpreter.MyLangInterpreter;
import myLang.response.EmptyResponse;
import myLang.response.MyLangException;
import myLang.response.Response;
import myLangParser.MyLangParser;

import java.util.LinkedList;
import java.util.List;

public class FunInvocationContext implements ContextHandler<MyLangParser.FunInvocationContext> {
    @Override
    public Response handler(MyLangParser.FunInvocationContext ctx) throws MyLangException {
        String name = ctx.ID().getText();
        Function function = MyLangInterpreter.functions
                .stream()
                .filter(fun -> fun.getName().equals(name))
                .findFirst()
                .orElse(null);
        if(function == null){
            throw new MyLangException("Функция " + name + " не объявлена");
        }

        if (function.getName().equals("main")){
            throw new MyLangException("Нельзя вызывать рекурсивно функцию main");
        }

        List<MyLangParser.ExpressionContext> funArgs = ctx.argumentList() == null ? new LinkedList<>() : ctx.argumentList().expression();
        if(function.getArgs().size() != funArgs.size()){
            throw new MyLangException("Неверное количество параметров для функции " + name
                    + "\nОжидалось " + function.getArgs().size() + " аргументов");
        }

        BlockContext.pushStack();
        BlockContext.associateNamesWithValues(function.getArgs(), funArgs);
        MyLangParser.FunDeclarationContext fdc = function.getCtx();
        Response response = new BlockContext().handler(fdc.block());
        if(response.getResponse() == null && !function.getResultType().equals("void")){
            throw new MyLangException("Функция " + name + " должна возвращать значение типа " + function.getResultType());
        }
        if(response.getResponse() != null && ((response.getResponse().equals("") && !function.getResultType().equals("void")) ||
                (!response.getResponse().equals("") && function.getResultType().equals("void")))){
            throw new MyLangException("Функция " + name + " должна возвращать значение типа " + function.getResultType());
        }
        BlockContext.popStack();

        if (!(response instanceof EmptyResponse) && response.getResponse().equals("")){
            return new EmptyResponse();
        }
        return response;
    }
}
