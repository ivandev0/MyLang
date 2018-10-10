package myLang.expression;

import myLang.ContextHandler;
import myLang.block.BlockContext;
import myLang.interpreter.Function;
import myLang.interpreter.MyLangInterpreter;
import myLang.response.IntegerResponse;
import myLang.response.MyLangException;
import myLang.block.statement.FunInvocationContext;
import myLangParser.MyLangLexer;
import myLangParser.MyLangParser;
import org.antlr.v4.runtime.tree.TerminalNodeImpl;

public class AdditiveExpressionContext implements ContextHandler<MyLangParser.AdditiveExpressionContext> {

    @Override
    public IntegerResponse handler(MyLangParser.AdditiveExpressionContext ctx) throws MyLangException {
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
                    .filter(fun -> fun.getName().equals(name))
                    .findFirst()
                    .orElse(null);
            if(function == null){
                throw new MyLangException("Функция " + name + " не объявлена");
            }
            if(function.getResultType().equals("void")){
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
                    if(secondResponse.getResponse() == 0){
                        throw new MyLangException("Деление на 0");
                    }
                    return new IntegerResponse(firstResponse.getResponse() / secondResponse.getResponse());
            }
        }

        throw new MyLangException("Неподдерживаемая арифметическая операция");
    }
}
