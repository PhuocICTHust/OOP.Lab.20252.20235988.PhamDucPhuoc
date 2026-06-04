package hust.soict.globalict.aims.screen;

import hust.soict.globalict.aims.cart.Cart;
import hust.soict.globalict.aims.media.DigitalVideoDisc;
import hust.soict.globalict.aims.media.Media;
import hust.soict.globalict.aims.store.Store;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;

/**
 * The main Swing window for the Store. Uses BorderLayout:
 *   NORTH  -> menu bar (View store / Update store / View cart)
 *   CENTER -> a GridLayout of MediaStore cells, one per item in the store.
 */
public class StoreScreen extends JFrame {
    private Store store;
    private Cart cart;
    private JPanel center;   // kept as a field so we can refresh it

    public StoreScreen(Store store, Cart cart) {
        this.store = store;
        this.cart = cart;

        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());

        cp.add(createMenuBar(), BorderLayout.NORTH);

        center = new JPanel();
        cp.add(center, BorderLayout.CENTER);
        displayStore();   // populate the grid

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Store");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /** Rebuilds the grid from the current contents of the store. */
    public void displayStore() {
        center.removeAll();
        center.setLayout(new GridLayout(0, 3, 2, 2)); // 3 columns, rows grow as needed
        for (Media media : store.getItemsInStore()) {
            center.add(new MediaStore(media, cart));
        }
        center.revalidate();
        center.repaint();
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Options");

        JMenuItem viewStore = new JMenuItem("View store");
        viewStore.addActionListener(e -> displayStore());

        // Update store -> submenu with the three "add" screens.
        JMenu smUpdateStore = new JMenu("Update store");
        JMenuItem addBook = new JMenuItem("Add Book");
        JMenuItem addCD = new JMenuItem("Add CD");
        JMenuItem addDVD = new JMenuItem("Add DVD");
        addBook.addActionListener(e -> new AddBookToStoreScreen(store, this));
        addCD.addActionListener(e -> new AddCompactDiscToStoreScreen(store, this));
        addDVD.addActionListener(e -> new AddDigitalVideoDiscToStoreScreen(store, this));
        smUpdateStore.add(addBook);
        smUpdateStore.add(addCD);
        smUpdateStore.add(addDVD);

        JMenuItem viewCart = new JMenuItem("View cart");
        viewCart.addActionListener(e -> new CartScreen(cart));

        menu.add(viewStore);
        menu.add(smUpdateStore);
        menu.add(viewCart);
        menuBar.add(menu);
        return menuBar;
    }

    /** Convenience launcher so the GUI can be started directly. */
    public static void main(String[] args) {
        Store store = new Store();
        Cart cart = new Cart();
        store.addMedia(new DigitalVideoDisc(1, "The Lion King", "Animation", 19.95f, 87, "Roger Allers"));
        store.addMedia(new DigitalVideoDisc(2, "Star Wars", "Science Fiction", 24.95f, 124, "George Lucas"));
        store.addMedia(new DigitalVideoDisc(3, "Aladdin", "Animation", 18.99f, 90, "Ron Clements"));

        javax.swing.SwingUtilities.invokeLater(() -> new StoreScreen(store, cart));
    }
}
