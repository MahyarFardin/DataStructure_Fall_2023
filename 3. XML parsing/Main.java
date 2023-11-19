import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void DFS(Node<String> node, int indent){
        if(node.isLeaf())   return;

        for(int i = 0 ; i < node.getChildren().size(); i++){
            // printing number of indentation
            for(int j=0; j<indent; j++)
                System.out.print("\t");

            System.out.println(node.getChildren().get(i).getData());
            // this is a queue data type
            DFS(node.getChildren().get(i), indent+1);
        }
    }
    public static void main(String[] args) throws FileNotFoundException {
        // reading file
        FileInputStream fis = new FileInputStream("./input.txt");
        Scanner scanner = new Scanner(fis);
        // this is the guarding stack that watches the tag pairing
        Stack<String> tags_stack = new Stack<>();
        // this one keeps the hierarchie of tags
        Stack<String> tags_hierachie = new Stack<>();
        // use this as a buffer to read tag names character by character
        String tag_buffer = "";
        // tag tree
        Node tree = new Node<String>(null);
        // keeping trac of root (We did not implement a tree data structure for whole!)
        Node root = tree;
        // keeping track of line number
        int line_number = 0;

        while (scanner.hasNextLine()) {
            // reading file and removing empty spaces before and after
            String line = scanner.nextLine().strip();
            // are we inside a tag?
            boolean open_tag_flag = false;

            for (int i = 0; i < line.length(); i++) {
                if (line.charAt(i) == '<') {
                    if(open_tag_flag)
                        throw new RuntimeException("line " + line_number + ": tag is not closed");
                    else
                        open_tag_flag =! open_tag_flag;
                } else if (line.charAt(i) == '>') {
                    if (!open_tag_flag)
                        throw new RuntimeException("line " + line_number + ": tag is not opened");
                    else {
                        // creating an node for our tag
                        Node<String> new_node = new Node<String>(tag_buffer);
                        // is it ending tag or opening tag
                        if (tag_buffer.charAt(0) == '/' ) {
                            // if it is ending tag check that it is paired right
                            String last_tag = tags_stack.pop();
                            if(last_tag.equals(tag_buffer.substring(1))){
                                tags_hierachie.push(last_tag);
                                // if a tag is closed go to its parent
                                tree = tree.getParent();
                                open_tag_flag = !open_tag_flag;
                                tag_buffer = "";
                            }
                            else
                                throw new RuntimeException("Line "+line_number+": tags are not properly closed");
                        } else {
                            // if it is opening tag create a new node and chain it
                            // then move to the new node
                            tags_stack.push(tag_buffer);
                            tree.addChild(new_node);
                            tree = new_node;
                            tag_buffer = ""; 
                        }
                    }
                } else {
                    // do not read obsolete texts between tags
                    if (open_tag_flag) {
                        tag_buffer += line.charAt(i);
                    }
                }
            }
            line_number++;
        }

        System.out.println("List of all nodes:");
        System.out.println(tags_hierachie);

        System.out.println("Hierarchie of nodes");
        DFS(root, 0);
    }
}
