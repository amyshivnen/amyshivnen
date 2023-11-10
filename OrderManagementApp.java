package MenuExample2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrderManagementApp {
    private JFrame frame;
    private JMenuBar menuBar;
    private JMenu userMenu, orderMenu, settingMenu;
    private JMenuItem signInItem, logOutItem, placeOrderItem, setDiscountItem;

    private boolean isAdminLoggedIn = false;
    private double discountPercentage = 0.0;

    public OrderManagementApp() {
        frame = new JFrame("Order Management Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        menuBar = new JMenuBar();

        // User menu
        userMenu = new JMenu("User");
        signInItem = new JMenuItem("Sign In");
        signInItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                signIn();
            }
        });
        logOutItem = new JMenuItem("Log Out");
        logOutItem.setEnabled(false);
        logOutItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logOut();
            }
        });

        userMenu.add(signInItem);
        userMenu.add(logOutItem);
        menuBar.add(userMenu);

        // Order menu
        orderMenu = new JMenu("Order");
        placeOrderItem = new JMenuItem("Place Order");
        placeOrderItem.setEnabled(false);
        placeOrderItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                placeOrder();
            }
        });

        orderMenu.add(placeOrderItem);
        menuBar.add(orderMenu);

        // Setting menu
        settingMenu = new JMenu("Setting");
        setDiscountItem = new JMenuItem("Set Discount");
        setDiscountItem.setEnabled(false);
        setDiscountItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setDiscount();
            }
        });

        settingMenu.add(setDiscountItem);
        menuBar.add(settingMenu);

        frame.setJMenuBar(menuBar);
        frame.setVisible(true);
    }

    private void signIn() {
        String username = JOptionPane.showInputDialog(frame, "Enter username:");
        String password = JOptionPane.showInputDialog(frame, "Enter password:");

        if (isAdmin(username, password)) {
            isAdminLoggedIn = true;
            enableAdminFeatures();
        } else {
            isAdminLoggedIn = false;
        }

        signInItem.setEnabled(false);
        logOutItem.setEnabled(true);
        placeOrderItem.setEnabled(true);
        setDiscountItem.setEnabled(isAdminLoggedIn);
    }

    private void logOut() {
        isAdminLoggedIn = false;
        signInItem.setEnabled(true);
        logOutItem.setEnabled(false);
        placeOrderItem.setEnabled(false);
        setDiscountItem.setEnabled(false);
    }

    private void placeOrder() {
        OrderDialog orderDialog = new OrderDialog(frame, discountPercentage);
        orderDialog.setVisible(true);
    }

    private void setDiscount() {
        try {
            String discountStr = JOptionPane.showInputDialog(frame, "Enter discount percentage:");
            if (discountStr != null) {
                discountPercentage = Double.parseDouble(discountStr);
                JOptionPane.showMessageDialog(frame, "Discount set successfully: " + discountPercentage + "%");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Invalid input. Please enter a valid number for discount.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean isAdmin(String username, String password) {
        // Hardcoded admin credentials
        return username.equals("Admin") && password.equals("123456");
    }

    private void enableAdminFeatures() {
        placeOrderItem.setEnabled(true);
        setDiscountItem.setEnabled(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new OrderManagementApp();
            }
        });
    }
}

class OrderDialog extends JDialog {
    
	private static final long serialVersionUID = 1L;

	private JTextField itemNameField, itemPriceField, orderValueField, discountValueField;

    private double orderValue = 0.0;
    private double discountPercentage;

    public OrderDialog(JFrame parent, double discountPercentage) {
        super(parent, "Place Order", true);

        this.discountPercentage = discountPercentage;

        // Initialize components
        itemNameField = new JTextField(20);
        itemPriceField = new JTextField(10);
        orderValueField = new JTextField(10);
        discountValueField = new JTextField(10);

        // Set default values
        orderValueField.setText("0.0");
        discountValueField.setText("0.0");

        // Create panels
        JPanel addItemPanel = createAddItemPanel();
        JPanel totalValuePanel = createTotalValuePanel();

        // Set layout for the dialog
        setLayout(new BorderLayout());

        // Add panels to the dialog
        add(addItemPanel, BorderLayout.NORTH);
        add(totalValuePanel, BorderLayout.CENTER);

        // Set dialog properties
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(parent);
    }

    private JPanel createAddItemPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JLabel itemNameLabel = new JLabel("Item Name:");
        JLabel itemPriceLabel = new JLabel("Item Price:");

        JButton addItemButton = new JButton("Add Item");
        addItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addItem();
            }
        });

        panel.add(itemNameLabel);
        panel.add(itemNameField);
        panel.add(itemPriceLabel);
        panel.add(itemPriceField);
        panel.add(addItemButton);

        return panel;
    }

    private JPanel createTotalValuePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JLabel orderValueLabel = new JLabel("Order Value:");
        JLabel discountLabel = new JLabel("Discount:");

        panel.add(orderValueLabel);
        panel.add(orderValueField);
        panel.add(discountLabel);
        panel.add(discountValueField);

        return panel;
    }

    private void addItem() {
        try {
            String itemName = itemNameField.getText();
            double itemPrice = Double.parseDouble(itemPriceField.getText());

            orderValue += itemPrice;
            updateOrderValue();

            
            double discountAmount = (discountPercentage / 100) * orderValue;
            discountValueField.setText(String.format("%.2f", discountAmount));

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid number for item price.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateOrderValue() {
        orderValueField.setText(String.format("%.2f", orderValue));
    }
}



