import javax.swing.*;
import java.security.cert.X509Certificate;

public class FrmVerifyCertificate extends JFrame {

    static X509Certificate selfSignedX509Certificate, certificateSignedX509Certificate;

    FrmVerifyCertificate() {
        setTitle("Verify Certificate");
        setSize(525, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setLayout(null);

        JButton btnLoadRootCertificate = new JButton("Load Root Certificate");
        btnLoadRootCertificate.setBounds(25, 10, 200, 30);
        add(btnLoadRootCertificate);

        JButton btnLoadClientCertificate = new JButton("Load Client Certificate");
        btnLoadClientCertificate.setBounds(275, 10, 200, 30);
        add(btnLoadClientCertificate);

        JLabel lblRootCertificate = new JLabel("Root Certificate:");
        lblRootCertificate.setBounds(25, 50, 200, 30);
        add(lblRootCertificate);

        JLabel lblRootCertificateName = new JLabel("");
        lblRootCertificateName.setBounds(25, 80, 200, 30);
        add(lblRootCertificateName);

        JLabel lblClientCertificate = new JLabel("Client Certificate:");
        lblClientCertificate.setBounds(275, 50, 200, 30);
        add(lblClientCertificate);

        JLabel lblClientCertificateName = new JLabel("");
        lblClientCertificateName.setBounds(275, 80, 200, 30);
        add(lblClientCertificateName);

        JButton btnVerify = new JButton("Verify");
        btnVerify.setBounds(200, 150, 100, 30);
        add(btnVerify);

        btnLoadRootCertificate.addActionListener(e -> {
            selfSignedX509Certificate = CertificateGenerator.loadCertificateFromFile();
            if (selfSignedX509Certificate != null)
                lblRootCertificateName.setText(selfSignedX509Certificate.getSubjectDN().getName().replaceFirst("DNQ=", ""));
        });

        btnLoadClientCertificate.addActionListener(e -> {
            certificateSignedX509Certificate = CertificateGenerator.loadCertificateFromFile();
            if (certificateSignedX509Certificate != null)
                lblClientCertificateName.setText(certificateSignedX509Certificate.getSubjectDN().getName().replaceFirst("DNQ=", ""));
        });

        btnVerify.addActionListener(e -> {
            if (selfSignedX509Certificate != null && certificateSignedX509Certificate != null)
                CertificateGenerator.verifyCertificate(certificateSignedX509Certificate, selfSignedX509Certificate.getPublicKey());
            else
                JOptionPane.showMessageDialog(null, "Please load certificates");
        });

    }

    public static void main(String[] args) {
        new FrmVerifyCertificate().setVisible(true);
    }
}
