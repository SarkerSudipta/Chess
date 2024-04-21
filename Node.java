public class Node
    {   
        Item item;
        Node next;

        public Node(Item i)
        {
            item = i;
        }
        public Node(Item i, Node next)
        {
            this(i);
            this.next = next;
        }

    }