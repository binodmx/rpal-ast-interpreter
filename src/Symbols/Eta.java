package Symbols;

public class Eta extends Symbol {
    private int index;
    private int environment;
    private Id identifier;
    private Lambda lambda;
    
    public Eta() {
        super("eta");
    }    
    
    public void setIndex(int i) {
        this.index = i;
    }
    
    public void setEnvironment(int e) {
        this.environment = e;
    }
    
    public void setIdentifier(Id id) {
        this.identifier = id;
    }
    
    public void setLambda(Lambda lambda) {
        this.lambda = lambda;
    }
    
    public Lambda getLambda() {
        return this.lambda;
    }
    
}
