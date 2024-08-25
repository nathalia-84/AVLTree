import java.util.ArrayList;
import java.util.List;

public class AVLTree {

    private Node root;

    public void updateHeight(Node n) {
        n.height = 1 + Math.max(height(n.left), height(n.right));
    }

    public int height(Node n) {
        return (n == null) ? -1 : n.height;
    }

    public int getBalance(Node n) {
        return (n == null) ? 0 : height(n.right) - height(n.left);
    }

    public Node rotateRight(Node y) {
        Node x = y.left;
        Node z = x.right;
        x.right = y;
        y.left = z;
        updateHeight(y);
        updateHeight(x);
        return x;
    }

    public Node rotateLeft(Node y) {
        Node x = y.right;
        Node z = x.left;
        x.left = y;
        y.right = z;
        updateHeight(y);
        updateHeight(x);
        return x;
    }

    public Node rebalance(Node z) {
        updateHeight(z);
        int balance = getBalance(z);
        if (balance > 1) {
            if (height(z.right.right) > height(z.right.left)) {
                z = rotateLeft(z);
            } else {
                z.right = rotateRight(z.right);
                z = rotateLeft(z);
            }
        } else if (balance < -1) {
            if (height(z.left.left) > height(z.left.right))
                z = rotateRight(z);
            else {
                z.left = rotateLeft(z.left);
                z = rotateRight(z);
            }
        }
        return z;
    }

    public void insertReservation(Reservation reservation){
        try {
            root = insertReservationRecursive(root, reservation);
            System.out.println("Reserva inserida com sucesso: " + reservation.toString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Node insertReservationRecursive(Node node, Reservation reservation) {
        if (node == null) {
            return new Node(reservation);
        } else if (node.reservation.code > reservation.code) {
            node.left = insertReservationRecursive(node.left, reservation);
        } else if (node.reservation.code < reservation.code) {
            node.right = insertReservationRecursive(node.right, reservation);
        } else {
            throw new RuntimeException("Reserva Duplicada!");
        }
        return rebalance(node);
    }

    public void removeReservation(int code) {
        root = removeReservationRecursive(root, code);
        System.out.println("Reserva removida com sucesso: ID = " + code);

    }

    public Node removeReservationRecursive(Node node, int code) {
        if (node == null) {
            return node;
        } else if (node.reservation.code > code) {
            node.left = removeReservationRecursive(node.left, code);
        } else if (node.reservation.code < code) {
            node.right = removeReservationRecursive(node.right, code);
        } else {
            if (node.left == null || node.right == null) {
                node = (node.left == null) ? node.right : node.left;
            } else {
                Node mostLeftChild = mostLeftChild(node.right);
                node.reservation = mostLeftChild.reservation;
                node.right = removeReservationRecursive(node.right, node.reservation.code);
            }
        }
        if (node != null) {
            node = rebalance(node);
        }
        return node;
    }

    Node mostLeftChild(Node node)
    {
        if (node.left != null)
            return mostLeftChild(node.left);

        else
            return node;
    }

    public void printPreOrder() {
        preOrder(root);
    }

    public void preOrder(Node node)
    {
        if(node == null)
        {
            System.out.println("No nodes in the tree");
            return;
        }

        System.out.println(node.reservation.toString());
        if(node.left != null)
            preOrder(node.left);
        if(node.right != null)
            preOrder(node.right);

    }

    public void searchReservation(int code){
        Node node = searchReservationRecursive(root, code);
        if(node != null) {
            System.out.println("Reserva encontrada: " + code);
        }
        System.out.println("Reserva nao encontrada: ID = " + code);
    }

    public Node searchReservationRecursive(Node node, int code)
    {
        if (node == null)
            return null;

        if (code==node.reservation.code)
            return node;

        if (code < node.reservation.code)
            return searchReservationRecursive(node.left, code);

        else
            return searchReservationRecursive(node.right, code);
    }

    public void inOrderByFlight(String flight) {
        System.out.println("Reservas do voo " + flight + ":");
        inOrderByFlightRecursive(root, flight).forEach(System.out::println);
    }

    public List<Reservation> inOrderByFlightRecursive(Node node, String flight) {
        List<Reservation> reservations = new ArrayList<>();

        if (node != null) {
            if (node.left != null) {
                reservations.addAll(inOrderByFlightRecursive(node.left, flight));
            }
            if (node.reservation.getFlight().equals(flight)) {
                reservations.add(node.reservation);
            }
            if (node.right != null) {
                reservations.addAll(inOrderByFlightRecursive(node.right, flight));
            }
        }

        return reservations;
    }
}