package hust.soict.globalict.aims.screen;

import hust.soict.globalict.aims.cart.Cart;
import hust.soict.globalict.aims.media.Media;
import hust.soict.globalict.aims.media.Playable;
import hust.soict.globalict.aims.exception.LimitExceededException;
import hust.soict.globalict.aims.exception.PlayerException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * One cell in the Store grid: shows a media's title and cost, an "Add to cart"
 * button, and (only when the media is Playable) a "Play" button.
 */
public class MediaStore extends JPanel {
    private Media media;
    private Cart cart;

    public MediaStore(Media media, Cart cart) {
        this.media = media;
        this.cart = cart;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel title = new JLabel(media.getTitle());
        title.setFont(new Font("Sans Serif", Font.PLAIN, 18));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel cost = new JLabel(media.getCost() + " $");
        cost.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttons = new JPanel();
        buttons.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton addToCart = new JButton("Add to cart");
        addToCart.addActionListener(new AddToCartListener());
        buttons.add(addToCart);

        // The Play button only exists for media that can actually be played.
        if (media instanceof Playable) {
            JButton play = new JButton("Play");
            play.addActionListener(new PlayListener());
            buttons.add(play);
        }

        add(Box.createVerticalGlue());
        add(title);
        add(cost);
        add(Box.createVerticalGlue());
        add(buttons);

        setBorder(BorderFactory.createLineBorder(Color.GRAY));
    }

    private class AddToCartListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                cart.addMedia(media);
                JOptionPane.showMessageDialog(null,
                        "\"" + media.getTitle() + "\" has been added to your cart.",
                        "Added to cart", JOptionPane.INFORMATION_MESSAGE);
            } catch (LimitExceededException ex) {
                // Show the error in a dialog instead of crashing/printing to console.
                JOptionPane.showMessageDialog(null, ex.getMessage(),
                        "Cart is full", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class PlayListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Show a small JDialog that triggers playback.
            javax.swing.JDialog dialog =
                    new javax.swing.JDialog((java.awt.Frame) null, "Play: " + media.getTitle(), true);
            dialog.setLayout(new FlowLayout());
            JButton playNow = new JButton("Play now");
            playNow.addActionListener(ev -> {
                try {
                    ((Playable) media).play();
                    JOptionPane.showMessageDialog(dialog, "Now playing: " + media.getTitle());
                } catch (PlayerException pe) {
                    // Catch the runtime/play failure and show an error dialog.
                    JOptionPane.showMessageDialog(dialog, pe.getMessage(),
                            "Player error", JOptionPane.ERROR_MESSAGE);
                }
                dialog.dispose();
            });
            dialog.add(new JLabel(media.getTitle()));
            dialog.add(playNow);
            dialog.pack();
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
        }
    }
}
