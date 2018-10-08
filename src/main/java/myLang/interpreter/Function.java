package myLang.interpreter;

import myLangParser.MyLangParser;

import java.util.LinkedList;

public class Function {
    final String name, resultType;
    final LinkedList<FunArgs> args;
    final MyLangParser.FunDeclarationContext ctx;

    Function(String name, String resultType, LinkedList<FunArgs> args, MyLangParser.FunDeclarationContext ctx) {
        this.name = name;
        this.resultType = resultType;
        this.args = args;
        this.ctx = ctx;
    }

    public String getName() {
        return name;
    }

    public String getResultType() {
        return resultType;
    }

    public LinkedList<FunArgs> getArgs() {
        return args;
    }

    public MyLangParser.FunDeclarationContext getCtx() {
        return ctx;
    }

    @Override
    public String toString() {
        return "myLang.interpreter.Function{" +
                "name='" + name + '\'' +
                ", resultType='" + resultType + '\'' +
                ", args=" + args +
                '}';
    }
}
