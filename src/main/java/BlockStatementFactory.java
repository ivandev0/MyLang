import generated.*;
import org.antlr.v4.runtime.tree.TerminalNodeImpl;
import response.Response;

import java.util.LinkedList;

class BlockStatementsFactory implements ContextHandler<MyLangParser.BlockStatementsContext> {

    private static LinkedList<Variable> localStorage = new LinkedList<>();

    private static boolean contains(String name){
        return localStorage
                .stream()
                .anyMatch(obj -> obj.getName().equals(name));
    }

    static Variable get(String name) {
        if (!contains(name)){
            System.err.println("Нет такой переменной " + name);
            System.exit(1);
        }
        return localStorage
                .stream()
                .filter(obj -> obj.getName().equals(name))
                .findFirst()
                .get();
    }

    static void add(String name, String type, int value){
        if(contains(name)) {
            System.err.println("Такая переменная уже существует " + name);
            System.exit(1);
        }
        localStorage.add(new Variable(name, type, value));
    }

    static void update(String name, String type, String value){

    }

    @Override
    public Response handler(MyLangParser.BlockStatementsContext ctx) {
        defaultHandler(ctx);
        return null;
    }

}

class LocalVariableDeclarationStatementContext implements ContextHandler<MyLangParser.LocalVariableDeclarationStatementContext> {

    @Override
    public Response handler(MyLangParser.LocalVariableDeclarationStatementContext ctx) {
        //System.out.println("i'm in 1");
        String type = ctx.types().getText();
        for(int i = 0; i < ctx.variableDeclaratorList().getChildCount(); i++){
            if(ctx.variableDeclaratorList().getChild(i) instanceof TerminalNodeImpl){
                continue;
            }

            MyLangParser.VariableDeclaratorContext vdCtx = (MyLangParser.VariableDeclaratorContext) ctx.variableDeclaratorList().getChild(i);
            if(vdCtx.getChildCount() == 1){
                BlockStatementsFactory.add(vdCtx.getText(), type, 0);
            } else {
                //count additive expression
                Response<Integer> response = new AdditiveExpressionContext().handler(vdCtx.additiveExpression());
                BlockStatementsFactory.add(vdCtx.getChild(0).getText(), type, response.getResponse());
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



        return null;
    }
}