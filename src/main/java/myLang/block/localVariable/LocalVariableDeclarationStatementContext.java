package myLang.block.localVariable;

import myLang.ContextHandler;
import myLang.block.BlockContext;
import myLang.expression.AdditiveExpressionContext;
import myLang.response.EmptyResponse;
import myLang.response.MyLangException;
import myLang.response.Response;
import myLangParser.MyLangParser;
import org.antlr.v4.runtime.tree.TerminalNodeImpl;

public class LocalVariableDeclarationStatementContext implements ContextHandler<MyLangParser.LocalVariableDeclarationStatementContext> {

    @Override
    public Response handler(MyLangParser.LocalVariableDeclarationStatementContext ctx) throws MyLangException {
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
                //count additive myLang.expression
                Response<Integer> response = new AdditiveExpressionContext().handler(vdCtx.additiveExpression());
                BlockContext.add(vdCtx.getChild(0).getText(), response.getResponse());
            }
        }

        return new EmptyResponse();
    }
}
