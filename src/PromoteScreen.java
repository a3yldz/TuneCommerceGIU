import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class PromoteScreen extends JFrame {
    private JComboBox<String> tuneComboBox;
    private JTextField newPriceField;
    private JButton setPromotionButton;
    private Map<String, Integer> tunes;


    public PromoteScreen() {

        tunes = new HashMap<>();
        tunes.put("Music 1", 8);
        tunes.put("Music 2", 5);
        tunes.put("Music 3", 3);
        tunes.put("Music 4", 3);
        tunes.put("Music 5", 2);

        setTitle("Promote Tunes");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout());

        tuneComboBox = new JComboBox<>(tunes.keySet().toArray(new String[0]));
        add(tuneComboBox);

        newPriceField = new JTextField(10);
        add(newPriceField);

        setPromotionButton = new JButton("Set Promotion");
        setPromotionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setPromotion();
            }
        });
        add(setPromotionButton);

        setVisible(true);
    }

    private void setPromotion() {
        String selectedTune = (String) tuneComboBox.getSelectedItem();
        String newPriceText = newPriceField.getText();

        if (selectedTune.isEmpty() || newPriceText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a tune and enter a new price.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int newPrice;
        try {
            newPrice = Integer.parseInt(newPriceText);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid price.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        MusicScreen musicScreen = new MusicScreen();
        musicScreen.updatePrice(selectedTune, newPrice);
        JOptionPane.showMessageDialog(this, "Promotion set for " + selectedTune + "new price: ₺" + newPrice, "Success", JOptionPane.INFORMATION_MESSAGE);
        dispose();
    }

}
