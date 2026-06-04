package hust.soict.globalict.aims.screen;

import hust.soict.globalict.aims.cart.Cart;
import hust.soict.globalict.aims.media.Media;
import hust.soict.globalict.aims.media.Playable;
import hust.soict.globalict.aims.exception.PlayerException;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;

public class CartScreenController {
    private Cart cart;

    @FXML private TableView<Media> tblMedia;
    @FXML private TableColumn<Media, String> colMediaTitle;
    @FXML private TableColumn<Media, String> colMediaCategory;
    @FXML private TableColumn<Media, Float> colMediaCost;

    @FXML private TextField tfFilter;
    @FXML private ToggleGroup filterCategory;
    @FXML private RadioButton radioBtnFilterID;
    @FXML private RadioButton radioBtnFilterTitle;

    @FXML private Button btnPlay;
    @FXML private Button btnRemove;
    @FXML private Button btnPlaceOrder;
    @FXML private Label totalCostLabel;

    /**
     * initialize() runs automatically when the FXML is loaded, BEFORE the Cart
     * is injected. So here we only do UI-only wiring (column factories,
     * hide buttons, selection listener). Cart-dependent wiring lives in setCart().
     */
    @FXML
    private void initialize() {
        colMediaTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colMediaCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colMediaCost.setCellValueFactory(new PropertyValueFactory<>("cost"));

        // Play/Remove start hidden; they appear once a row is selected.
        btnPlay.setVisible(false);
        btnRemove.setVisible(false);

        tblMedia.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Media>() {
                    @Override
                    public void changed(ObservableValue<? extends Media> observable,
                                        Media oldValue, Media newValue) {
                        if (newValue != null) {
                            updateButtonBar(newValue);
                        }
                    }
                });
    }

    /** Remove is always shown for a selection; Play only for Playable media. */
    private void updateButtonBar(Media media) {
        btnRemove.setVisible(true);
        btnPlay.setVisible(media instanceof Playable);
    }

    /** Called by CartScreen after the FXML is loaded to inject the data. */
    public void setCart(Cart cart) {
        this.cart = cart;

        // Wrap the cart's ObservableList in a FilteredList for the filter feature.
        FilteredList<Media> filtered = new FilteredList<>(cart.getItemsOrdered(), p -> true);

        // Re-filter whenever the text OR the chosen radio button changes.
        tfFilter.textProperty().addListener((obs, oldV, newV) -> applyFilter(filtered));
        filterCategory.selectedToggleProperty().addListener((obs, oldV, newV) -> applyFilter(filtered));

        tblMedia.setItems(filtered);
        updateTotalCost();
    }

    private void applyFilter(FilteredList<Media> filtered) {
        String text = tfFilter.getText();
        filtered.setPredicate(media -> {
            if (text == null || text.isEmpty()) {
                return true;
            }
            String f = text.toLowerCase();
            if (radioBtnFilterID.isSelected()) {
                return String.valueOf(media.getId()).contains(f);
            }
            return media.getTitle().toLowerCase().contains(f);
        });
    }

    private void updateTotalCost() {
        totalCostLabel.setText(cart.totalCost() + " $");
    }

    @FXML
    private void btnRemovePressed() {
        Media media = tblMedia.getSelectionModel().getSelectedItem();
        if (media != null) {
            cart.removeMedia(media);
            updateTotalCost();
            // hide buttons again until a new row is picked
            btnPlay.setVisible(false);
            btnRemove.setVisible(false);
        }
    }

    @FXML
    private void btnPlayPressed() {
        Media media = tblMedia.getSelectionModel().getSelectedItem();
        if (media instanceof Playable) {
            try {
                ((Playable) media).play();
                showAlert(Alert.AlertType.INFORMATION, "Playing", "Now playing: " + media.getTitle());
            } catch (PlayerException e) {
                // Catch the play error and show it as a JavaFX Alert instead of crashing.
                showAlert(Alert.AlertType.ERROR, "Player error", e.getMessage());
            }
        }
    }

    @FXML
    private void btnPlaceOrderPressed() {
        if (cart.getItemsOrdered().isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Empty cart", "Your cart is empty.");
            return;
        }
        showAlert(Alert.AlertType.INFORMATION, "Order placed",
                "Order placed! Total: " + cart.totalCost() + " $");
        cart.empty();
        updateTotalCost();
        btnPlay.setVisible(false);
        btnRemove.setVisible(false);
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
