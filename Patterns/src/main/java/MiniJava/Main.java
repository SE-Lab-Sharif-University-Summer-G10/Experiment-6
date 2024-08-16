package MiniJava;

import MiniJava.errorHandler.ErrorHandler;
import MiniJava.parser.ParserFacade;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(new File("src/main/resources/code"));
            ParserFacade parserFacade = new ParserFacade(scanner);

            // start parsing
            parserFacade.startParsing();
        } catch (FileNotFoundException e) {
            ErrorHandler.printError(e.getMessage());
        }
    }
}
