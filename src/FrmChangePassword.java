import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class FrmChangePassword extends JFrame {
    static Connection connection;
    FrmChangePassword() throws ClassNotFoundException {
        connection = GlobalClass.connect();
        setTitle("Change Password");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setLayout(null);

        JLabel lblChangePassword = new JLabel("Change Password");
        lblChangePassword.setFont(new Font("Arial", Font.BOLD, 20));
        lblChangePassword.setBounds(100, 10, 200, 30);
        add(lblChangePassword);

        JLabel lblEnterNewPassword = new JLabel("Enter New Password");
        lblEnterNewPassword.setBounds(100, 55, 200, 30);
        add(lblEnterNewPassword);

        JTextField txtEnterNewPassword = new JTextField();
        txtEnterNewPassword.setBounds(100, 85, 200, 30);
        add(txtEnterNewPassword);

        JButton btnChangePassword = new JButton("Change Password");
        btnChangePassword.setBounds(100, 150, 200, 30);
        add(btnChangePassword);

        btnChangePassword.addActionListener(e -> {
            if (txtEnterNewPassword.getText().equals(""))
                JOptionPane.showMessageDialog(null, "Please enter new password");
            else {
                try {
                    Statement statement = connection.createStatement();
                    statement.executeUpdate("UPDATE Credentials SET password = '" + txtEnterNewPassword.getText() + "' WHERE name = 'admin'");
                    JOptionPane.showMessageDialog(null, "Password changed successfully");
                    dispose();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    public static void main(String[] args) throws ClassNotFoundException {
        new FrmChangePassword().setVisible(true);
    }
}
