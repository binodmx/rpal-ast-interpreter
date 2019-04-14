package Engine;

public class FileReaderFactory {
    public FileReaderFactory() {
        
    }
    
    public FileReader getFileReader(String fileName) {
        return new FileReader(fileName);
    }
}
