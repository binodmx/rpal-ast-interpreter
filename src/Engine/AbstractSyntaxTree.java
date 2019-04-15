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
    
    private void preOrderTraverse(Node node,int i) {
        for (int n = 0; n < i; n++) {System.out.print(".");}
        System.out.println(node.getData());
        node.children.forEach((child) -> preOrderTraverse(child, i+1));
    }
    
    public void printAst() {
        this.preOrderTraverse(this.getRoot(), 0);
    }
}
