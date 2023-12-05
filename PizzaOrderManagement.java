package OrderM;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class PizzaOrderManagement {
    private JFrame frame;
    private JTextArea orderTextArea;
    private ArrayList<OrderItem> orderItems;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PizzaOrderManagement().createAndShowGUI());
    }

    private void createAndShowGUI() {
        frame = new JFrame("Order Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createMenuBar();
        createMainPanel();

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
       // Menu
    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu userMenu = new JMenu("User");
        userMenu.setMnemonic('U');
        JMenuItem loginMenuItem = new JMenuItem("Log in");
        loginMenuItem.setMnemonic('L');
        loginMenuItem.addActionListener(e -> showLoginDialog());
        
        //Quit menu item(of user menu)
        JMenuItem quitMenuItem = new JMenuItem("Quit");
        quitMenuItem.setMnemonic('Q');
        quitMenuItem.addActionListener(e -> System.exit(0));

        userMenu.add(loginMenuItem);
        userMenu.add(quitMenuItem);

        JMenu orderMenu = new JMenu("Order");
        orderMenu.setMnemonic('O');
        JMenuItem drinkMenuItem = new JMenuItem("Drink");
        drinkMenuItem.setMnemonic('D');
        drinkMenuItem.addActionListener(e -> showDrinkInputDialog());
        JMenuItem pizzaMenuItem = new JMenuItem("Pizza");
        pizzaMenuItem.setMnemonic('P');
        pizzaMenuItem.addActionListener(e -> showPizzaOrderDialog());

        orderMenu.add(drinkMenuItem);
        orderMenu.add(pizzaMenuItem);

        JMenu checkoutMenu = new JMenu("Check Out");
        JMenuItem totalMenuItem = new JMenuItem("Total");
        totalMenuItem.addActionListener(e -> showTotalDialog());

        checkoutMenu.add(totalMenuItem);

        menuBar.add(userMenu);
        menuBar.add(orderMenu);
        menuBar.add(checkoutMenu);

        frame.setJMenuBar(menuBar);
    }

    private void createMainPanel() {
        JPanel mainPanel = new JPanel();
        orderTextArea = new JTextArea(10, 30);
        mainPanel.add(new JLabel("Welcome!"));
        mainPanel.add(orderTextArea);
        frame.add(mainPanel);
    }
       // Log In menu item(of user menu)
    private void showLoginDialog() {
        String username = JOptionPane.showInputDialog(frame, "Please enter your user name:");
        String password = JOptionPane.showInputDialog(frame, "Please enter your password:");
        frame.setTitle("Order Management - Welcome, " + username + password);
    }
      //Drink Menu item (of order menu)
    private void showDrinkInputDialog() {
        String drinkCountStr = JOptionPane.showInputDialog(frame, "Please enter the number of drinks:");
        if (drinkCountStr != null && !drinkCountStr.isEmpty()) {
            int drinkCount = Integer.parseInt(drinkCountStr);
            double cost = 3.00 * drinkCount;
            addOrderItem("Drink", cost);
        }
    }
       // Pizza menu item(of order menu)
    private void showPizzaOrderDialog() {
    	JDialog pizzaOrderDialog = new JDialog(frame, "Pizza Order", true);
        pizzaOrderDialog.setLayout(new BoxLayout(pizzaOrderDialog.getContentPane(), BoxLayout.Y_AXIS));

        List<String> ingredients = List.of("Tomato Sauce", "Cheese", "Pineapple", "Olives", "Mushrooms");
        List<String> meats = List.of("Chicken", "Salami", "Pepperoni", "Meat Ball");

        JList<String> ingredientList = new JList<>(ingredients.toArray(new String[0]));
        ingredientList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane ingredientScrollPane = new JScrollPane(ingredientList);
        ingredientScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        JList<String> meatList = new JList<>(meats.toArray(new String[0]));
        meatList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane meatScrollPane = new JScrollPane(meatList);
        meatScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        JButton orderButton = new JButton("Order");
        orderButton.addActionListener(orderButtonEvent -> {
            handlePizzaOrder(ingredientList.getSelectedValuesList(), meatList.getSelectedValue());
            pizzaOrderDialog.dispose(); 
        });

        pizzaOrderDialog.add(new JLabel("Select one or multiple Ingredients:"));
        pizzaOrderDialog.add(ingredientScrollPane);
        pizzaOrderDialog.add(new JLabel("Select one Meat:"));
        pizzaOrderDialog.add(meatScrollPane);
        pizzaOrderDialog.add(orderButton);

        pizzaOrderDialog.setSize(300, 300);
        pizzaOrderDialog.setLocationRelativeTo(frame);
        pizzaOrderDialog.setVisible(true);
    }

    private void handlePizzaOrder(List<String> selectedIngredients, String selectedMeat) {
        double cost = calculatePizzaCost(selectedIngredients.size(), selectedMeat != null);
        addOrderItem("Pizza", cost);
    }

    private double calculatePizzaCost(int ingredientCount, boolean hasMeat) {
        double ingredientCost = 1.50 * ingredientCount;
        double meatCost = hasMeat ? 2.00 : 0.00;
        return ingredientCost + meatCost;
    }

    private void showTotalDialog() {
        double total = calculateTotal();
        JOptionPane.showMessageDialog(frame, "Total Order Cost: €" + total);
    }
        //Total menu item
    private void addOrderItem(String itemName, double cost) {
        if (orderItems == null) {
            orderItems = new ArrayList<>();
        }
        orderItems.add(new OrderItem(itemName, cost));
        updateOrderTextArea();
    }

    private void updateOrderTextArea() {
        orderTextArea.setText("");
        if (orderItems != null) {
            for (OrderItem item : orderItems) {
                orderTextArea.append(item.getName() + ": €" + item.getPrice() + "\n");
            }
        }
    }

    private double calculateTotal() {
        double total = 0;
        if (orderItems != null) {
            for (OrderItem item : orderItems) {
                total += item.getPrice();
            }
        }
        return total;
    }

    private static class OrderItem {
        private String name;
        private double price;

        public OrderItem(String name, double price) {
            this.name = name;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }
    }
}

