package Engine;

public class CSEMachineFactory {
    public CSEMachineFactory() {
        
    }
    
    public CSEMachine getCSEMachine(AbstractSyntaxTree ast) {
        return new CSEMachine();
    }
}
