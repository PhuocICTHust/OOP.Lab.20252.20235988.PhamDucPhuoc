package hust.soict.globalict.test.cart;

import hust.soict.globalict.aims.cart.Cart;
import hust.soict.globalict.aims.media.DigitalVideoDisc;

public class CartTest {
    public static void main(String[] args) {
        Cart cart = new Cart();

        DigitalVideoDisc dvd1 = new DigitalVideoDisc(1, "The Lion King", "Animation", 19.95f, 87, "Roger Allers");
        cart.addMedia(dvd1); // Đổi addDigitalVideoDisc -> addMedia

        DigitalVideoDisc dvd2 = new DigitalVideoDisc(2, "Star Wars", "Science Fiction", 24.95f, 124, "George Lucas");
        cart.addMedia(dvd2);

        DigitalVideoDisc dvd3 = new DigitalVideoDisc(3, "Aladin", "Animation", 18.99f, 0, "Unknown");
        cart.addMedia(dvd3);

        System.out.println("\n--- Testing Print Method ---");
        cart.print();

        System.out.println("\n--- Testing Search by ID ---");
        System.out.println(cart.searchById(2) != null ? "Found: " + cart.searchById(2).toString() : "Not found");
        System.out.println(cart.searchById(99) != null ? "Found: " + cart.searchById(99).toString() : "Not found");

        System.out.println("\n--- Testing Search by Title ---");
        System.out.println(cart.searchByTitle("Aladin") != null ? "Found: " + cart.searchByTitle("Aladin").toString() : "Not found");
        System.out.println(cart.searchByTitle("Avenger") != null ? "Found: " + cart.searchByTitle("Avenger").toString() : "Not found");
    }
}