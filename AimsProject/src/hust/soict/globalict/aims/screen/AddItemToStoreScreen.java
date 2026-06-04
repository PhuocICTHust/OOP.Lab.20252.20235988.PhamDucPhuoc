package hust.soict.globalict.aims.screen;

import hust.soict.globalict.aims.media.Media;
import hust.soict.globalict.aims.store.Store;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.GridLayout;

/**
 * Abstract parent for the three "Add ... to store" windows.
 *
 * This uses the Template Method pattern:
 *  - the parent builds the common Title / Category / Cost fields and the
 *    "Add to store" button;
 *  - each subclass plugs in its own extra fields via addSpecificFields()
 *    and builds the concrete Media object via createMedia().
 *
 * Note: addSpecificFields() is called from the constructor, so subclasses
 * must ASSIGN their extra JTextField fields inside that method (do not give
 * them initializers at declaration, or those would overwrite the assignment).
 */
public abstract class AddItemToStoreScreen extends JFrame {
    protected Store store;
    protected StoreScreen storeScreen;   // used to refresh the grid after adding

    protected JPanel formPanel;
    protected JTextField titleField;
    protected JTextField categoryField;
    protected JTextField costField;

    public AddItemToStoreScreen(Store store, StoreScreen storeScreen, String windowTitle) {
        this.store = store;
        this.storeScreen = storeScreen;

        setTitle(windowTitle);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        formPanel = new JPanel(new GridLayout(0, 2, 8, 8));
        titleField = addField("Title:");
        categoryField = addField("Category:");
        costField = addField("Cost ($):");

        addSpecificFields();   // subclass hook

        JButton addButton = new JButton("Add to store");
        addButton.addActionListener(e -> onAdd());

        setLayout(new BorderLayout(10, 10));
        ((JComponent) getContentPane()).setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        add(formPanel, BorderLayout.CENTER);
        add(addButton, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /** Adds a labelled text field to the form and returns it. */
    protected JTextField addField(String label) {
        JTextField tf = new JTextField(20);
        formPanel.add(new JLabel(label));
        formPanel.add(tf);
        return tf;
    }

    /** A simple next-id strategy based on the current store size. */
    protected int nextId() {
        return store.getItemsInStore().size() + 1;
    }

    // ---- Hooks the subclasses must implement ----
    protected abstract void addSpecificFields();
    protected abstract Media createMedia() throws NumberFormatException;

    private void onAdd() {
        try {
            Media media = createMedia();
            store.addMedia(media);
            if (storeScreen != null) {
                storeScreen.displayStore();   // reflect the new item immediately
            }
            JOptionPane.showMessageDialog(this,
                    "\"" + media.getTitle() + "\" has been added to the store.");
            dispose();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Please enter valid numbers for cost / length.",
                    "Invalid input", JOptionPane.ERROR_MESSAGE);
        }
    }
}
