public class FriendsList extends ListADT<Friend> {
    private int numFriends;
    private Node<Friend> head;

    //For an empty list.
    public FriendsList() {
        this.numFriends = 0;
        this.head = null;
    }

    //For a list with a starting item (friend).
    public FriendsList(Friend firstFriend) {
        this.numFriends = 1;
        this.head = new Node<Friend>(firstFriend);
    }

    @Override
    public int size() {
        return this.numFriends;
    }

    @Override
    public boolean isEmpty() {
        return this.numFriends == 0;
    }

    @Override
    public void removeAll() {
        this.numFriends = 0;
        this.head = null;
    }

    private Node<Friend> getNodeAt(int index) {
        Node<Friend> n = this.head;
        for(int x = 1; x <= index; x++) {
            n = n.getNext();
        }
        return n;
    } 

    @Override
    public void add(int index, Friend item) throws ListException {
        Node<Friend> newFriend = new Node<Friend>(item);
        if (index < 0  || index > this.size()) {
            throw new ListException("Index " + index + " is invalid for a list of size: " + this.size());
        }
        if(index == 0) {
            newFriend.setNext(this.head);
            this.head = newFriend;
        } else {
            Node<Friend> before = this.getNodeAt(index--);
            Node<Friend> after = before.getNext();

            before.setNext(newFriend);
            newFriend.setNext(after);
        }
        numFriends++;
    }

    @Override
    public Friend get(int index) throws ListException {
        if (index < 0  || index >= this.size()) {
            throw new ListException("Index " + index + " is invalid for a list of size: " + this.size());
        }
        Node<Friend> node = this.getNodeAt(index);
        return node.getItem();
    }

    @Override
    public void remove(int index) throws ListException {
        if (index < 0  || index >= this.size()) {
            throw new ListException("Index " + index + " is invalid for a list of size: " + this.size());
        }
        if(index == 0) {
            this.head = this.head.getNext();
        } else {
            Node<Friend> prev = this.getNodeAt(index--);
            Node<Friend> remove = prev.getNext();
            Node<Friend> next = remove.getNext();

            prev.setNext(next);
            remove.setNext(null);

        }
        this.numFriends--;
    }

    @Override
    public String toString() {
        String s = "[" + this.head;
        return s + "]";
    }
}