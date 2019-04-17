package Symbols;

import java.util.HashMap;

public class E extends Symbol {
    private int index;
    private E parent;
    private boolean isRemoved = false;
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
    
    public void setParent(E e) {
        this.parent = e;
    }
    
    public E getParent() {
        return this.parent;
    }
    
    public void setIsRemoved(boolean isRemoved) {
        this.isRemoved = isRemoved;
    }
    
    public boolean getIsRemoved() {
        return this.isRemoved;
    }
    
    public Symbol lookup(Id id){
        for (Id key: this.values.keySet()) {
            if (key.getData().equals(id.getData())){
                return this.values.get(key);
            }
        }
        if (this.parent != null) {
            return this.parent.lookup(id);
        } else {
            return new Symbol(id.getData());
        }
    }
}
