package Symbols;

import java.util.HashMap;

public class E extends Symbol {
    private int index;
    public HashMap<String,String> values;
    
    public E(int i) {
        super("e");
        this.setIndex(i);
        this.values = new HashMap<String,String>();
    }
    
    public void setIndex(int i) {
        this.index = i;
    }
    
    public int getIndex() {
        return this.index;
    }
}
