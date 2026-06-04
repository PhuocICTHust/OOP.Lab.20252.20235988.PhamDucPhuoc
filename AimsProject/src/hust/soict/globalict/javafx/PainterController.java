package hust.soict.globalict.javafx;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class PainterController {

    @FXML private Canvas canvas;
    @FXML private ToggleGroup tools;
    @FXML private RadioButton penButton;
    @FXML private RadioButton eraserButton;

    private GraphicsContext gc;

    @FXML
    public void initialize() {
        gc = canvas.getGraphicsContext2D();
    }

    /**
     * Called continuously while the mouse is dragged across the canvas.
     * If the Eraser radio button is selected we paint with WHITE (erasing
     * on a white canvas). Otherwise the Pen draws with BLACK.
     * Pen and Eraser belong to the same ToggleGroup, so only one can be active.
     */
    @FXML
    private void canvasMouseDragged(MouseEvent e) {
        double x = e.getX();
        double y = e.getY();

        if (eraserButton.isSelected()) {
            gc.setFill(Color.WHITE);
            gc.fillRect(x - 10, y - 10, 20, 20); // bigger square = eraser
        } else {
            gc.setFill(Color.BLACK);
            gc.fillOval(x - 2, y - 2, 4, 4);     // small dot = pen
        }
    }
}
