package myLang;

import myLang.interpreter.MyLangInterpreter;
import myLangParser.MyLangLexer;
import myLangParser.MyLangParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import myLang.response.MyLangException;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        String input;
        try {
            byte[] encoded = Files.readAllBytes(Paths.get("src/main/resources/input.txt"));
            input = new String(encoded, Charset.forName("UTF8"));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        MyLangLexer lexer = new MyLangLexer(CharStreams.fromString(input));

        CommonTokenStream tokens = new CommonTokenStream(lexer);
        MyLangParser parser = new MyLangParser(tokens);

        MyLangInterpreter interpreter = new MyLangInterpreter();
        try {
            interpreter.visitCompilationUnit(parser.compilationUnit());
        } catch (MyLangException e){
            e.printStackTrace();
            System.exit(1);
        }

    }
}
