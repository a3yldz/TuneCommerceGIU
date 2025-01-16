import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class MusicScreen extends JFrame {
    private JComboBox<String> choose;
    public Map<String, Integer> tunes;
    private List<String> pocket;
    private JButton addToCart;
    private JButton playTune;
    private JButton viewCart;
    private JLabel priceLabel;

    public MusicScreen() {
        setTitle("Buyers Screen");
        setSize(400, 250);
        setLayout(new GridLayout(2, 1));

        tunes = new HashMap<>();
        tunes.put("Music 1", 8);
        tunes.put("Music 2", 5);
        tunes.put("Music 3", 3);
        tunes.put("Music 4", 3);
        tunes.put("Music 5", 2);

        JPanel screen = new JPanel(new FlowLayout());
        String[] musics = tunes.keySet().toArray(new String[0]);

        choose = new JComboBox<>(musics);
        choose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updatePriceLabel();
            }
        });
        screen.add(choose);

        priceLabel = new JLabel();
        updatePriceLabel();
        screen.add(priceLabel);

        JPanel button = new JPanel(new FlowLayout());
        addToCart = new JButton("Add to Cart");
        playTune = new JButton("Play Sample Tune");
        viewCart = new JButton("View Cart");
        pocket = new ArrayList<>();

        addToCart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String choosetunes = (String) choose.getSelectedItem();
                if (!pocket.contains(choosetunes)) {
                    pocket.add(choosetunes);
                    JOptionPane.showMessageDialog(MusicScreen.this, choosetunes + " added.");
                } else {
                    JOptionPane.showMessageDialog(MusicScreen.this, choosetunes + " already exists.");
                }
            }
        });

        playTune.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String choosetunes = (String) choose.getSelectedItem();
                String desktopFilePath;
                switch (choosetunes) {
                    case "Music 1":
                        desktopFilePath = "path";
                        break;
                    case "Music 2":
                        desktopFilePath = "path";
                        break;
                    case "Music 3":
                        desktopFilePath = "path";
                        break;
                    case "Music 4":
                        desktopFilePath = "path";
                        break;
                    case "Music 5":
                        desktopFilePath = "path";
                        break;
                    default:
                        JOptionPane.showMessageDialog(MusicScreen.this, "No music selected.");
                        return;
                }

                try {
                    File soundFile = new File(desktopFilePath);
                    if (!soundFile.exists()) {
                        JOptionPane.showMessageDialog(MusicScreen.this, "The selected tune file does not exist.");
                        return;
                    }
                    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
                    Clip clip = AudioSystem.getClip();
                    clip.open(audioInputStream);
                    clip.start();
                } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(MusicScreen.this, "Error playing the selected tune: " + e.getMessage());
                }
            }
        });

        viewCart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                CartScreen cart = new CartScreen(pocket);
                cart.setVisible(true);

            }
        });

        add(screen);
        add(button);
        button.add(addToCart);
        button.add(playTune);
        button.add(viewCart);
        setVisible(true);
    }

    private void updatePriceLabel() {
        String selectedTune = (String) choose.getSelectedItem();
        if (selectedTune != null) {
            Integer price = tunes.get(selectedTune);
            if (price != null) {
                priceLabel.setText("Price: ₺" + price);
            } else {
                priceLabel.setText("Price: Unknown");
            }
        } else {
            priceLabel.setText("Price: Unknown");
        }
    }

    public void updatePrice(String tune, int newPrice) {
        tunes.put(tune, newPrice);
        updatePriceLabel();
    }



}
