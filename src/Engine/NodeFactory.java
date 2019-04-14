package Engine;

import java.util.ArrayList;

public class NodeFactory {
    
    public NodeFactory() {
        
    }
    
    public static Node getNode(String data, int depth) {
        Node node = new Node();
        node.setData(data);
        node.setDepth(depth);
        node.children = new ArrayList<Node>();
        return node;
    }
    
    public static Node getNode(String data, int depth, Node parent, ArrayList<Node> children, boolean isStandardize) {
        Node node = new Node();
        node.setData(data);
        node.setDepth(depth);
        node.setParent(parent);
        node.children = children;
        node.isStandardized = isStandardize;
        return node;
    }
}
