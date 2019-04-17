package Engine;

import Symbols.*;
import java.util.ArrayList;

public class CSEMachineFactory {
    private E e0 = new E(0);
    private int i = 1;
    private int j = 0;
    
    public CSEMachineFactory() {
        
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
                if (node.getData().startsWith("<ID:")) { 
                    return new Id(node.getData().substring(4, node.getData().length()-1));
                } else if (node.getData().startsWith("<INT:")) {                    
                    return new Int(node.getData().substring(5, node.getData().length()-1));
                } else if (node.getData().startsWith("<STR:")) {                    
                    return new Int(node.getData().substring(6, node.getData().length()-2));
                } else if (node.getData().startsWith("<nil")) {                    
                    return new Tup();
                } else if (node.getData().startsWith("<true>")) {                    
                    return new Bool("true");
                } else if (node.getData().startsWith("<false>")) {                    
                    return new Bool("false");
                } else if (node.getData().startsWith("<dummy>")) {                    
                    return new Dummy();
                } else {
                    System.out.println("Err node: "+node.getData());
                    return new Err();
                }          
        }
    }
    
    public B getB(Node node) {
        B b = new B();
        b.symbols = this.getPreOrderTraverse(node);
        return b;
    }
    
    public Lambda getLambda(Node node) {
        Lambda lambda = new Lambda(this.i++);
        lambda.setDelta(this.getDelta(node.children.get(1)));
        if (",".equals(node.children.get(0).getData())) {
            for (Node identifier: node.children.get(0).children) {
                lambda.identifiers.add(new Id(identifier.getData().substring(4, node.getData().length()-1)));
            }
        } else {
            lambda.identifiers.add(new Id(node.children.get(0).getData().substring(4, node.getData().length()-1)));
        }
        return lambda;
    }
    
    private ArrayList<Symbol> getPreOrderTraverse(Node node) {
        ArrayList<Symbol> symbols = new ArrayList<Symbol>();
        if ("lambda".equals(node.getData())) {
            symbols.add(this.getLambda(node));
        } else if ("->".equals(node.getData())) {
            symbols.add(this.getDelta(node.children.get(1)));
            symbols.add(this.getDelta(node.children.get(2)));
            symbols.add(new Beta());
            symbols.add(this.getB(node.children.get(0)));
        } else {
            symbols.add(this.getSymbol(node));
            for (Node child: node.children) {
                symbols.addAll(this.getPreOrderTraverse(child));
            }
        }
        return symbols;
    }
    
    public Delta getDelta(Node node) {
        Delta delta = new Delta(this.j++);
        delta.symbols = this.getPreOrderTraverse(node);
        return delta;        
    }
    
    public ArrayList<Symbol> getControl(AbstractSyntaxTree ast) {
        ArrayList<Symbol> control = new ArrayList<Symbol>();
        control.add(this.e0);
        control.add(this.getDelta(ast.getRoot()));
        return control;
    }
    
    public ArrayList<Symbol> getStack() {
        ArrayList<Symbol> stack = new ArrayList<Symbol>();
        stack.add(this.e0);
        return stack;
    }
    
    public ArrayList<E> getEnvironment() {
        ArrayList<E> environment = new ArrayList<E>();
        environment.add(this.e0);
        return environment;
    }
    
    public CSEMachine getCSEMachine(AbstractSyntaxTree ast) {        
        return new CSEMachine(this.getControl(ast), this.getStack(), this.getEnvironment());
    }
}
