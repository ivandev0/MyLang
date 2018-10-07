import response.MyLangException;
import response.Response;

import java.util.LinkedList;
import java.util.stream.Collectors;

class MyLangInterpreter {

    static LinkedList<Function> functions;

    void visitCompilationUnit(MyLangParser.CompilationUnitContext ctx) throws MyLangException {
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

    class FunctionVisitor extends MyLangBaseVisitor<Function>{
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

    private class FunArgsVisitor extends MyLangBaseVisitor<FunArgs>{
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

