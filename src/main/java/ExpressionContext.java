import org.antlr.v4.runtime.tree.TerminalNodeImpl;
import response.*;

public class ExpressionContext implements ContextHandler<MyLangParser.ExpressionContext> {
    @Override
    public Response handler(MyLangParser.ExpressionContext ctx){
        return defaultHandler(ctx);
    }
}

class ConditionalOrExpressionContext implements ContextHandler<MyLangParser.ConditionalOrExpressionContext> {

    @Override
    public Response handler(MyLangParser.ConditionalOrExpressionContext ctx) {
        //System.out.println("or");

        if(ctx.getChildCount() == 1){
            return defaultHandler(ctx);
        }
        BooleanResponse firstResponse = (BooleanResponse) defaultHandler(ctx, 0);
        BooleanResponse secondResponse = (BooleanResponse) defaultHandler(ctx, 2);
        return new BooleanResponse(firstResponse.getResponse() || secondResponse.getResponse());
    }
}

class ConditionalAndExpressionContext implements ContextHandler<MyLangParser.ConditionalAndExpressionContext> {

    @Override
    public Response handler(MyLangParser.ConditionalAndExpressionContext ctx) {
        //System.out.println("and");

        if(ctx.getChildCount() == 1){
            return defaultHandler(ctx);
        }
        BooleanResponse firstResponse = (BooleanResponse) defaultHandler(ctx, 0);
        BooleanResponse secondResponse = (BooleanResponse) defaultHandler(ctx, 2);
        return new BooleanResponse(firstResponse.getResponse() && secondResponse.getResponse());

    }
}

class EqualityExpressionContext implements ContextHandler<MyLangParser.EqualityExpressionContext> {

    @Override
    public Response handler(MyLangParser.EqualityExpressionContext ctx) {
        //System.out.println("eq");

        if(ctx.getChildCount() == 1){
            return defaultHandler(ctx);
        }
        BooleanResponse firstResponse = (BooleanResponse) defaultHandler(ctx, 0);
        BooleanResponse secondResponse = (BooleanResponse) defaultHandler(ctx, 2);
        switch (((TerminalNodeImpl) ctx.getChild(1)).getSymbol().getType()) {
            case MyLangLexer.EQUAL:
                return new BooleanResponse(firstResponse.getResponse() == secondResponse.getResponse());
            case MyLangLexer.NOTEQUAL:
                return new BooleanResponse(firstResponse.getResponse() != secondResponse.getResponse());
        }

        //TODO exception | response error
        return null;
    }
}

class RelationalExpressionContext implements ContextHandler<MyLangParser.RelationalExpressionContext> {

    @Override
    public Response handler(MyLangParser.RelationalExpressionContext ctx) {
        //System.out.println("rel");

        if(ctx.getChildCount() == 1){
            return defaultHandler(ctx);
        }
        IntegerResponse firstResponse = (IntegerResponse) defaultHandler(ctx, 0);
        IntegerResponse secondResponse = (IntegerResponse) defaultHandler(ctx, 2);
        switch (((TerminalNodeImpl) ctx.getChild(1)).getSymbol().getType()) {
            case MyLangLexer.LT:
                return new BooleanResponse(firstResponse.getResponse() < secondResponse.getResponse());
            case MyLangLexer.GT:
                return new BooleanResponse(firstResponse.getResponse() > secondResponse.getResponse());
            case MyLangLexer.LE:
                return new BooleanResponse(firstResponse.getResponse() <= secondResponse.getResponse());
            case MyLangLexer.GE:
                return new BooleanResponse(firstResponse.getResponse() >= secondResponse.getResponse());
        }

        //TODO exception | response error
        return null;
    }
}

class AdditiveExpressionContext implements ContextHandler<MyLangParser.AdditiveExpressionContext> {

    @Override
    public IntegerResponse handler(MyLangParser.AdditiveExpressionContext ctx) {
        if(ctx.NUMBER() != null){
            return new IntegerResponse(Integer.valueOf(ctx.NUMBER().getText()));
        }

        if(ctx.ID() != null){
            int result = (Integer) BlockFactory.get(ctx.ID().getText()).getValue();
            return new IntegerResponse(result);
        }

        if(ctx.funInvocation() != null){
            //TODO fun invocation
            String name = ctx.funInvocation().ID().getText();
            Function function = MyLangInterpreter.functions
                    .stream()
                    .filter(fun -> fun.name.equals(name))
                    .findFirst()
                    .orElse(null);
            if(function == null){
                System.err.println("Функция " + name + " не объявлена");
                System.exit(1);
            }
            if(function.resultType.equals("void")){
                System.err.println("Функция " + name + " не возвращает результата");
                System.exit(1);
            }
        }

        if (ctx.LPAREN() != null && ctx.RPAREN() != null) {
            return handler(ctx.additiveExpression(0));
        } else {
            IntegerResponse firstResponse = handler(ctx.additiveExpression(0));
            IntegerResponse secondResponse = handler(ctx.additiveExpression(1));
            switch (((TerminalNodeImpl) ctx.getChild(1)).getSymbol().getType()) {
                case MyLangLexer.ADD:
                    return new IntegerResponse(firstResponse.getResponse() + secondResponse.getResponse());
                case MyLangLexer.SUB:
                    return new IntegerResponse(firstResponse.getResponse() - secondResponse.getResponse());
                case MyLangLexer.MUL:
                    return new IntegerResponse(firstResponse.getResponse() * secondResponse.getResponse());
                case MyLangLexer.DIV:
                    return new IntegerResponse(firstResponse.getResponse() / secondResponse.getResponse());
            }
        }

        //TODO exception | response error
        return null;
    }
}