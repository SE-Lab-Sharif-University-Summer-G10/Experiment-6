package MiniJava.semantic.symbol;

import MiniJava.codeGenerator.VarType;

/**
 * Created by mohammad hosein on 6/28/2015.
 */

public enum SymbolType {
    Int(VarType.Int),
    Bool(VarType.Bool),
    ;

    public final VarType varType;

    SymbolType(VarType varType) {
        this.varType = varType;
    }
}