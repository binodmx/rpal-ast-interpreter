package Engine;

import Symbols.*;
import java.util.ArrayList;

public class CSEMachine {
    private ArrayList<Symbol> control;
    private ArrayList<Symbol> stack;
    private ArrayList<Symbol> environment;

    public CSEMachine(ArrayList<Symbol> control, ArrayList<Symbol> stack, ArrayList<Symbol> environment) {
        this.setControl(control);
        this.setStack(stack);
        this.setEnvironment(environment);
    }  
    
    public void setControl(ArrayList<Symbol> control) {
        this.control = control;
    }
    
    public void setStack(ArrayList<Symbol> stack) {
        this.stack = stack;
    }
    
    public void setEnvironment(ArrayList<Symbol> environment) {
        this.environment = environment;
    }
    
    public void execute() {
        E currentEnvironment = (E) this.control.get(0);
        while (!control.isEmpty()) {
            // pop last element of the control
            Symbol currentSymbol = control.get(control.size()-1);
            control.remove(control.size()-1);
            
            // rule no. 1
            if (currentSymbol instanceof Id) {
                stack.add(0, this.lookup((Id) currentSymbol, currentEnvironment));
            // rule no. 2
            } else if (currentSymbol instanceof Lambda) {
                Lambda lambda = (Lambda) currentSymbol;
                lambda.setEnvironment(currentEnvironment.getIndex());
                this.stack.add(0, lambda);
            // rule no. 3, 4, 10, 11, 12 & 13
            } else if (currentSymbol instanceof Gamma) {
                Symbol nextSymbol = this.stack.get(0);
                this.stack.remove(0);
                // lambda (rule no. 4 & 11)
                if (nextSymbol instanceof Lambda) {
                    Lambda lambda = (Lambda) nextSymbol;
                    E e = new E(lambda.getIndex());
                    if (lambda.identifiers.size() == 1) {
                        e.values.put(lambda.identifiers.get(0), this.stack.get(0));
                        this.stack.remove(0);
                    } else {
                        Tup tup = (Tup) this.stack.get(0);
                        this.stack.remove(0);
                        int i = 0;
                        for (Id id: lambda.identifiers) {
                            e.values.put(id, tup.symbols.get(i++));
                        }
                    }
                    this.control.add(e);
                    this.control.add(lambda.getDelta());
                    this.stack.add(0, e);
                }
                // tup (rule no. 10)
                if (nextSymbol instanceof Tup) {
                    Tup tup = (Tup) this.stack.get(0);
                    this.stack.remove(0);
                    int i = Integer.parseInt(this.stack.get(0).getData());
                    this.stack.remove(0);
                    this.stack.add(0, tup.symbols.get(i));
                }
                // ystar (rule no. 12)
                if (nextSymbol instanceof Ystar) {
                    Lambda lambda = (Lambda) nextSymbol;
                    this.stack.remove(0);
                    Eta eta = new Eta();
                    eta.setIndex(lambda.getIndex());
                    eta.setEnvironment(lambda.getEnvironment());
                    eta.setIdentifier(lambda.identifiers.get(0));
                    eta.setLambda(lambda);
                    this.stack.add(0, eta);
                }
                // eta (rule no. 13)
                if (nextSymbol instanceof Eta) {
                    Eta eta = (Eta) nextSymbol;
                    this.control.add(new Gamma());
                    this.control.add(new Gamma());
                    this.stack.add(0, eta.getLambda());
                }
            // rule no. 5
            } else if (currentSymbol instanceof E) {
                this.stack.remove(1);
            // rule no. 6 & 7
            } else if (currentSymbol instanceof Rator) {
                if (currentSymbol instanceof Uop) {
                    Symbol rator = currentSymbol;
                    Symbol rand = this.stack.get(0);
                    this.stack.remove(0);
                    stack.add(0, this.applyUnaryOperation(rator, rand));
                }
                if (currentSymbol instanceof Bop) {
                    Symbol rator = currentSymbol;
                    Symbol rand1 = this.stack.get(0);
                    Symbol rand2 = this.stack.get(1);
                    this.stack.remove(0);
                    this.stack.remove(1);
                    this.stack.add(0, this.applyBinaryOperation(rator, rand1, rand2));
                }
            // rule no. 8
            } else if (currentSymbol instanceof Beta) {
                if (Boolean.parseBoolean(this.stack.get(0).getData())) {
                    this.control.remove(control.size()-1); 
                } else {
                    this.control.remove(control.size()-2); 
                }
                this.stack.remove(0);
            // rule no. 9
            } else if (currentSymbol instanceof Tau) {
                Tau tau = (Tau) currentSymbol;
                Tup tup = new Tup();
                for (int i = 0; i < tau.getN(); i++) {
                    tup.symbols.add(this.stack.get(0));
                    this.stack.remove(0);
                }
                this.stack.add(0, tup);
            }
        }        
    }
    
    public void printControl() {
        for (Symbol symbol: this.control) {
            System.out.println(symbol.getData());
        }
    }
    
    public Symbol lookup(Id id, E e) {
        return e.values.get(id);
    }
    
    public Symbol applyUnaryOperation(Symbol rator, Symbol rand) {
        if (rator.getData() == "neg") {
            int val = Integer.parseInt(rand.getData());
            return new Int(Integer.toString(-1*val));
        } else if (rator.getData() == "not") {
            boolean val = Boolean.parseBoolean(rand.getData());
            return new Bool(Boolean.toString(!val));
        } else {
            return new Err();
        }
    }
    
    public Symbol applyBinaryOperation(Symbol rator, Symbol rand1, Symbol rand2) {
        if (rator.getData() == "+") {
            int val1 = Integer.parseInt(rand1.getData());
            int val2 = Integer.parseInt(rand2.getData());
            return new Int(Integer.toString(val1+val2));
        } else if (rator.getData() == "-") {
            int val1 = Integer.parseInt(rand1.getData());
            int val2 = Integer.parseInt(rand2.getData());
            return new Int(Integer.toString(val1-val2));
        } else if (rator.getData() == "*") {
            int val1 = Integer.parseInt(rand1.getData());
            int val2 = Integer.parseInt(rand2.getData());
            return new Int(Integer.toString(val1*val2));
        } else if (rator.getData() == "/") {
            int val1 = Integer.parseInt(rand1.getData());
            int val2 = Integer.parseInt(rand2.getData());
            return new Int(Integer.toString(val1/val2));
        } else if (rator.getData() == "**") {
            int val1 = Integer.parseInt(rand1.getData());
            int val2 = Integer.parseInt(rand2.getData());
            return new Int(Double.toString(Math.pow(val1, val2)));
        } else if (rator.getData() == "&") {            
            boolean val1 = Boolean.parseBoolean(rand1.getData());
            boolean val2 = Boolean.parseBoolean(rand2.getData());
            return new Bool(Boolean.toString(val1 && val2));
        } else if (rator.getData() == "or") {            
            boolean val1 = Boolean.parseBoolean(rand1.getData());
            boolean val2 = Boolean.parseBoolean(rand2.getData());
            return new Bool(Boolean.toString(val1 || val2));
        } else if (rator.getData() == "eq") {            
            String val1 = rand1.getData();
            String val2 = rand2.getData();
            return new Bool(Boolean.toString(val1 == val2));
        } else if (rator.getData() == "ne") {            
            String val1 = rand1.getData();
            String val2 = rand2.getData();
            return new Bool(Boolean.toString(val1 != val2));
        } else if (rator.getData() == "ls") {            
            int val1 = Integer.parseInt(rand1.getData());
            int val2 = Integer.parseInt(rand2.getData());
            return new Bool(Boolean.toString(val1 < val2));
        } else if (rator.getData() == "le") {            
            int val1 = Integer.parseInt(rand1.getData());
            int val2 = Integer.parseInt(rand2.getData());
            return new Bool(Boolean.toString(val1 <= val2));
        } else if (rator.getData() == "gr") {            
            int val1 = Integer.parseInt(rand1.getData());
            int val2 = Integer.parseInt(rand2.getData());
            return new Bool(Boolean.toString(val1 > val2));
        } else if (rator.getData() == "ge") {            
            int val1 = Integer.parseInt(rand1.getData());
            int val2 = Integer.parseInt(rand2.getData());
            return new Bool(Boolean.toString(val1 >= val2));
        } else if (rator.getData() == "aug") {            
            Tup val1 = (Tup) rand1;
            Tup val2 = (Tup) rand2;
            Tup tup = new Tup();
            tup.symbols.addAll(val1.symbols);
            tup.symbols.addAll(val2.symbols);                    
            return tup;
        } else {
            return new Err();
        }
    }
    
    public String getAnswer() {
        this.execute();
        return stack.get(0).getData();
    }
}
