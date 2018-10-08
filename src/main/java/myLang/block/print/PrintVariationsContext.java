package myLang.block.print;

import myLang.ContextHandler;
import myLang.expression.ExpressionContext;
import myLang.response.EmptyResponse;
import myLang.response.MyLangException;
import myLang.response.Response;
import myLangParser.MyLangLexer;
import myLangParser.MyLangParser;

public class PrintVariationsContext implements ContextHandler<MyLangParser.PrintVariationsContext> {
    @Override
    public Response handler(MyLangParser.PrintVariationsContext ctx) throws MyLangException {
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
