package myLangKotlin

import myLangKotlin.response.MyLangException
import myLangParser.MyLangLexer
import myLangParser.MyLangParser
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream

import java.io.IOException
import java.nio.charset.Charset
import java.nio.file.Files
import java.nio.file.Paths

fun main(args: Array<String>) {
    val input: String
    try {
        val encoded = Files.readAllBytes(Paths.get("src/main/resources/input.txt"))
        input = String(encoded, Charset.forName("UTF8"))
    } catch (e: IOException) {
        e.printStackTrace()
        return
    }

    val lexer = MyLangLexer(CharStreams.fromString(input))

    val tokens = CommonTokenStream(lexer)
    val parser = MyLangParser(tokens)

    try {
        CompilationUnitVisitor().visitCompilationUnit(parser.compilationUnit())
    } catch (e: MyLangException) {
        e.printStackTrace()
        System.exit(1)
    }

}
