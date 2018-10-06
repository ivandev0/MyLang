import generated.*;

import java.util.LinkedList;
import java.util.stream.Collectors;

public class MyLangInterpreter extends MyLangBaseVisitor<Void> {

    @Override
    public Void visitCompilationUnit(MyLangParser.CompilationUnitContext ctx) {
        FunctionVisitor functionVisitor = new FunctionVisitor();
        LinkedList<Function> functions = ctx.funDeclaration()
                .stream()
                .map(function -> function.accept(functionVisitor))
                .collect(Collectors.toCollection(LinkedList::new));

        if (!functions.getFirst().name.equals("main") || functions.getFirst().args.size() != 0 || !functions.getFirst().resultType.equals("int")){
            System.err.println("Первая функция должна быть main без аргументов, возвращающая int");
            System.exit(1);
        }

        /*for(Function fun: functions){
            System.out.println(fun);
        }*/

        FunBodyVisitor funBodyVisitor = new FunBodyVisitor();
        for (MyLangParser.BlockStatementsContext blockStatement : functions.getFirst().ctx.block().blockStatements()) {
            funBodyVisitor.visitBlockStatements(blockStatement);
        }

        return null;
    }

    private class FunctionVisitor extends MyLangBaseVisitor<Function>{
        @Override
        public Function visitFunDeclaration(MyLangParser.FunDeclarationContext ctx) {
            String resultType = ctx.getChild(0).getText();
            String name = ctx.getChild(1).getText();

            FunArgsVisitor funArgsVisitor = new FunArgsVisitor();
            LinkedList<FunArgs> funArgs = ctx.funCallArgs()
                    .stream()
                    .map(args -> args.accept(funArgsVisitor))
                    .collect(Collectors.toCollection(LinkedList::new));

            return new Function(name, resultType, funArgs, ctx);
        }
    }

    private class FunArgsVisitor extends MyLangBaseVisitor<FunArgs>{
        @Override
        public FunArgs visitFunCallArgs(MyLangParser.FunCallArgsContext ctx) {
            if(ctx.getChildCount() == 0){
                return new FunArgs("", "");
            }
            return new FunArgs(ctx.getChild(0).getChild(0).getText(), ctx.getChild(0).getChild(1).getText());
        }
    }

    private class FunBodyVisitor extends MyLangBaseVisitor<Void>{
        @Override
        public Void visitBlockStatements(generated.MyLangParser.BlockStatementsContext ctx) {
            new BlockStatementsFactory().handler(ctx);
            return null;
        }
    }
}

class Function {
    final String name, resultType;
    final LinkedList<FunArgs> args;
    final MyLangParser.FunDeclarationContext ctx;

    LinkedList<Variable> localStorage;

    Function(String name, String resultType, LinkedList<FunArgs> args, MyLangParser.FunDeclarationContext ctx) {
        this.name = name;
        this.resultType = resultType;
        this.args = args;
        this.ctx = ctx;
    }

    @Override
    public String toString() {
        return "Function{" +
                "name='" + name + '\'' +
                ", resultType='" + resultType + '\'' +
                ", args=" + args +
                '}';
    }
}

class FunArgs{
    final String type, name;

    FunArgs(String type, String name) {
        this.type = type;
        this.name = name;
    }

    @Override
    public String toString() {
        return "FunArgs{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}

class Variable {
    private final String name, type;
    int value;

    public Variable(String name, String type, int value) {
        this.name = name;
        this.type = type;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void validate(){

    }

    public void updateValue(int newValue){
        value = newValue;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Variable){
            Variable variable = (Variable) obj;
            return name.equals(variable.name);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return "Variable{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}

