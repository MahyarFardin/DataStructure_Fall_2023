public class Main {
    public static void main(String[] args) throws Exception {
        PriorityQueue pq = new PriorityQueue<>();

        
        pq.push(7);
        pq.push(1);
        pq.push(1);
        pq.push(7);
        pq.push(2);
        pq.push(0);
        pq.push(1);
        pq.push(2);
        pq.push(7);
        pq.push(5);
        pq.push(6);
        pq.push(3);
        pq.push(4);
        pq.push(7);
        pq.push(7);

        System.out.println(pq.pop());
        System.out.println(pq.pop());
        System.out.println(pq.pop());        
        System.out.println(pq.pop());
        System.out.println(pq.pop());

    }
}
