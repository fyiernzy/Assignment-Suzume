package com.assignment.suzume.authentication;

import java.awt.*;
import javax.swing.*;
import com.assignment.suzume.profile.User;
import com.assignment.suzume.profile.Dashboard;
import com.assignment.suzume.database.DatabaseManager;

public class SignInManager {
    private final DatabaseManager databaseManager;

    public SignInManager() {
        this.databaseManager = DatabaseManager.getInstance();
    }

    public void run() {
        setUIFont(new Font("Georgia", Font.PLAIN, 13));

        JPanel panel = createPanel();
        addComponentsToPanel(panel);

        JFrame frame = createFrame(panel);
        setIconImage(frame, "src/main/resources/static/images/daijin.png");

        frame.setComponentZOrder(panel, 0);

        int option = showSignInDialog(frame, panel);

        if (option == JOptionPane.OK_OPTION) {
            String username = getUsernameField(panel);
            String password = getPasswordField(panel);

            if (username.isEmpty() || password.isEmpty()) {
                System.out.println("The username and password field should not be empty!");
                return;
            }

            if (!databaseManager.checkIfUserExists(username)) {
                System.out.println("User does not exist!");
                return;
            }

            if (!databaseManager.checkIfPasswordMatch(username, password)) {
                System.out.println("Password does not match!");
                return;
            }

            databaseManager.loadUser(username);
            Dashboard dashboard = new Dashboard(User.getInstance());
            dashboard.showDashboard();
        } else {
            System.out.println("Sign-in canceled!");
        }
    }

    private void setUIFont(Font font) {
        UIManager.put("Button.font", font);
        UIManager.put("Label.font", font);
        UIManager.put("TextField.font", font);
        UIManager.put("PasswordField.font", font);
    }

    private JPanel createPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        return panel;
    }

    private void addComponentsToPanel(JPanel panel) {
        panel.add(createLabel("Username:"));
        panel.add(createTextField(20));
        panel.add(Box.createVerticalStrut(10));
        panel.add(createLabel("Password:"));
        panel.add(createPasswordField(20));
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Georgia", Font.BOLD, 13));
        return label;
    }

    private JTextField createTextField(int columns) {
        JTextField textField = new JTextField(columns);
        textField.setFont(new Font("Georgia", Font.PLAIN, 13));
        return textField;
    }

    private JPasswordField createPasswordField(int columns) {
        JPasswordField passwordField = new JPasswordField(columns);
        passwordField.setFont(new Font("Georgia", Font.PLAIN, 12));
        return passwordField;
    }

    private JFrame createFrame(JPanel panel) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(panel);
        frame.setTitle("Sign In");
        frame.pack();
        frame.setLocationRelativeTo(null);
        return frame;
    }

    private void setIconImage(JFrame frame, String imagePath) {
        ImageIcon icon = resizeIconImage(imagePath, 64, 64);
        frame.setIconImage(icon.getImage());
    }

    private ImageIcon resizeIconImage(String imagePath, int width, int height) {
        ImageIcon originalIcon = new ImageIcon(imagePath);
        Image originalImage = originalIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }

    private int showSignInDialog(JFrame frame, JPanel panel) {
        ImageIcon icon = resizeIconImage("src/main/resources/static/images/sadaijin.png", 64, 64);
        return JOptionPane.showOptionDialog(frame, panel, "Sign In", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE, icon, null, null);
    }

    private String getUsernameField(JPanel panel) {
        Component[] components = panel.getComponents();
        if (components.length >= 2 && components[1] instanceof JTextField) {
            return ((JTextField) components[1]).getText();
        }
        return "";
    }

    private String getPasswordField(JPanel panel) {
        Component[] components = panel.getComponents();
        for (Component component : components) {
            if (component instanceof JPasswordField) {
                char[] passwordChars = ((JPasswordField) component).getPassword();
                return new String(passwordChars);
            }
        }
        return "";
    }
}
