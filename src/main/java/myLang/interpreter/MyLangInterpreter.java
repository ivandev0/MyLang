package myLang.interpreter;

import myLang.block.BlockContext;
import myLang.response.MyLangException;
import myLang.response.Response;
import myLangParser.MyLangBaseVisitor;
import myLangParser.MyLangParser;

import java.util.LinkedList;
import java.util.stream.Collectors;

public class MyLangInterpreter {

    public static LinkedList<Function> functions;

    public void visitCompilationUnit(MyLangParser.CompilationUnitContext ctx) throws MyLangException {
        FunctionVisitor functionVisitor = new FunctionVisitor();
        functions = ctx.funDeclaration()
                .stream()
                .map(function -> function.accept(functionVisitor))
                .collect(Collectors.toCollection(LinkedList::new));

        if(functions.size() == 0){
            System.exit(0);
        }

        if (!functions.getFirst().name.equals("main") || functions.getFirst().args.size() != 0 || !functions.getFirst().resultType.equals("int")){
            throw new MyLangException("Первая функция должна быть main без аргументов, возвращающая int");
        }

        new FunBodyVisitor().visitBlock(functions.getFirst().ctx.block());
    }

    class FunctionVisitor extends MyLangBaseVisitor<Function> {
        @Override
        public Function visitFunDeclaration(MyLangParser.FunDeclarationContext ctx) {
            String resultType = ctx.getChild(0).getText();
            String name = ctx.getChild(1).getText();

            FunArgsVisitor funArgsVisitor = new FunArgsVisitor();
            LinkedList<FunArgs> funArgs = new LinkedList<>();
            if(ctx.funCallArgs() != null) {
                funArgs = ctx.funCallArgs().funArgs()
                        .stream()
                        .map(args -> args.accept(funArgsVisitor))
                        .collect(Collectors.toCollection(LinkedList::new));
            }

            return new Function(name, resultType, funArgs, ctx);
        }
    }

    private class FunArgsVisitor extends MyLangBaseVisitor<FunArgs> {
        @Override
        public FunArgs visitFunArgs(MyLangParser.FunArgsContext ctx) {
            /*if(ctx.getChildCount() == 0){
                return null;
            }*/
            return new FunArgs(ctx.types().getText(), ctx.ID().getText());
        }


    }

    private class FunBodyVisitor{

        void visitBlock(MyLangParser.BlockContext ctx) throws MyLangException{
            Response response = new BlockContext().handler(ctx);
            if(response.getResponse() == null || response.getResponse().equals("")){
                throw new MyLangException("Функция main должна возвращать тип int");
            }
        }

    }
}

