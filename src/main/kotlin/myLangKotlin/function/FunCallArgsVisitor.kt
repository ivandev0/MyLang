package myLangKotlin.function

import myLangKotlin.function.FunArgs
import myLangParser.MyLangBaseVisitor
import myLangParser.MyLangParser
import java.util.stream.Collectors

class FunCallArgsVisitor : MyLangBaseVisitor<List<FunArgs>>(){
    override fun visitFunCallArgs(ctx: MyLangParser.FunCallArgsContext?): List<FunArgs> {
        if(ctx == null)
            return emptyList()
        return ctx.funArgs()
                .stream()
                .map { FunArgsVisitor().visitFunArgs(it) }
                .collect(Collectors.toList())
    }
}