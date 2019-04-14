package Engine;

public class AbstractSyntaxTree {
    private Node root;
    
    public AbstractSyntaxTree(Node root) {
        this.setRoot(root);
    }
    
    public void setRoot(Node root) {
        this.root = root;
    }
    
    public Node getRoot() {
        return this.root;
    }
    
    public void standardize() {  
        if (!this.root.isStandardized) {
            this.root.standardize();
        }
    }
    
    public void preOrderTraverse(Node node) {
        System.out.println(node.getData());
        node.children.forEach((child) -> preOrderTraverse(child));
    }
    
    public void printStandardizedAst() {
        
    }
}
