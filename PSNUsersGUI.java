package examen2_lab2;

import java.awt.FlowLayout;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;

public class PSNUsersGUI extends JFrame implements ActionListener {

    private PSNUsers psn;

    private JTextField usernameField;
    private JButton addUserButton;
    private JButton deactivateUserButton;
    private JButton addTrophyButton;
    private JButton playerInfoButton;
    private JTextArea outputArea;

    public PSNUsersGUI() {
        psn = new PSNUsers();

        setTitle("PSN Users");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new FlowLayout());
        panel.setLayout(null);

        usernameField = new JTextField();
        usernameField.setBounds(20, 20, 150, 25);
        panel.add(usernameField);

        addUserButton = new JButton("Add User");
        addUserButton.setBounds(180, 20, 100, 25);
        addUserButton.addActionListener(this);
        panel.add(addUserButton);

        deactivateUserButton = new JButton("Deactivate User");
        deactivateUserButton.setBounds(20, 50, 150, 25);
        deactivateUserButton.addActionListener(this);
        panel.add(deactivateUserButton);

        addTrophyButton = new JButton("Add Trophy");
        addTrophyButton.setBounds(180, 50, 100, 25);
        addTrophyButton.addActionListener(this);
        panel.add(addTrophyButton);

        playerInfoButton = new JButton("Player Info");
        playerInfoButton.setBounds(20, 80, 150, 25);
        playerInfoButton.addActionListener(this);
        panel.add(playerInfoButton);

        outputArea = new JTextArea();
        outputArea.setBounds(20, 120, 340, 130);
        panel.add(outputArea);

        add(panel); 
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addUserButton) {
            String username = usernameField.getText();
            try {
                psn.addUser(username);
                outputArea.append("User added: " + username + "\n");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == deactivateUserButton) {
            String username = usernameField.getText();
            try {
                psn.deactivateUser(username);
                outputArea.append("User deactivated: " + username + "\n");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == addTrophyButton) {
            String username = usernameField.getText();
            String trophyGame = JOptionPane.showInputDialog("Enter trophy game:");
            String trophyName = JOptionPane.showInputDialog("Enter trophy name:");
            Trophy trophyType = (Trophy) JOptionPane.showInputDialog(null,
                    "Choose trophy type:", "Trophy Type",
                    JOptionPane.QUESTION_MESSAGE, null,
                    Trophy.values(), Trophy.values()[0]);
            try {
                psn.addTrophyTo(username, trophyGame, trophyName, trophyType);
                outputArea.append("Trophy added to user: " + username + "\n");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == playerInfoButton) {
            String username = usernameField.getText();
            try {
                psn.playerInfo(username);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new PSNUsersGUI();

    }
}
