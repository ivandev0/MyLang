import response.Response;

import java.util.LinkedList;
import java.util.stream.Collectors;

public class MyLangInterpreter extends MyLangBaseVisitor<Void> {

    static LinkedList<Function> functions;

    @Override
    public Void visitCompilationUnit(MyLangParser.CompilationUnitContext ctx) {
        FunctionVisitor functionVisitor = new FunctionVisitor();
        functions = ctx.funDeclaration()
                .stream()
                .map(function -> function.accept(functionVisitor))
                .collect(Collectors.toCollection(LinkedList::new));

        if(functions.size() == 0){
            System.exit(0);
        }

        if (!functions.getFirst().name.equals("main") || functions.getFirst().args.size() != 0 || !functions.getFirst().resultType.equals("int")){
            System.err.println("Первая функция должна быть main без аргументов, возвращающая int");
            System.exit(1);
        }

        /*for(Function fun: functions){
            System.out.println(fun);
        }*/

        new FunBodyVisitor().visitBlock(functions.getFirst().ctx.block());

        return null;
    }

    class FunctionVisitor extends MyLangBaseVisitor<Function>{
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
                return null;
            }
            return new FunArgs(ctx.getChild(0).getChild(0).getText(), ctx.getChild(0).getChild(1).getText());
        }
    }

    private class FunBodyVisitor extends MyLangBaseVisitor<Void>{

        @Override
        public Void visitBlock(MyLangParser.BlockContext ctx) {
            Response response = new BlockFactory().handler(ctx);
            if(response == null || response.getResponse().equals("")){
                System.err.println("Функция main должна возвращать тип int");
                System.exit(1);
            }
            return null;
        }

    }
}

class Function {
    final String name, resultType;
    final LinkedList<FunArgs> args;
    final MyLangParser.FunDeclarationContext ctx;

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

class Variable<T> {
    private final String name;
    private T value;

    Variable(String name, T value) {
        this.name = name;
        this.value = value;
    }

    String getName() {
        return name;
    }

    T getValue() {
        return value;
    }

    public void validate(){

    }

    void updateValue(T newValue){
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
                ", value='" + value + '\'' +
                '}';
    }

}

