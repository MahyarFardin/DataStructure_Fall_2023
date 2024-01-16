import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;

public class PriorityQueue<E>{
    private int m_size;
    private LinkedList<Node> m_content;
    private Comparator<E> m_cmp;

    private class Node<E>{
        private E m_data;
        private int m_key;

        public Node(E data, int key) {
            m_data = data;
            m_key = key;
        }

        public E getData() {
            return m_data;
        }

        public int getKey() {
            return m_key;
        }

        public void setKey(int key) {
            m_key = key;
        }

        public void setData(E data){
            this.m_data = data;
        }

        public String toString() {
            return "(data: " + m_data + ", key: " + m_key + ")";
        }
    }

    public PriorityQueue() {
        m_content = new LinkedList<>();
        m_cmp = new Comparator<E>() {
            @Override
            public int compare(E input1, E input2) {
                return ((Comparable) input1).compareTo(input2);
            }
        };
    }

    public int size() {
        return m_size;
    }

    public boolean isEmpty() {
        return m_size == 0;
    }

    public void push(E data) {
        Iterator<Node> iterator = m_content.iterator();
        int newKey = 1;
        boolean new_node_flag = false;
        while (iterator.hasNext()) {
            Node nextEntity = iterator.next();
            if (m_cmp.compare(data, (E) nextEntity.getData()) == 0) {
                newKey = nextEntity.getKey() + 1;
                nextEntity.setKey(newKey);
                new_node_flag = true;
                break;
            }
        }
        if(new_node_flag == false)
            m_content.add(0, new Node(data, newKey));

        m_size++;
    }

    public E pop() {
        if (this.isEmpty())
            throw new IllegalStateException("Priority queue is empty!");

        Node top = top();

        if(top.getKey() == 1)
            m_content.remove(top);
        else
            top.setKey(top.getKey() - 1);

        m_size--;

        return (E) top.getData();
    }

    public Node top() {
        if (isEmpty())
            throw new IllegalStateException("Priority queue is empty");

        Node place_holder = new Node(null, 0);

        Iterator<Node> iterator = m_content.iterator();
        while (iterator.hasNext()) {
            Node nextEntity = iterator.next();
            if (place_holder.getKey() < nextEntity.getKey())
                place_holder = nextEntity;
        }
        return place_holder;
    }
}
