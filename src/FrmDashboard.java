import javax.swing.*;

public class FrmDashboard extends JFrame {
    FrmDashboard() {
        setTitle("Dashboard");
        setSize(600, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(null);


    }

    public static void main(String[] args) {
        new FrmDashboard().setVisible(true);
    }
}
