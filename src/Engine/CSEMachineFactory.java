package Engine;

import Symbols.*;
import java.util.ArrayList;

public class CSEMachineFactory {
    public CSEMachineFactory() {
        
    }
    
    private ArrayList<Symbol> getPreOrderTraverse(Node node,int i) {
        ArrayList<Symbol> symbols = new ArrayList<Symbol>();
        if (node.getData() == "lambda") {
            symbols.add(this.getLambda(node, i+1));
        } else if (node.getData() == "->") {
            symbols.add(this.getDelta(node.children.get(1), i+1));
            symbols.add(this.getDelta(node.children.get(2), i+2));
            symbols.add(new Beta());
            symbols.add(this.getB(node, i+3));
        } else {
            symbols.add(this.getSymbol(node));
            for (Node child: node.children) {
                symbols.addAll(this.getPreOrderTraverse(child, i));
            }
        }
        return symbols;
    }
    
    public Symbol getSymbol(Node node) {
        switch (node.getData()) {
            // unary operators
            case "not": 
            case "neg":
                return new Uop(node.getData());
            // binary operators
            case "+": 
            case "-": 
            case "*": 
            case "/": 
            case "**":
            case "&":
            case "or": 
            case "eq": 
            case "ne": 
            case "ls": 
            case "le": 
            case "gr": 
            case "ge":              
            case "aug":
                return new Bop(node.getData());
            // gamma
            case "gamma":
                return new Gamma();
            // tau
            case "tau":
                return new Tau(node.children.size());
            // ystar
            case "ystar": 
                return new Ystar();
            // operands <ID:>, <INT:>, <STR:>, <nil>, <true>, <false>, <dummy>
            default:
                return new Rand(node.getData());
        }
    }
    
    public Lambda getLambda(Node node, int i) {
        Lambda lambda = new Lambda(i);
        lambda.setDelta(this.getDelta(node.children.get(1), i));
        if (node.children.get(0).getData() == ",") {
            for (Node identifier: node.children.get(0).children) {
                lambda.identifiers.add(identifier.getData());
            }
        } else {
            lambda.identifiers.add(node.children.get(0).getData());
        }
        return lambda;
    }
    
    public Delta getDelta(Node node, int i) {
        Delta delta = new Delta(i);
        delta.symbols = this.getPreOrderTraverse(node, i);
        return delta;        
    }
    
    public B getB(Node node, int i) {
        B b = new B();
        b.symbols = this.getPreOrderTraverse(node, i);
        return b;
    }
    
    public ArrayList<Symbol> getControl(AbstractSyntaxTree ast) {
        E e0 = new E(0);
        Delta delta0 = this.getDelta(ast.getRoot(), 0);
        ArrayList<Symbol> control = new ArrayList<Symbol>();
        control.add(e0);
        control.add(delta0);
        return control;
    }
    
    public ArrayList<Symbol> getStack() {
        return new ArrayList<Symbol>();
    }
    
    public CSEMachine getCSEMachine(AbstractSyntaxTree ast) {        
        return new CSEMachine(this.getControl(ast), this.getStack());
    }
}
