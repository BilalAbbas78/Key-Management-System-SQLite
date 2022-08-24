import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.Properties;

public class FrmDashboard extends JFrame {
    static Connection connection;

    FrmDashboard() throws SQLException {
        setTitle("Dashboard");
        setSize(600, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(null);

        JLabel lblRootCertificates = new JLabel("Root Certificates");
        lblRootCertificates.setFont(new Font("Arial", Font.BOLD, 20));
        lblRootCertificates.setBounds(20, 20, 200, 30);
        add(lblRootCertificates);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 60, 500, 350);
        JTable table = new JTable();
        scrollPane.setViewportView(table);
        add(scrollPane);
        setTableRootCertificates(table);

        JButton btnExportRootCertificate = new JButton("Export Root Certificate");
        btnExportRootCertificate.setBounds(320, 420, 200, 30);
        add(btnExportRootCertificate);

        JLabel lblAddRootCertificate = new JLabel("Add Root Certificate");
        lblAddRootCertificate.setFont(new Font("Arial", Font.BOLD, 20));
        lblAddRootCertificate.setBounds(20, 480, 200, 30);
        add(lblAddRootCertificate);


        JLabel lblIssuerName = new JLabel("Issuer Name:");
        lblIssuerName.setBounds(20, 520, 100, 30);
        add(lblIssuerName);

        JTextField txtIssuer = new JTextField();
        txtIssuer.setBounds(120, 520, 200, 30);
        add(txtIssuer);

        JLabel lblValidFrom = new JLabel("Valid From:");
        lblValidFrom.setBounds(20, 560, 100, 30);
        add(lblValidFrom);

        UtilDateModel modelValidFrom = new UtilDateModel();
        JDatePanelImpl datePanelValidFrom = new JDatePanelImpl(modelValidFrom, new Properties());
        JDatePickerImpl datePickerValidFrom = new JDatePickerImpl(datePanelValidFrom, new DateLabelFormatter());
        datePickerValidFrom.setBounds(120, 560, 200, 30);
        add(datePickerValidFrom);

        JLabel lblValidTo = new JLabel("Valid To:");
        lblValidTo.setBounds(20, 600, 100, 30);
        add(lblValidTo);

        UtilDateModel modelValidTo = new UtilDateModel();
        JDatePanelImpl datePanelValidTo = new JDatePanelImpl(modelValidTo, new Properties());
        JDatePickerImpl datePickerValidTo = new JDatePickerImpl(datePanelValidTo, new DateLabelFormatter());
        datePickerValidTo.setBounds(120, 600, 200, 30);
        add(datePickerValidTo);

        JButton btnAddRootCertificate = new JButton("Add Root Certificate");
        btnAddRootCertificate.setBounds(120, 640, 200, 30);
        add(btnAddRootCertificate);






        // Client Certificate

        JLabel lblClientCertificates = new JLabel("Client Certificates");
        lblClientCertificates.setFont(new Font("Arial", Font.BOLD, 20));
        lblClientCertificates.setBounds(600, 20, 200, 30);
        add(lblClientCertificates);

        JScrollPane scrollPaneClientCertificates = new JScrollPane();
        scrollPaneClientCertificates.setBounds(600, 60, 500, 350);
        JTable tableClientCertificates = new JTable();
        scrollPaneClientCertificates.setViewportView(tableClientCertificates);
        add(scrollPaneClientCertificates);
//        setTableClientCertificates(tableClientCertificates);

        JButton btnExportClientCertificate = new JButton("Export Client Certificate");
        btnExportClientCertificate.setBounds(690, 420, 200, 30);
        add(btnExportClientCertificate);

        JButton btnExportAllClientCertificates = new JButton("Export All Client Certificates");
        btnExportAllClientCertificates.setBounds(900, 420, 200, 30);
        add(btnExportAllClientCertificates);

        JLabel lblAddClientCertificate = new JLabel("Add Client Certificate");
        lblAddClientCertificate.setFont(new Font("Arial", Font.BOLD, 20));
        lblAddClientCertificate.setBounds(600, 480, 250, 30);
        add(lblAddClientCertificate);

        JLabel lblClientName = new JLabel("Client Name:");
        lblClientName.setBounds(600, 520, 100, 30);
        add(lblClientName);

        JTextField txtClientName = new JTextField();
        txtClientName.setBounds(690, 520, 200, 30);
        add(txtClientName);

        JLabel lblValidFromClientCertificate = new JLabel("Valid From:");
        lblValidFromClientCertificate.setBounds(600, 560, 100, 30);
        add(lblValidFromClientCertificate);

        UtilDateModel modelValidFromClientCertificate = new UtilDateModel();
        JDatePanelImpl datePanelValidFromClientCertificate = new JDatePanelImpl(modelValidFromClientCertificate, new Properties());
        JDatePickerImpl datePickerValidFromClientCertificate = new JDatePickerImpl(datePanelValidFromClientCertificate, new DateLabelFormatter());
        datePickerValidFromClientCertificate.setBounds(690, 560, 200, 30);
        add(datePickerValidFromClientCertificate);

        JLabel lblValidToClientCertificate = new JLabel("Valid To:");
        lblValidToClientCertificate.setBounds(600, 600, 100, 30);
        add(lblValidToClientCertificate);

        UtilDateModel modelValidToClientCertificate = new UtilDateModel();
        JDatePanelImpl datePanelValidToClientCertificate = new JDatePanelImpl(modelValidToClientCertificate, new Properties());
        JDatePickerImpl datePickerValidToClientCertificate = new JDatePickerImpl(datePanelValidToClientCertificate, new DateLabelFormatter());
        datePickerValidToClientCertificate.setBounds(690, 600, 200, 30);
        add(datePickerValidToClientCertificate);

        JButton btnAddClientCertificate = new JButton("Add Client Certificate");
        btnAddClientCertificate.setBounds(690, 640, 200, 30);
        add(btnAddClientCertificate);







    }

    public static void setTableRootCertificates(JTable table) throws SQLException {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Issuer");
        model.addColumn("Valid From");
        model.addColumn("Valid To");

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from RootCertificates");
        while (resultSet.next()) {
            model.addRow(new Object[]{resultSet.getString("Issuer"), resultSet.getString("ValidFrom"), resultSet.getString("ValidTo")});
        }
        table.setModel(model);
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

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        connect();
        new FrmDashboard().setVisible(true);
    }
}
