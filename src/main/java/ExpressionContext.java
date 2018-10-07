import org.antlr.v4.runtime.tree.TerminalNodeImpl;
import response.*;

public class ExpressionContext implements ContextHandler<MyLangParser.ExpressionContext> {
    @Override
    public Response handler(MyLangParser.ExpressionContext ctx) throws MyLangException {
        return defaultHandler(ctx);
    }
}

class ConditionalOrExpressionContext implements ContextHandler<MyLangParser.ConditionalOrExpressionContext> {

    @Override
    public Response handler(MyLangParser.ConditionalOrExpressionContext ctx) throws MyLangException {
        if(ctx.getChildCount() == 1){
            return defaultHandler(ctx);
        }
        Response firstResponse = defaultHandler(ctx, 0);
        Response secondResponse = defaultHandler(ctx, 2);
        if(firstResponse instanceof IntegerResponse || secondResponse instanceof IntegerResponse){
            throw new MyLangException("Оператор || не применим к типу int");
        }
        return new BooleanResponse((boolean)firstResponse.getResponse() || (boolean)secondResponse.getResponse());
    }
}

class ConditionalAndExpressionContext implements ContextHandler<MyLangParser.ConditionalAndExpressionContext> {

    @Override
    public Response handler(MyLangParser.ConditionalAndExpressionContext ctx) throws MyLangException {
        if(ctx.getChildCount() == 1){
            return defaultHandler(ctx);
        }
        Response firstResponse = defaultHandler(ctx, 0);
        Response secondResponse = defaultHandler(ctx, 2);
        if(firstResponse instanceof IntegerResponse || secondResponse instanceof IntegerResponse){
            throw new MyLangException("Оператор && не применим к типу int");
        }

        return new BooleanResponse((boolean) firstResponse.getResponse() && (boolean)secondResponse.getResponse());
    }
}

class EqualityExpressionContext implements ContextHandler<MyLangParser.EqualityExpressionContext> {

    @Override
    public Response handler(MyLangParser.EqualityExpressionContext ctx) throws MyLangException{
        if(ctx.getChildCount() == 1){
            return defaultHandler(ctx);
        }
        Response firstResponse = defaultHandler(ctx, 0);
        Response secondResponse = defaultHandler(ctx, 2);
        switch (((TerminalNodeImpl) ctx.getChild(1)).getSymbol().getType()) {
            case MyLangLexer.EQUAL:
                return new BooleanResponse(firstResponse.getResponse().equals(secondResponse.getResponse()));
            case MyLangLexer.NOTEQUAL:
                return new BooleanResponse(!firstResponse.getResponse().equals(secondResponse.getResponse()));
        }

        throw new MyLangException("Неподдерживаемая операция сравнения");
    }
}

class RelationalExpressionContext implements ContextHandler<MyLangParser.RelationalExpressionContext> {

    @Override
    public Response handler(MyLangParser.RelationalExpressionContext ctx) throws MyLangException{
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

        throw new MyLangException("Неподдерживаемая операция сравнения");
    }
}

class AdditiveExpressionContext implements ContextHandler<MyLangParser.AdditiveExpressionContext> {

    @Override
    public IntegerResponse handler(MyLangParser.AdditiveExpressionContext ctx) throws MyLangException{
        if(ctx.NUMBER() != null){
            return new IntegerResponse(Integer.valueOf(ctx.NUMBER().getText()));
        }

        if(ctx.ID() != null){
            int result = (Integer) BlockContext.get(ctx.ID().getText()).getValue();
            return new IntegerResponse(result);
        }

        if(ctx.funInvocation() != null){
            String name = ctx.funInvocation().ID().getText();
            Function function = MyLangInterpreter.functions
                    .stream()
                    .filter(fun -> fun.name.equals(name))
                    .findFirst()
                    .orElse(null);
            if(function == null){
                throw new MyLangException("Функция " + name + " не объявлена");
            }
            if(function.resultType.equals("void")){
                throw new MyLangException("Функция " + name + " не возвращает результата");
            }
            return new IntegerResponse(Integer.valueOf((String) new FunInvocationContext().handler(ctx.funInvocation()).getResponse()));
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

        throw new MyLangException("Неподдерживаемая арифметическая операция");
    }
}