import javax.swing.*;

public class MainMenu {
    public MainMenu() {
        JFrame frame = new JFrame("Tabbed Menu");
        JTabbedPane tabbedPane = new JTabbedPane();

        Dao dao = new Dao();

        // Create 'Customer' tab
        tabbedPane.addTab("Customer", new CustomerWindow(dao));

        // Create 'Order' tab
        tabbedPane.addTab("Order", new OrderWindow(dao));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(tabbedPane);
        frame.setSize(800, 600);
        frame.setVisible(true);
    }
}
