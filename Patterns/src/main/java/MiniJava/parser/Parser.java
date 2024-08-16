package MiniJava.parser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Stack;

import MiniJava.Log.Log;
import MiniJava.codeGenerator.CodeGeneratorFacade;
import MiniJava.errorHandler.ErrorHandler;
import MiniJava.scanner.lexicalAnalyzer;
import MiniJava.scanner.token.Token;

public class Parser {
    private ArrayList<Rule> rules;
    private Stack<Integer> parsStack;
    private ParseTable parseTable;
    private lexicalAnalyzer lexicalAnalyzer;
    private final CodeGeneratorFacade codeGeneratorFacade;

    public Parser() {
        parsStack = new Stack<Integer>();
        parsStack.push(0);
        try {
            parseTable = new ParseTable(Files.readAllLines(Paths.get("src/main/resources/parseTable")).get(0));
        } catch (Exception e) {
            e.printStackTrace();
        }
        rules = new ArrayList<Rule>();
        try {
            for (String stringRule : Files.readAllLines(Paths.get("src/main/resources/Rules"))) {
                rules.add(new Rule(stringRule));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        codeGeneratorFacade = new CodeGeneratorFacade();
    }

    public void startParse(java.util.Scanner sc) {
        lexicalAnalyzer = new lexicalAnalyzer(sc);
        Token lookAhead = lexicalAnalyzer.getNextToken();
        boolean finish = false;
        Action currentAction;
        while (!finish) {
            try {
                Log.print(lookAhead.toString() + "\t" + parsStack.peek());

                currentAction = parseTable.getActionTable(parsStack.peek(), lookAhead);

                Log.print(currentAction.toString());

                switch (currentAction.action) {
                    case shift:
                        lookAhead = doShiftAction(currentAction);

                        break;
                    case reduce:
                        doReduceAction(currentAction, lookAhead);
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

    private void doReduceAction(Action currentAction, Token lookAhead) {
        Rule rule = rules.get(currentAction.number);
        for (int i = 0; i < rule.RHS.size(); i++) {
            parsStack.pop();
        }

        Log.print(parsStack.peek() + "\t" + rule.LHS);

        parsStack.push(parseTable.getGotoTable(parsStack.peek(), rule.LHS));

        Log.print(parsStack.peek() + "");
        try {
            codeGeneratorFacade.semanticFunction(rule.semanticAction, lookAhead);
        } catch (Exception e) {
            Log.print("Code Genetator Error");
        }
    }

    private Token doShiftAction(Action currentAction) {
        Token lookAhead;
        parsStack.push(currentAction.number);
        lookAhead = lexicalAnalyzer.getNextToken();
        return lookAhead;
    }
}
