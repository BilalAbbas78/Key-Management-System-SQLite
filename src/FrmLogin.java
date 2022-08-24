import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class FrmLogin extends JFrame {

    static Connection connection;

    FrmLogin(){
        setTitle("Login Form");
        setSize(600, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setLayout(null);

        JLabel lblLogin = new JLabel("Login Form");
        lblLogin.setFont(new Font("Arial", Font.BOLD, 20));
        lblLogin.setBounds(200, 30, 200, 30);
        add(lblLogin);

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setBounds(100, 100, 100, 30);
        add(lblUsername);

        JTextField txtUsername = new JTextField();
        txtUsername.setBounds(250, 100, 200, 30);
        add(txtUsername);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setBounds(100, 150, 100, 30);
        add(lblPassword);

        JPasswordField txtPassword = new JPasswordField();
        txtPassword.setBounds(250, 150, 200, 30);
        add(txtPassword);

        JButton btnLogin = new JButton("Login");
        btnLogin.setBounds(250, 200, 100, 30);
        add(btnLogin);

        btnLogin.addActionListener(e -> {
            if(txtUsername.getText().trim().isEmpty() || txtPassword.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Username or Password is empty");
            }
            else {
                try {
                    login(txtUsername.getText().trim(), txtPassword.getText().trim());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
    }

    void login(String username, String password) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from Credentials");
        boolean isCredentialsValid = false;
        while (resultSet.next()) {
            if (resultSet.getString("name").equals(username) && resultSet.getString("password").equals(password)) {
                isCredentialsValid = true;
                break;
            }
        }
        if (isCredentialsValid) {
            JOptionPane.showMessageDialog(null, "Login Successful");
//            new FrmLogin().setVisible(true);
//            setVisible(false);
        } else {
            JOptionPane.showMessageDialog(null, "Invalid Credentials");
        }
    }

    public static void connect() throws ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:KMS.db");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) throws ClassNotFoundException {
        connect();
        new FrmLogin().setVisible(true);
    }
}
