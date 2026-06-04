package hust.soict.globalict.aims.screen;

import hust.soict.globalict.aims.media.DigitalVideoDisc;
import hust.soict.globalict.aims.media.Media;
import hust.soict.globalict.aims.store.Store;

import javax.swing.JTextField;

public class AddDigitalVideoDiscToStoreScreen extends AddItemToStoreScreen {
    private JTextField directorField;
    private JTextField lengthField;

    public AddDigitalVideoDiscToStoreScreen(Store store, StoreScreen storeScreen) {
        super(store, storeScreen, "Add DVD to Store");
    }

    @Override
    protected void addSpecificFields() {
        directorField = addField("Director:");
        lengthField = addField("Length (min):");
    }

    @Override
    protected Media createMedia() {
        return new DigitalVideoDisc(nextId(), titleField.getText(), categoryField.getText(),
                Float.parseFloat(costField.getText()),
                Integer.parseInt(lengthField.getText()), directorField.getText());
    }
}
