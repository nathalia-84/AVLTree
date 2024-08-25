public class Main {
    public static void main(String[] args) {

        AVLTree system = new AVLTree();
        // Insercoes
        system.insertReservation(new Reservation(1, "Alice Silva", "V001", "2024-08-20T15:00:00"));
        system.insertReservation(new Reservation(2, "Joao Pereira", "V002", "2024-08-21T09:00:00"));
        system.insertReservation(new Reservation(3, "Maria Costa", "V001", "2024-08-21T15:00:00"));
        system.insertReservation(new Reservation(4, "Carlos Souza", "V002", "2024-08-22T09:00:00"));
        system.insertReservation(new Reservation(5, "Fernanda Oliveira", "V003", "2024-08-22T10:30:00"));
        system.insertReservation(new Reservation(6, "Beatriz Lima", "V004", "2024-08-23T07:30:00"));

        // Remocoes
        system.removeReservation(2);
        system.removeReservation(4);

       // Impressao em Pre-order
        system.printPreOrder();

        // Buscar
        system.searchReservation(3);
        system.searchReservation(99);

        // Busca por voo Em Ordem
        system.inOrderByFlight("V001");
    }
}
