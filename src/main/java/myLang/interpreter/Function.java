package myLang.interpreter;

import myLangParser.MyLangParser;

import java.util.LinkedList;

/**
 * Класс обертка, хранит информацию о функции:
 * <ol>
 *     <li>имя {@code name}</li>
 *     <li>возвращаемое значение {@code resultType}</li>
 *     <li>список аргументов {@code args}</li>
 *     <li>ссылка на блок метода {@code ctx}</li>
 * </ol>
 */
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

}
