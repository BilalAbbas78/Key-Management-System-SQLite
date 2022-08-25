import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Properties;

public class FrmDashboard extends JFrame {
    static Connection connection;
    static int selectedRootCertificateId = 0, selectedClientCertificateId = 0;
    static ArrayList<Integer> selectedRootCertificateIds = new ArrayList<>();

    FrmDashboard() throws SQLException, ClassNotFoundException {
        connection = GlobalClass.connect();
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
        JTable tableRootCertificates = new JTable();
        scrollPane.setViewportView(tableRootCertificates);
        add(scrollPane);
        setTableRootCertificates(tableRootCertificates);

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

        JTextField txtIssuerName = new JTextField();
        txtIssuerName.setBounds(120, 520, 200, 30);
        add(txtIssuerName);

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
        setTableClientCertificates(tableClientCertificates);

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

        btnAddRootCertificate.addActionListener(e -> {
            String issuerName = txtIssuerName.getText().trim();
            if (issuerName.isEmpty() || datePickerValidFrom.getModel().getValue() == null || datePickerValidTo.getModel().getValue() == null) {
                JOptionPane.showMessageDialog(null, "Please fill all fields");
            } else {
                try {
                    String validFrom = datePickerValidFrom.getModel().getValue().toString();
                    String validTo = datePickerValidTo.getModel().getValue().toString();
                    X509Certificate selfSignedX509Certificate = CertificateGenerator.generateSelfSignedX509Certificate(issuerName, validFrom, validTo);
                    insertRootCertificateIntoDB(selfSignedX509Certificate, CertificateGenerator.privateKey);
                    txtIssuerName.setText("");
                    datePickerValidFrom.getModel().setValue(null);
                    datePickerValidTo.getModel().setValue(null);
                    txtClientName.setText("");
                    datePickerValidFromClientCertificate.getModel().setValue(null);
                    datePickerValidToClientCertificate.getModel().setValue(null);
                    JOptionPane.showMessageDialog(this, "Root Certificate Generated Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                    setTableRootCertificates(tableRootCertificates);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });

        tableRootCertificates.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tableRootCertificates.getSelectedRow();
                selectedRootCertificateId = selectedRootCertificateIds.get(row);
                selectedClientCertificateId = 0;
                try {
                    setTableClientCertificates(tableClientCertificates);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                    throw new RuntimeException(ex);
                }

            }
        });

        tableClientCertificates.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                selectedClientCertificateId = tableClientCertificates.getSelectedRow() + 1;
            }
        });

        btnAddClientCertificate.addActionListener(e -> {
            String clientName = txtClientName.getText().trim();
            if (clientName.isEmpty() || datePickerValidFromClientCertificate.getModel().getValue() == null || datePickerValidToClientCertificate.getModel().getValue() == null) {
                JOptionPane.showMessageDialog(null, "Please fill all fields");
            }
            else if (selectedRootCertificateId == 0)
                JOptionPane.showMessageDialog(null, "Please select a Root Certificate");
            else {
                try {
                    String validFrom = datePickerValidFromClientCertificate.getModel().getValue().toString();
                    String validTo = datePickerValidToClientCertificate.getModel().getValue().toString();

                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery("SELECT * FROM RootCertificates WHERE certificateId = " + selectedRootCertificateId);
                    PrivateKey privateKey = null;
                    if (resultSet.next())
                        privateKey = CertificateGenerator.getPrivateKeyFromString(resultSet.getString("privateKey"));

                    X509Certificate x509Certificate = CertificateGenerator.generateCertificateSignedX509Certificate(tableRootCertificates.getModel().getValueAt(tableRootCertificates.getSelectedRow(), 0).toString(), txtClientName.getText(), 1, privateKey, validFrom, validTo);
                    insertClientCertificateIntoDB(x509Certificate, CertificateGenerator.privateKey);
                    txtIssuerName.setText("");
                    datePickerValidFrom.getModel().setValue(null);
                    datePickerValidTo.getModel().setValue(null);
                    txtClientName.setText("");
                    datePickerValidFromClientCertificate.getModel().setValue(null);
                    datePickerValidToClientCertificate.getModel().setValue(null);
                    JOptionPane.showMessageDialog(this, "Client Certificate Generated Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                    setTableClientCertificates(tableClientCertificates);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });

        btnExportRootCertificate.addActionListener(e -> {
            if (selectedRootCertificateId == 0)
                JOptionPane.showMessageDialog(null, "Please select a Root Certificate");
            else {
                try {
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery("SELECT * FROM RootCertificates WHERE certificateId = " + selectedRootCertificateId);
                    X509Certificate certificate = null;
//                    if (resultSet.next())
                    certificate = CertificateGenerator.getCertificateFromString(resultSet.getString("certificateKey"));
                    exportCertificate(certificate, resultSet.getString("issuerName"), resultSet.getString("privateKey"));

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                    throw new RuntimeException(ex);
                }
            }
        });

        btnExportClientCertificate.addActionListener(e -> {
            if (selectedClientCertificateId == 0)
                JOptionPane.showMessageDialog(null, "Please select a Client Certificate");
            else {
                try {

                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery("SELECT * FROM ClientCertificates WHERE rootCertificateId = " + selectedRootCertificateId + " AND clientName = '" + tableClientCertificates.getModel().getValueAt(tableClientCertificates.getSelectedRow(), 0) + "'" );

                    if (resultSet.next()) {
                        X509Certificate certificate = CertificateGenerator.getCertificateFromString(resultSet.getString("certificateKey"));
                        exportCertificate(certificate, resultSet.getString("clientName"), resultSet.getString("privateKey"));
                    }




//                    Statement statement = connection.createStatement();
//                    ResultSet resultSet = statement.executeQuery("SELECT * FROM ClientCertificates WHERE certificateId = " + selectedClientCertificateId);
//                    X509Certificate certificate = null;
//                    if (resultSet.next())
//                        certificate = CertificateGenerator.getX509CertificateFromString(resultSet.getString("certificateKey"));
//                    exportCertificate(certificate, resultSet.getString("clientName"), resultSet.getString("privateKey"));

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                    throw new RuntimeException(ex);
                }
            }
        });







    }

    private void insertClientCertificateIntoDB(X509Certificate certificate, PrivateKey privateKey) {
        try {
            String clientCertificateKey = Base64.getEncoder().encodeToString(certificate.getEncoded());
            String clientName = certificate.getSubjectDN().getName().replaceFirst("DN=", "").trim();
            DateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
            String validFrom = dateFormat.format(certificate.getNotBefore());
            String validTo = dateFormat.format(certificate.getNotAfter());
            String sql = "INSERT INTO ClientCertificates VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, clientCertificateKey);
            preparedStatement.setString(2, clientName);
            preparedStatement.setString(3, validFrom);
            preparedStatement.setString(4, validTo);
            preparedStatement.setInt(5, selectedRootCertificateId);
            preparedStatement.setString(6, Base64.getEncoder().encodeToString(privateKey.getEncoded()));
            preparedStatement.executeUpdate();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            throw new RuntimeException(ex);
        }
    }

    private void insertRootCertificateIntoDB(X509Certificate selfSignedX509Certificate, PrivateKey privateKey) {
        try {
            String rootCertificateKey = Base64.getEncoder().encodeToString(selfSignedX509Certificate.getEncoded());
            String issuerName = selfSignedX509Certificate.getIssuerX500Principal().getName().replaceFirst("CN=", "").trim();
            DateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
            String validFrom = dateFormat.format(selfSignedX509Certificate.getNotBefore());
            String validTo = dateFormat.format(selfSignedX509Certificate.getNotAfter());
            String sql = "INSERT INTO RootCertificates (certificateKey, issuerName, validFrom, validTo, privateKey) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, rootCertificateKey);
            preparedStatement.setString(2, issuerName);
            preparedStatement.setString(3, validFrom);
            preparedStatement.setString(4, validTo);
            preparedStatement.setString(5, Base64.getEncoder().encodeToString(privateKey.getEncoded()));
            preparedStatement.executeUpdate();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            throw new RuntimeException(ex);
        }
    }

    public static void setTableClientCertificates(JTable table) throws SQLException {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Client Name");
        model.addColumn("Valid From");
        model.addColumn("Valid To");
        table.setModel(model);

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM ClientCertificates WHERE rootCertificateId = " + selectedRootCertificateId);
        while (resultSet.next()) {
            model.addRow(new Object[]{resultSet.getString("ClientName"), resultSet.getString("ValidFrom"), resultSet.getString("ValidTo")});
        }
        table.setModel(model);
    }

    public static void setTableRootCertificates(JTable table) throws SQLException {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Issuer Name");
        model.addColumn("Valid From");
        model.addColumn("Valid To");

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from RootCertificates");
        selectedRootCertificateIds.clear();
        while (resultSet.next()) {
            selectedRootCertificateIds.add(resultSet.getInt("certificateId"));
            model.addRow(new Object[]{resultSet.getString("IssuerName"), resultSet.getString("ValidFrom"), resultSet.getString("ValidTo")});
        }
        table.setModel(model);
    }

    void exportCertificate(X509Certificate certificate, String fileName, String privateKey) {
        try {
            // parent component of the dialog
            JFrame parentFrame = new JFrame();
            JFileChooser fileChooser = new JFileChooser(System.getProperty("user.home") + "\\Desktop");
            fileChooser.setDialogTitle("Specify a file to save");
            fileChooser.setAcceptAllFileFilterUsed(false);
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Certificate File", "cer");
            fileChooser.setFileFilter(filter);

            File f = new File(fileName);
            fileChooser.setSelectedFile(f);

            int userSelection = fileChooser.showSaveDialog(parentFrame);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
                CertificateGenerator.exportCertificate(certificate, fileToSave.getAbsolutePath(), privateKey);
                JOptionPane.showMessageDialog(this, "Certificate Exported Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
            else {
                System.out.println("Save command cancelled by user.");
            }
        }
        catch (Exception e1) {
            e1.printStackTrace();
            JOptionPane.showMessageDialog(this, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        new FrmDashboard().setVisible(true);
    }
}
