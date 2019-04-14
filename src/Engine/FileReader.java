package Engine;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReader {
    private final String fileName;
    private Scanner scanner;
    private ArrayList<String> data;
    
    public FileReader(String fileName) {
        this.fileName = fileName;
    }
    
    private void openFile() {
        try {
            this.scanner = new Scanner(new File(this.fileName));
        } catch(Exception e) {
            System.out.println("Could not find the file.");
        }
    }
    
    private void readFile() {
        this.data = new ArrayList<String>();
        while(this.scanner.hasNext()) {
             this.data.add(this.scanner.next());
        }
    }
    
    private void closeFile() {
        this.scanner.close();
    }
    
    public ArrayList<String> getData() {
        this.openFile();
        this.readFile();
        this.closeFile();
        return this.data;
    }
}