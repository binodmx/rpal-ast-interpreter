package Symbols;


import java.util.ArrayList;

public class Delta extends Symbol {
    private int index;
    public ArrayList<Symbol> symbols;
    
    public Delta(int i) {
        super("delta");
        this.setIndex(i);
    }
    
    private void setIndex(int i) {
        this.index = i;
    }
    
    public int getIndex() {
        return this.index;
    }    
}
