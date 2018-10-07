import org.antlr.v4.runtime.tree.TerminalNodeImpl;
import response.IntegerResponse;
import response.Response;
import response.ReturnResponse;

import java.beans.Expression;
import java.util.*;
import java.util.stream.Collectors;

class BlockFactory implements ContextHandler<MyLangParser.BlockContext>{

    static private LinkedList<Set<Variable>> localStorage = new LinkedList<>();
    static private Queue<LinkedList<Set<Variable>>> stack = new LinkedList<>();

    private static boolean contains(String name){
        return localStorage
                .stream()
                .flatMap(Collection::stream)
                .anyMatch(var -> var.getName().equals(name));

    }

    static Variable get(String name){
        Variable var = localStorage
                .stream()
                .flatMap(Collection::stream)
                .filter(obj -> obj.getName().equals(name))
                .findFirst()
                .orElse(null);
        if(var == null){
            System.err.println("Нет такой переменной " + name);
            System.exit(1);
        }
        return var;
    }

    static <T> void add(String name, T value){
        if(!localStorage.getLast().add(new Variable<>(name, value))){
            System.err.println("Такая переменная уже существует " + name);
            System.exit(1);
        }
    }

    static <T> void update(String name, T newValue){
        Variable var = localStorage
                .stream()
                .flatMap(Collection::stream)
                .filter(obj -> obj.getName().equals(name))
                .findFirst()
                .orElse(null);

        if(var == null){
            System.err.println("Нет такой переменной " + name);
            System.exit(1);
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
        stack.add(new LinkedList<Set<Variable>>(localStorage));
        localStorage.clear();
        createNestedStorage();
    }

    static void popStack(){
        deleteNestedStorage();
        localStorage = stack.poll();
    }

    static void associateNamesWithValues(LinkedList<FunArgs> names, List<MyLangParser.ExpressionContext> values){
        for (int i = 0; i < values.size(); i++){
            add(names.get(i).name, (Integer) new ExpressionContext().handler(values.get(i)).getResponse());
        }
    }

    @Override
    public Response handler(MyLangParser.BlockContext ctx) {
        Response response = null;
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
    public Response handler(MyLangParser.BlockStatementsContext ctx) {
        return defaultHandler(ctx);
    }

}

class LocalVariableDeclarationStatementContext implements ContextHandler<MyLangParser.LocalVariableDeclarationStatementContext> {

    @Override
    public Response handler(MyLangParser.LocalVariableDeclarationStatementContext ctx) {
        //System.out.println("i'm in 1");
        String type = ctx.types().getText();
        if(!type.equals("int")){
            System.err.println("Нереалиованный тип данных " + type);
            System.exit(1);
        }
        for(int i = 0; i < ctx.variableDeclaratorList().getChildCount(); i++){
            if(ctx.variableDeclaratorList().getChild(i) instanceof TerminalNodeImpl){
                continue;
            }

            MyLangParser.VariableDeclaratorContext vdCtx = (MyLangParser.VariableDeclaratorContext) ctx.variableDeclaratorList().getChild(i);
            if(vdCtx.getChildCount() == 1){
                BlockFactory.add(vdCtx.getText(), 0);
            } else {
                //count additive expression
                Response<Integer> response = new AdditiveExpressionContext().handler(vdCtx.additiveExpression());
                BlockFactory.add(vdCtx.getChild(0).getText(), response.getResponse());
            }
        }

        //TODO
        return null;

    }
}

class PrintContext implements ContextHandler<MyLangParser.PrintContext> {

    @Override
    public Response handler(MyLangParser.PrintContext ctx) {
        defaultHandler(ctx, 1);
        return null;
    }
}

class PrintVariationsContext implements ContextHandler<MyLangParser.PrintVariationsContext> {
    @Override
    public Response handler(MyLangParser.PrintVariationsContext ctx) {
        if(ctx.getChildCount() >= 3 && ctx.start.getType() == MyLangLexer.STRING && ctx.COLON() != null){
            //System.out.println("3");
            Response response = new ExpressionContext().handler(ctx.expression());
            System.out.println(ctx.start.getText() + " " + response.getResponse());
        } else if ( ctx.start.getType() == MyLangLexer.STRING){
            //System.out.println("1");
            System.out.println(ctx.start.getText());
        } else if (ctx.expression() != null){
            //System.out.println("2");
            Response response = new ExpressionContext().handler(ctx.expression());
            System.out.println(response.getResponse());
        } else {
            //TODO undefined
        }

        for(MyLangParser.PrintVariationsContext context: ctx.printVariations()){
            handler(context);
        }

        return null;
    }
}

class StatementContext implements ContextHandler<MyLangParser.StatementContext> {

    @Override
    public Response handler(MyLangParser.StatementContext ctx) {
        //System.out.println("i'm in 3");

        if(ctx.block() != null){
            new BlockFactory().handler(ctx.block());
        }

        return defaultHandler(ctx);

    }
}

class AssignmentContext implements ContextHandler<MyLangParser.AssignmentContext>{
    @Override
    public Response handler(MyLangParser.AssignmentContext ctx) {
        String name = ctx.ID().getText();
        Integer num = (Integer) BlockFactory.get(name).getValue();
        IntegerResponse response = new AdditiveExpressionContext().handler(ctx.additiveExpression());
        switch (ctx.assignmentOperator().start.getType()) {
            case MyLangLexer.ASSIGN:
                BlockFactory.update(name, response.getResponse());
                break;
            case MyLangLexer.ADD_ASSIGN:
                BlockFactory.update(name, response.getResponse() + num);
                break;
            case MyLangLexer.SUB_ASSIGN:
                BlockFactory.update(name,response.getResponse() - num);
                break;
            case MyLangLexer.MUL_ASSIGN:
                BlockFactory.update(name,response.getResponse() * num);
                break;
            case MyLangLexer.DIV_ASSIGN:
                if(num == 0){
                    System.err.println("Деление на 0");
                    System.exit(1);
                }
                BlockFactory.update(name,response.getResponse() / num);
                break;
        }

        return null;
    }
}

class FunInvocationContext implements ContextHandler<MyLangParser.FunInvocationContext>{
    @Override
    public Response handler(MyLangParser.FunInvocationContext ctx) {
        String name = ctx.ID().getText();
        Function function = MyLangInterpreter.functions
                .stream()
                .filter(fun -> fun.name.equals(name))
                .findFirst()
                .orElse(null);
        if(function == null){
            System.err.println("Функция " + name + " не объявлена");
            System.exit(1);
        }


        List<MyLangParser.ExpressionContext> funArgs = ctx.argumentList().expression();
        if(function.args.size() != funArgs.size()){
            //TODO ожидалось/получили
            System.err.println("Неверное количество параметров для функции " + name);
            System.exit(1);
        }

        BlockFactory.pushStack();
        BlockFactory.associateNamesWithValues(function.args, funArgs);
        MyLangParser.FunDeclarationContext fdc = function.ctx;
        Response response = new BlockFactory().handler(fdc.block());
        if(response == null && !function.resultType.equals("void")){
            System.err.println("Функция " + name + " должна возвращать значение типа " + function.resultType);
            System.exit(1);
        }
        if(response != null && ((response.getResponse().equals("") && !function.resultType.equals("void")) ||
                                (!response.getResponse().equals("") && function.resultType.equals("void")))){
            System.err.println("Функция " + name + " должна возвращать тип " + function.resultType);
            System.exit(1);
        }
        BlockFactory.popStack();


        return null;
    }
}

class ReturnStatementContext implements ContextHandler<MyLangParser.ReturnStatementContext>{
    @Override
    public Response handler(MyLangParser.ReturnStatementContext ctx) {
        if(ctx.expression() != null){
            Response response = new ExpressionContext().handler(ctx.expression());
            return new ReturnResponse(String.valueOf(response.getResponse()));
        }
        return new ReturnResponse("");
    }
}