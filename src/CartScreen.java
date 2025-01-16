import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

public class CartScreen extends JFrame {

    private List<String> cart;
    private Map<String, Integer> tunes;
    private JTextArea cartArea;
    private JTextArea totalText;

    public CartScreen(List<String> cart) {
        this.cart = cart;

        tunes = new HashMap<>();
        tunes.put("Music 1", 8);
        tunes.put("Music 2", 5);
        tunes.put("Music 3", 3);
        tunes.put("Music 4", 3);
        tunes.put("Music 5", 2);

        setTitle("Cart Screen");
        setSize(400, 300);
        setLayout(new BorderLayout());

        cartArea = new JTextArea();
        cartArea.setEditable(false);
        JScrollPane cartScrollPane = new JScrollPane(cartArea);

        totalText = new JTextArea();
        totalText.setEditable(false);

        JButton purchaseButton = new JButton("Purchase");
        purchaseButton.addActionListener(e -> {
            int total = 0;
            StringBuilder message = new StringBuilder("Purchased Tunes:\n");
            for (String tune : cart) {
                Integer price = tunes.get(tune);

                if (price == null) {
                    JOptionPane.showMessageDialog(null, "Error: Tune '" + tune + "' has no associated price.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                message.append(tune).append("\n");
                total += price;
            }
            message.append("Total Paid: ₺").append(total);
            JOptionPane.showMessageDialog(null, message.toString(), "Purchase Confirmation", JOptionPane.INFORMATION_MESSAGE);

            cart.clear();
            updateCart();
            updateTotal();
        });

        JPanel actionPanel = new JPanel(new FlowLayout());
        actionPanel.add(purchaseButton);

        add(cartScrollPane, BorderLayout.CENTER);
        add(actionPanel, BorderLayout.SOUTH);
        add(totalText, BorderLayout.NORTH);

        updateCart();
        updateTotal();

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void updateCart() {
        StringBuilder cartContent = new StringBuilder("Cart Contents:\n");
        for (String tune : cart) {
            cartContent.append(tune).append("\n");
        }
        cartArea.setText(cartContent.toString());
    }

    private void updateTotal() {
        int total = 0;
        for (String tune : cart) {
            Integer price = tunes.get(tune);
            if (price != null) {
                total += price;
            }
        }
        totalText.setText("Total: ₺" + total);
    }
}
