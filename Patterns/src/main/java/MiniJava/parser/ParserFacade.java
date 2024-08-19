package MiniJava.parser;

import MiniJava.Log.Log;
import MiniJava.codeGenerator.CodeGeneratorFacade;
import MiniJava.errorHandler.ErrorHandler;
import MiniJava.scanner.token.Token;
import MiniJava.scanner.LexicalAnalyzer;

import java.util.Scanner;

public class ParserFacade {
    private final Parser parser;
    private final LexicalAnalyzer lexicalAnalyzer;
    private final CodeGeneratorFacade codeGeneratorFacade;

    public ParserFacade(Scanner scanner) {
        this.parser = new Parser();
        codeGeneratorFacade = new CodeGeneratorFacade();
        lexicalAnalyzer = new LexicalAnalyzer(scanner);
    }

    public void startParsing() {
        Token lookAhead = lexicalAnalyzer.getNextToken();
        boolean finish = false;
        Action currentAction;
        while (!finish) {
            try {
                currentAction = parser.getCurrentAction(lookAhead);

                Log.print(currentAction.toString());

                switch (currentAction.action) {
                    case shift:
                        lookAhead = parser.doShiftAction(currentAction, lexicalAnalyzer);
                        break;
                    case reduce:
                        parser.doReduceAction(currentAction, lookAhead, codeGeneratorFacade);
                        break;
                    case accept:
                        finish = true;
                        break;
                }
                Log.print("");
            } catch (Exception ignored) {
                ignored.printStackTrace();
            }
        }
        if (!ErrorHandler.hasError) codeGeneratorFacade.printMemory();
    }
}
