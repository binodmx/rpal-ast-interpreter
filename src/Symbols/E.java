package Symbols;

public class E extends Symbol {
    private int index;
    
    public E() {
        super("e");
    }
    
    public void setIndex(int i) {
        this.index = i;
    }
    
    public int getIndex() {
        return this.index;
    }
}
