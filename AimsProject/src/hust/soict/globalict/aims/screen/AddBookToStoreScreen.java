package hust.soict.globalict.aims.screen;

import hust.soict.globalict.aims.media.Book;
import hust.soict.globalict.aims.media.Media;
import hust.soict.globalict.aims.store.Store;

import javax.swing.JTextField;

public class AddBookToStoreScreen extends AddItemToStoreScreen {
    private JTextField authorsField;

    public AddBookToStoreScreen(Store store, StoreScreen storeScreen) {
        super(store, storeScreen, "Add Book to Store");
    }

    @Override
    protected void addSpecificFields() {
        authorsField = addField("Authors (comma-separated):");
    }

    @Override
    protected Media createMedia() {
        Book book = new Book(nextId(), titleField.getText(), categoryField.getText(),
                Float.parseFloat(costField.getText()));
        for (String author : authorsField.getText().split(",")) {
            if (!author.trim().isEmpty()) {
                book.addAuthor(author.trim());
            }
        }
        return book;
    }
}
