package Symbols;

import java.util.HashMap;

public class E extends Symbol {
    private int index;
    public HashMap<Id,Symbol> values;
    
    public E(int i) {
        super("e");
        this.setIndex(i);
        this.values = new HashMap<Id,Symbol>();
    }
    
    public void setIndex(int i) {
        this.index = i;
    }
    
    public int getIndex() {
        return this.index;
    }
}
