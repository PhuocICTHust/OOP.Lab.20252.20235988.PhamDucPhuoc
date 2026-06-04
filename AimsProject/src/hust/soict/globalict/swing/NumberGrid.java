package hust.soict.globalict.swing;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 * A numeric keypad: digits 0-9, plus DEL (delete last char) and C (clear).
 * All button presses are handled by the single inner class ButtonListener,
 * which decides what to do based on the button's action command (its label).
 */
public class NumberGrid extends JFrame {
    private JTextField tfDisplay;

    // Layout order for a 4x3 grid.
    private static final String[] BUTTON_LABELS = {
            "1", "2", "3",
            "4", "5", "6",
            "7", "8", "9",
            "DEL", "0", "C"
    };

    public NumberGrid() {
        // Top: the display where digits accumulate.
        tfDisplay = new JTextField();
        tfDisplay.setEditable(false);
        tfDisplay.setHorizontalAlignment(JTextField.RIGHT);
        tfDisplay.setFont(new Font("Monospaced", Font.BOLD, 24));
        add(tfDisplay, BorderLayout.NORTH);

        // Center: the grid of buttons. One shared listener for all of them.
        JPanel panelButtons = new JPanel(new GridLayout(4, 3, 5, 5));
        ButtonListener listener = new ButtonListener();
        for (String label : BUTTON_LABELS) {
            JButton btn = new JButton(label);
            btn.setFont(new Font("SansSerif", Font.BOLD, 18));
            btn.addActionListener(listener);
            panelButtons.add(btn);
        }
        add(panelButtons, BorderLayout.CENTER);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Number Grid");
        setSize(300, 360);
        setVisible(true);
    }

    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand(); // the button's label

            switch (command) {
                case "DEL": {
                    String current = tfDisplay.getText();
                    if (!current.isEmpty()) {
                        tfDisplay.setText(current.substring(0, current.length() - 1));
                    }
                    break;
                }
                case "C":
                    tfDisplay.setText("");
                    break;
                default: // any digit 0-9
                    tfDisplay.setText(tfDisplay.getText() + command);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(NumberGrid::new);
    }
}
