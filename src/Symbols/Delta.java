package Symbols;


import java.util.ArrayList;

public class Delta extends Symbol {
    private int environment;
    private ArrayList<Symbol> list;
    
    public Delta() {
        super("delta");
    }
    
    public void setEnvironment(int n) {
        this.environment = n;
    }
    
    public int getEnvironment() {
        return this.environment;
    }
    
    public void setStack(ArrayList<Symbol> list) {
        this.list = list;
    }
    
    public ArrayList<Symbol> getStack() {
        return this.list;
    }
    
}
