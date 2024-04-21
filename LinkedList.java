public class LinkedList {
    
    private Node top;
    int size;

    // private class Node
    // {   
    //     Move move;
    //     Node next;

    //     private Node(Move possMove)
    //     {
    //         move = possMove;
    //     }
    //     private Node(Move possMove, Node next)
    //     {
    //         this(possMove);
    //         this.next = next;
    //     }

    // }

    public LinkedList(){
        top = null;
        size = 0;
    }

    public void add(Item item){
        if(top == null)
            top = new Node(item);
        else
        {
            Node newNode = new Node(item, top);
            top = newNode;
        }
        size++;
    }

    public Item getItem(int moveNumber){
        Node currNode = top;
        int i = 0;
        
        while(i < size)
        {
            if(i == moveNumber)
                return currNode.item;
            currNode = currNode.next;
            i++;
        }

        return null;
    }


    public int getSize(){
        return size;
    }
}
