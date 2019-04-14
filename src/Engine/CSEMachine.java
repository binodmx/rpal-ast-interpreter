package Engine;

import Symbols.*;
import java.util.ArrayList;
import java.util.HashMap;

public class CSEMachine {
    private String ans;
    private ArrayList<Symbol> control;
    private ArrayList<Symbol> stack;
    public HashMap<E, HashMap<Symbol, Symbol>> environment = new HashMap<E, HashMap<Symbol, Symbol>>();
    public HashMap<Symbol, ArrayList<Symbol>> hashmap = new HashMap<Symbol, ArrayList<Symbol>>();
    
    public CSEMachine() {
        
    }  
    
    public void execute() {
        int currentEnvironment = 0;
        while (!control.isEmpty()) {
            // pop last element of the control
            Symbol currentSymbol = control.get(control.size()-1);
            control.remove(control.size()-1);
            
            // rule no. 1
            if (currentSymbol instanceof Var) {
                stack.add(0, environment.get(currentEnvironment).get(currentSymbol));
            // rule no. 2
            } else if (currentSymbol instanceof Lambda) {
            
            // rule no. 3, 4, 10, 12 & 13
            } else if (currentSymbol instanceof Gamma) {
                
            // rule no. 5
            } else if (currentSymbol instanceof E) {
                
            // rule no. 6 & 7
            } else if (currentSymbol instanceof Rator) {
                
            // rule no. 8
            } else if (currentSymbol instanceof Beta) {
                
            // rule no. 9
            } else if (currentSymbol instanceof Tau) {
                
            }
        }        
    }
    
    public String getAnswer() {
        this.execute();
        return stack.get(0).getData();
    }
}
