import java.util.*;

public class Node<T> {
    private LinkedList<Node<T>> children = new LinkedList<>();
    private Node<T> parent;
    private T data = null;

    public Node(T data) {
        this.data = data;
    }

    public LinkedList<Node<T>> getChildren() {
        return children;
    }
    public void addChild(Node<T> child) {
        child.setParent(this);
        this.children.add(child);
    }

    public void addChild(T data) {
        Node<T> newChild = new Node<>(data);
        this.addChild(newChild);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    private void setParent(Node<T> parent) {
        this.parent = parent;
    }

    public Node<T> getParent() {
        return parent;
    }

    public boolean isLeaf(){
        return children.size() == 0;
    }
}