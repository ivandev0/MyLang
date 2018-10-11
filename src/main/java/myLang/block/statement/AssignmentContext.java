package myLang.block.statement;

import myLang.block.BlockContext;
import myLang.ContextHandler;
import myLang.response.*;
import myLangParser.MyLangLexer;
import myLangParser.MyLangParser;

public class AssignmentContext implements ContextHandler<MyLangParser.AssignmentContext> {
    @Override
    public Response handler(MyLangParser.AssignmentContext ctx) throws MyLangException {
        String name = ctx.ID().getText();
        Integer num = (Integer) BlockContext.get(name).getValue();
        IntegerResponse response = (IntegerResponse) defaultHandler(ctx, 2);
        switch (ctx.assignmentOperator().start.getType()) {
            case MyLangLexer.ASSIGN:
                return new IntegerResponse(BlockContext.update(name, response.getResponse()));
            case MyLangLexer.ADD_ASSIGN:
                return new IntegerResponse(BlockContext.update(name, num + response.getResponse()));
            case MyLangLexer.SUB_ASSIGN:
                return new IntegerResponse(BlockContext.update(name,num - response.getResponse()));
            case MyLangLexer.MUL_ASSIGN:
                return new IntegerResponse(BlockContext.update(name,num * response.getResponse()));
            case MyLangLexer.DIV_ASSIGN:
                if(response.getResponse() == 0){
                    throw new MyLangException("Деление на 0");
                }
                return new IntegerResponse(BlockContext.update(name,num / response.getResponse()));
            default:
                throw new MyLangException("Операция " + ctx.assignmentOperator().start.getText() + " не поддерживается");
        }
    }
}
