package Symbols;

import java.util.ArrayList;

public class Lambda extends Symbol {
    private int index;
    private int environment;
    public ArrayList<Id> identifiers;
    private Delta delta;
    
    public Lambda(int i) {
        super("lambda");
        this.setIndex(i);
        this.identifiers = new ArrayList<Id>();
    }
    
    private void setIndex(int i) {
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
    
    public void setDelta(Delta delta) {
        this.delta = delta;
    }
    
    public Delta getDelta() {
        return this.delta;
    }
}
