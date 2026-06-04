package hust.soict.globalict.aims.screen;

import hust.soict.globalict.aims.cart.Cart;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javax.swing.JFrame;
import java.io.IOException;

/**
 * A Swing JFrame that embeds the JavaFX cart UI (cart.fxml) using a JFXPanel.
 *
 * Key points:
 *  - Constructing a JFXPanel implicitly starts the JavaFX runtime.
 *  - Any work on the JavaFX scene graph must run on the JavaFX Application
 *    Thread, so we wrap the FXML loading in Platform.runLater(...).
 */
public class CartScreen extends JFrame {
    private Cart cart;

    public CartScreen(Cart cart) {
        this.cart = cart;

        setTitle("Cart");
        setSize(1024, 600);
        setLocationRelativeTo(null);

        final JFXPanel fxPanel = new JFXPanel();
        add(fxPanel);
        setVisible(true);

        Platform.runLater(() -> initFX(fxPanel));
    }

    private void initFX(JFXPanel fxPanel) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("cart.fxml"));
            Parent root = loader.load();

            // Inject the cart into the controller AFTER loading.
            CartScreenController controller = loader.getController();
            controller.setCart(cart);

            fxPanel.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
