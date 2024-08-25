public class Node {
    Reservation reservation;
    int height;
    Node left;
    Node right;

    public Node(Reservation reservation) {
        this.reservation = reservation;
    }
}