package hust.soict.globalict.aims;

import hust.soict.globalict.aims.cart.Cart;
import hust.soict.globalict.aims.media.DigitalVideoDisc;
import hust.soict.globalict.aims.media.Media;
import hust.soict.globalict.aims.media.Playable;
import hust.soict.globalict.aims.store.Store;

import java.util.Scanner;

public class Aims {
    private static Store store = new Store();
    private static Cart cart = new Cart();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        store.addMedia(new DigitalVideoDisc(1, "The Lion King", "Animation", 19.95f, 87, "Roger Allers"));
        store.addMedia(new DigitalVideoDisc(2, "Star Wars", "Science Fiction", 24.95f, 124, "George Lucas"));

        int choice;
        do {
            showMenu();
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    storeMenu();
                    break;
                case 2:
                    System.out.println("Update store feature coming soon...");
                    break;
                case 3:
                    cartMenu();
                    break;
                case 0:
                    System.out.println("Exiting AIMS...");
                    break;
                default:
                    System.out.println("Invalid choice. Please choose again.");
            }
        } while (choice != 0);
    }

    public static void showMenu() {
        System.out.println("\nAIMS: ");
        System.out.println("--------------------------------");
        System.out.println("1. View store");
        System.out.println("2. Update store");
        System.out.println("3. See current cart");
        System.out.println("0. Exit");
        System.out.println("--------------------------------");
        System.out.print("Please choose a number: 0-1-2-3: ");
    }

    public static void storeMenu() {
        int choice;
        do {
            store.print(); // Hiển thị các mặt hàng trong kho
            System.out.println("\nOptions: ");
            System.out.println("--------------------------------");
            System.out.println("1. See a media's details");
            System.out.println("2. Add a media to cart");
            System.out.println("3. Play a media");
            System.out.println("4. See current cart");
            System.out.println("0. Back");
            System.out.println("--------------------------------");
            System.out.print("Please choose a number: 0-1-2-3-4: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter the title of the media: ");
                    String title = scanner.nextLine();
                    Media foundMedia = store.searchByTitle(title);
                    if (foundMedia != null) {
                        System.out.println(foundMedia.toString());
                        mediaDetailsMenu(foundMedia);
                    } else {
                        System.out.println("Media not found in store.");
                    }
                    break;
                case 2:
                    System.out.print("Enter the title of the media to add to cart: ");
                    String titleToAdd = scanner.nextLine();
                    Media mediaToAdd = store.searchByTitle(titleToAdd);
                    if (mediaToAdd != null) {
                        cart.addMedia(mediaToAdd);
                    } else {
                        System.out.println("Media not found in store.");
                    }
                    break;
                case 3:
                    System.out.print("Enter the title of the media to play: ");
                    String titleToPlay = scanner.nextLine();
                    Media mediaToPlay = store.searchByTitle(titleToPlay);
                    if (mediaToPlay != null) {
                        if (mediaToPlay instanceof Playable) {
                            ((Playable) mediaToPlay).play();
                        } else {
                            System.out.println("This media cannot be played (e.g., it's a Book).");
                        }
                    } else {
                        System.out.println("Media not found in store.");
                    }
                    break;
                case 4:
                    cartMenu();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid choice. Please choose again.");
            }
        } while (choice != 0);
    }

    public static void mediaDetailsMenu(Media media) {
        int choice;
        do {
            System.out.println("\nOptions: ");
            System.out.println("--------------------------------");
            System.out.println("1. Add to cart");
            System.out.println("2. Play");
            System.out.println("0. Back");
            System.out.println("--------------------------------");
            System.out.print("Please choose a number: 0-1-2: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    cart.addMedia(media);
                    break;
                case 2:
                    if (media instanceof Playable) {
                        ((Playable) media).play();
                    } else {
                        System.out.println("This media type cannot be played.");
                    }
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);
    }

    public static void cartMenu() {
        int choice;
        do {
            cart.print();
            System.out.println("\nOptions: ");
            System.out.println("--------------------------------");
            System.out.println("1. Filter medias in cart");
            System.out.println("2. Sort medias in cart");
            System.out.println("3. Remove media from cart");
            System.out.println("4. Play a media");
            System.out.println("5. Place order");
            System.out.println("0. Back");
            System.out.println("--------------------------------");
            System.out.print("Please choose a number: 0-1-2-3-4-5: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Filter by: 1. ID | 2. Title");
                    int filterOption = scanner.nextInt();
                    scanner.nextLine();
                    if (filterOption == 1) {
                        System.out.print("Enter ID to filter: ");
                        int id = scanner.nextInt();
                        Media m = cart.searchById(id);
                        if (m != null) System.out.println("Found: " + m.toString());
                        else System.out.println("No match found.");
                    } else if (filterOption == 2) {
                        System.out.print("Enter Title to filter: ");
                        String title = scanner.nextLine();
                        Media m = cart.searchByTitle(title);
                        if (m != null) System.out.println("Found: " + m.toString());
                        else System.out.println("No match found.");
                    }
                    break;
                case 2:
                    System.out.println("Sort by: 1. Title then Cost | 2. Cost then Title");
                    int sortOption = scanner.nextInt();
                    scanner.nextLine();
                    if (sortOption == 1) cart.sortByTitleCost();
                    else if (sortOption == 2) cart.sortByCostTitle();
                    else System.out.println("Invalid option.");
                    break;
                case 3:
                    System.out.print("Enter the title of media to remove: ");
                    String titleToRemove = scanner.nextLine();
                    Media mRemove = cart.searchByTitle(titleToRemove);
                    if (mRemove != null) cart.removeMedia(mRemove);
                    else System.out.println("Media not found in cart.");
                    break;
                case 4:
                    System.out.print("Enter the title of media to play: ");
                    String titleToPlay = scanner.nextLine();
                    Media mPlay = cart.searchByTitle(titleToPlay);
                    if (mPlay != null) {
                        if (mPlay instanceof Playable) ((Playable) mPlay).play();
                        else System.out.println("This media cannot be played.");
                    } else {
                        System.out.println("Media not found in cart.");
                    }
                    break;
                case 5:
                    System.out.println("An order has been created. The cart is now empty.");
                    cart.empty();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);
    }
}