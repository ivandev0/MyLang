import org.antlr.v4.runtime.tree.TerminalNodeImpl;
import response.*;

import java.util.*;
import java.util.stream.Collectors;

class BlockContext implements ContextHandler<MyLangParser.BlockContext>{

    static private LinkedList<Set<Variable>> localStorage = new LinkedList<>();
    static private Deque<LinkedList<Set<Variable>>> stack = new LinkedList<>();

    static Variable get(String name) throws MyLangException{
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

    static <T> void add(String name, T value) throws MyLangException{
        if(!localStorage.getLast().add(new Variable<>(name, value))){
            throw new MyLangException("Переменная " + name + " уже существует");
        }
    }

    static <T> void update(String name, T newValue) throws MyLangException{
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

    static void pushStack(){
        stack.addFirst(new LinkedList<Set<Variable>>(localStorage));
        localStorage.clear();
        createNestedStorage();
    }

    static void popStack(){
        deleteNestedStorage();
        localStorage = stack.poll();
    }

    static void associateNamesWithValues(LinkedList<FunArgs> names, List<MyLangParser.ExpressionContext> values) throws MyLangException{
        for (int i = 0; i < values.size(); i++){
            add(names.get(i).name, new ExpressionContext().handler(values.get(i)).getResponse());
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

class LocalVariableDeclarationStatementContext implements ContextHandler<MyLangParser.LocalVariableDeclarationStatementContext> {

    @Override
    public Response handler(MyLangParser.LocalVariableDeclarationStatementContext ctx) throws MyLangException{
        String type = ctx.types().getText();
        if(!type.equals("int")){
            throw new MyLangException("Нереализованный тип данных " + type);
        }
        for(int i = 0; i < ctx.variableDeclaratorList().getChildCount(); i++){
            if(ctx.variableDeclaratorList().getChild(i) instanceof TerminalNodeImpl){
                continue;
            }

            MyLangParser.VariableDeclaratorContext vdCtx = (MyLangParser.VariableDeclaratorContext) ctx.variableDeclaratorList().getChild(i);
            if(vdCtx.getChildCount() == 1){
                BlockContext.add(vdCtx.getText(), 0);
            } else {
                //count additive expression
                Response<Integer> response = new AdditiveExpressionContext().handler(vdCtx.additiveExpression());
                BlockContext.add(vdCtx.getChild(0).getText(), response.getResponse());
            }
        }

        return new EmptyResponse();
    }
}

class PrintContext implements ContextHandler<MyLangParser.PrintContext> {

    @Override
    public Response handler(MyLangParser.PrintContext ctx) throws MyLangException {
        return defaultHandler(ctx, 1);
    }
}

class PrintVariationsContext implements ContextHandler<MyLangParser.PrintVariationsContext> {
    @Override
    public Response handler(MyLangParser.PrintVariationsContext ctx) throws MyLangException{
        if(ctx.getChildCount() >= 3 && ctx.start.getType() == MyLangLexer.STRING && ctx.COLON() != null){
            Response response = new ExpressionContext().handler(ctx.expression());
            System.out.print(ctx.start.getText().replace("\"", "") + " " + response.getResponse());
        } else if ( ctx.start.getType() == MyLangLexer.STRING){
            System.out.print(ctx.start.getText().replace("\"", ""));
        } else if (ctx.expression() != null){
            Response response = new ExpressionContext().handler(ctx.expression());
            System.out.print(response.getResponse());
        } else {
            throw new MyLangException(ctx.getText() + "\nНереализованная форма оператора print");
        }

        if(ctx.printVariations().size() != 0){
            System.out.print(" ");
        } else {
            System.out.println("");
        }

        for(MyLangParser.PrintVariationsContext context: ctx.printVariations()){
            handler(context);
        }

        return new EmptyResponse();
    }
}

class StatementContext implements ContextHandler<MyLangParser.StatementContext> {

    @Override
    public Response handler(MyLangParser.StatementContext ctx) throws MyLangException {
        /*if(ctx.block() != null){
            return new BlockContext().handler(ctx.block());
        }*/
        return defaultHandler(ctx);
    }
}

class AssignmentContext implements ContextHandler<MyLangParser.AssignmentContext>{
    @Override
    public Response handler(MyLangParser.AssignmentContext ctx) throws MyLangException{
        String name = ctx.ID().getText();
        Integer num = (Integer) BlockContext.get(name).getValue();
        IntegerResponse response = new AdditiveExpressionContext().handler(ctx.additiveExpression());
        switch (ctx.assignmentOperator().start.getType()) {
            case MyLangLexer.ASSIGN:
                BlockContext.update(name, response.getResponse());
                break;
            case MyLangLexer.ADD_ASSIGN:
                BlockContext.update(name, response.getResponse() + num);
                break;
            case MyLangLexer.SUB_ASSIGN:
                BlockContext.update(name,response.getResponse() - num);
                break;
            case MyLangLexer.MUL_ASSIGN:
                BlockContext.update(name,response.getResponse() * num);
                break;
            case MyLangLexer.DIV_ASSIGN:
                if(num == 0){
                    throw new MyLangException("Деление на 0");
                }
                BlockContext.update(name,response.getResponse() / num);
                break;
            default:
                throw new MyLangException("Операция " + ctx.assignmentOperator().start.getText() + " не поддерживается");
        }
        return new EmptyResponse();
    }
}

class FunInvocationContext implements ContextHandler<MyLangParser.FunInvocationContext>{
    @Override
    public Response handler(MyLangParser.FunInvocationContext ctx) throws MyLangException{
        String name = ctx.ID().getText();
        Function function = MyLangInterpreter.functions
                .stream()
                .filter(fun -> fun.name.equals(name))
                .findFirst()
                .orElse(null);
        if(function == null){
            throw new MyLangException("Функция " + name + " не объявлена");
        }

        if (function.name.equals("main")){
            throw new MyLangException("Нельзя вызывать рекурсивно функцию main");
        }

        List<MyLangParser.ExpressionContext> funArgs = ctx.argumentList() == null ? new LinkedList<>() : ctx.argumentList().expression();
        if(function.args.size() != funArgs.size()){
            throw new MyLangException("Неверное количество параметров для функции " + name
                    + "\nОжидалось " + function.args.size() + " аргументов");
        }

        BlockContext.pushStack();
        BlockContext.associateNamesWithValues(function.args, funArgs);
        MyLangParser.FunDeclarationContext fdc = function.ctx;
        Response response = new BlockContext().handler(fdc.block());
        if(response.getResponse() == null && !function.resultType.equals("void")){
            throw new MyLangException("Функция " + name + " должна возвращать значение типа " + function.resultType);
        }
        if(response.getResponse() != null && ((response.getResponse().equals("") && !function.resultType.equals("void")) ||
                                (!response.getResponse().equals("") && function.resultType.equals("void")))){
            throw new MyLangException("Функция " + name + " должна возвращать значение типа " + function.resultType);
        }
        BlockContext.popStack();

        if (!(response instanceof EmptyResponse) && response.getResponse().equals("")){
            return new EmptyResponse();
        }
        return response;
    }
}

class ReturnStatementContext implements ContextHandler<MyLangParser.ReturnStatementContext>{
    @Override
    public Response handler(MyLangParser.ReturnStatementContext ctx) throws MyLangException {
        if(ctx.expression() != null){
            Response response = new ExpressionContext().handler(ctx.expression());
            return new ReturnResponse(String.valueOf(response.getResponse()));
        }
        return new ReturnResponse("");
    }
}