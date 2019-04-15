package Main;

import java.util.ArrayList;
import Engine.*;

public class myrpal {
    public static void main(String[] args) {        
        String fn = args[0];                                                    // get file name        
        FileReaderFactory frf = new FileReaderFactory();                        // create file reader factory
        FileReader fr = frf.getFileReader(fn);                                  // get file reader to read given file
        
        ArrayList<String> data = fr.getData();                                  // read file data        
        AbstractSyntaxTreeFactory astf = new AbstractSyntaxTreeFactory();       // create abstract syntax tree factory
        AbstractSyntaxTree ast = astf.getAbstractSyntaxTree(data);              // get abstract syntax tree from astf using file data
        
        ast.standardize();                                                      // standardize ast
        CSEMachineFactory csemf = new CSEMachineFactory();                      // create cse machine factory
        ast.printAst();
        /*
        CSEMachine csem = csemf.getCSEMachine(ast);                             // get cse machine with cse generated by ast
        
        csem.evaluate();                                                        // evaluate cse
        String ans = csem.getAnswer();                                          // get answer
        System.out.println(ans);                                                // print answer
        */
    }
}
