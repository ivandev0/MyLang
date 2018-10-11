import myLangKotlin.block.BlockContext
import myLangKotlin.interpreter.MyLangInterpreter
import myLangKotlin.response.MyLangException
import myLangParser.MyLangLexer
import myLangParser.MyLangParser
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.junit.After
import org.junit.Assert
import org.junit.Before

import java.io.ByteArrayOutputStream
import java.io.PrintStream

open class TestBase {
    internal val outContent = ByteArrayOutputStream()
    private val originalOut = System.out

    internal fun run(input: String) {
        val lexer = MyLangLexer(CharStreams.fromString(input))

        val tokens = CommonTokenStream(lexer)
        val parser = MyLangParser(tokens)
        try {
            MyLangInterpreter().visitCompilationUnit(parser.compilationUnit())
        } catch (e: MyLangException) {
            e.printStackTrace()
            Assert.fail()
        }

    }

    internal fun runWithException(input: String, errorMsg: String) {
        val lexer = MyLangLexer(CharStreams.fromString(input))

        val tokens = CommonTokenStream(lexer)
        val parser = MyLangParser(tokens)
        try {
            MyLangInterpreter().visitCompilationUnit(parser.compilationUnit())
            Assert.fail()
        } catch (e: MyLangException) {
            //e.printStackTrace();
            Assert.assertEquals(errorMsg, e.message)
        }

    }

    @Before
    fun setUp() {
        System.setOut(PrintStream(outContent))
    }

    @After
    fun tearDown() {
        BlockContext.clear()
        System.setOut(originalOut)
    }

    companion object {
        init {
            System.setProperty("line.separator", "\r\n")
        }
    }
}
