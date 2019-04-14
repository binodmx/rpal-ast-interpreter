package Symbols;

import java.util.ArrayList;

public class Lambda extends Symbol {
    private int index;
    private int environment;
    private ArrayList<Symbol> list;
    
    public Lambda() {
        super("lambda");
    }
    
    public void setIndex(int i) {
        this.index = i;
    }
    
    public int getIndex() {
        return this.index;
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
