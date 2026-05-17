package hust.soict.globalict.test.store;

import hust.soict.globalict.aims.media.DigitalVideoDisc;
import hust.soict.globalict.aims.store.Store;

public class StoreTest {
    public static void main(String[] args) {
        Store store = new Store();

        DigitalVideoDisc dvd1 = new DigitalVideoDisc(1, "The Lion King", "Animation", 19.95f, 87, "Roger Allers");
        DigitalVideoDisc dvd2 = new DigitalVideoDisc(2, "Star Wars", "Science Fiction", 24.95f, 124, "George Lucas");
        DigitalVideoDisc dvd3 = new DigitalVideoDisc(3, "Aladdin", "Animation", 18.99f, 0, "Unknown");

        System.out.println("--- Adding DVDs to store ---");
        store.addMedia(dvd1); // Đổi addDVD -> addMedia
        store.addMedia(dvd2);
        store.addMedia(dvd3);

        System.out.println("\n--- Removing DVDs from store ---");
        store.removeMedia(dvd2); // Đổi removeDVD -> removeMedia

        System.out.println("\n--- Removing a DVD that is no longer in store ---");
        store.removeMedia(dvd2);
    }
}