package MiniJava.codeGenerator;

import MiniJava.Log.Log;
import MiniJava.scanner.token.Token;

public class CodeGeneratorFacade {
    private final CodeGenerator codeGenerator;

    public CodeGeneratorFacade() {
        codeGenerator = new CodeGenerator();
    }

    public void printMemory() {
        codeGenerator.printMemory();
    }

    public void semanticFunction(int func, Token next) {
        Log.print("codegenerator : " + func);
        switch (func) {
            case 0:
                return;
            case 1:
                codeGenerator.checkID();
                break;
            case 2:
                codeGenerator.pid(next);
                break;
            case 3:
                codeGenerator.fpid();
                break;
            case 4:
                codeGenerator.kpid(next);
                break;
            case 5:
                codeGenerator.intpid(next);
                break;
            case 6:
                codeGenerator.startCall();
                break;
            case 7:
                codeGenerator.call();
                break;
            case 8:
                codeGenerator.arg();
                break;
            case 9:
                codeGenerator.assign();
                break;
            case 10:
                codeGenerator.add();
                break;
            case 11:
                codeGenerator.sub();
                break;
            case 12:
                codeGenerator.mult();
                break;
            case 13:
                codeGenerator.label();
                break;
            case 14:
                codeGenerator.save();
                break;
            case 15:
                codeGenerator._while();
                break;
            case 16:
                codeGenerator.jpf_save();
                break;
            case 17:
                codeGenerator.jpHere();
                break;
            case 18:
                codeGenerator.print();
                break;
            case 19:
                codeGenerator.equal();
                break;
            case 20:
                codeGenerator.less_than();
                break;
            case 21:
                codeGenerator.and();
                break;
            case 22:
                codeGenerator.not();
                break;
            case 23:
                codeGenerator.defClass();
                break;
            case 24:
                codeGenerator.defMethod();
                break;
            case 25:
                codeGenerator.popClass();
                break;
            case 26:
                codeGenerator.extend();
                break;
            case 27:
                codeGenerator.defField();
                break;
            case 28:
                codeGenerator.defVar();
                break;
            case 29:
                codeGenerator.methodReturn();
                break;
            case 30:
                codeGenerator.defParam();
                break;
            case 31:
                codeGenerator.lastTypeBool();
                break;
            case 32:
                codeGenerator.lastTypeInt();
                break;
            case 33:
                codeGenerator.defMain();
                break;
        }
    }
}
