package MiniJava.parser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Stack;

import MiniJava.Log.Log;
import MiniJava.codeGenerator.CodeGeneratorFacade;
import MiniJava.scanner.LexicalAnalyzer;
import MiniJava.scanner.token.Token;

public class Parser {
    private final ArrayList<Rule> rules;
    private final Stack<Integer> parsStack;
    private ParseTable parseTable;

    public Parser() {
        parsStack = new Stack<>();
        parsStack.push(0);
        try {
            parseTable = new ParseTable(Files.readAllLines(Paths.get("src/main/resources/parseTable")).get(0));
        } catch (Exception e) {
            e.printStackTrace();
        }
        rules = new ArrayList<>();
        try {
            for (String stringRule : Files.readAllLines(Paths.get("src/main/resources/Rules"))) {
                rules.add(new Rule(stringRule));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void doReduceAction(Action currentAction, Token lookAhead, CodeGeneratorFacade codeGeneratorFacade) {
        Rule rule = rules.get(currentAction.number);
        for (int i = 0; i < rule.RHS.size(); i++) {
            parsStack.pop();
        }

        Log.print(parsStack.peek() + "\t" + rule.getLHS());

        parsStack.push(parseTable.getGotoTable(parsStack.peek(), rule.getLHS()));

        Log.print(parsStack.peek() + "");
        try {
            codeGeneratorFacade.semanticFunction(rule.getSemanticAction(), lookAhead);
        } catch (Exception e) {
            Log.print("Code Genetator Error");
        }
    }

    protected Token doShiftAction(Action currentAction, LexicalAnalyzer lexicalAnalyzer) {
        Token lookAhead;
        parsStack.push(currentAction.number);
        lookAhead = lexicalAnalyzer.getNextToken();
        return lookAhead;
    }

    protected Integer peekParseStack() {
        return parsStack.peek();
    }

    protected Action getCurrentAction(Token lookAhead) {
        Log.print(lookAhead.toString() + "\t" + peekParseStack());
        return parseTable.getActionTable(peekParseStack(), lookAhead);
    }
}
