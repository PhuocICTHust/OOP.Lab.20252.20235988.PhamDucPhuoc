package hust.soict.globalict.aims.screen;

import hust.soict.globalict.aims.media.CompactDisc;
import hust.soict.globalict.aims.media.Media;
import hust.soict.globalict.aims.store.Store;

import javax.swing.JTextField;

public class AddCompactDiscToStoreScreen extends AddItemToStoreScreen {
    private JTextField directorField;
    private JTextField artistField;

    public AddCompactDiscToStoreScreen(Store store, StoreScreen storeScreen) {
        super(store, storeScreen, "Add CD to Store");
    }

    @Override
    protected void addSpecificFields() {
        directorField = addField("Director:");
        artistField = addField("Artist:");
    }

    @Override
    protected Media createMedia() {
        // CD length is derived from its tracks, so we pass 0 as the base length.
        return new CompactDisc(nextId(), titleField.getText(), categoryField.getText(),
                Float.parseFloat(costField.getText()), 0,
                directorField.getText(), artistField.getText());
    }
}
