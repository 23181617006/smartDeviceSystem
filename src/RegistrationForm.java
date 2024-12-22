import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class RegistrationForm extends JDialog {

    private JButton registerButton;
    private JPasswordField createPasswordField;
    private JTextField createUsernameField;
    private JTextField createMailAdressField;
    private JPanel registrationPanel;
    private JButton backToLoginButton;

    private static final File USER_FILE = new File("users.txt"); // Kullanıcı bilgilerini kaydedeceğimiz dosya

    public RegistrationForm(JFrame parent) {

        super(parent);
        setTitle("Registration Form");
        setContentPane(registrationPanel);
        setLocationRelativeTo(parent);
        setModal(true);
        setMinimumSize(new Dimension(450, 450));
        setResizable(false);

        // Kayıt butonuna tıklama olayı
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String mailAdress = createMailAdressField.getText();
                String username = createUsernameField.getText();
                String password = new String(createPasswordField.getPassword());

                if (username.isEmpty() || password.isEmpty() || mailAdress.isEmpty()) {
                    JOptionPane.showMessageDialog(RegistrationForm.this, "Tüm alanlar doldurulmalıdır!", "Hata", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                User newUser = new User(username, password);
                try {
                    newUser.saveToFile(USER_FILE); // Yeni kullanıcıyı dosyaya kaydet
                    JOptionPane.showMessageDialog(RegistrationForm.this, "Kayıt Başarılı!", "Başarı", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                    new LoginForm(parent); // Giriş ekranına yönlendir
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(RegistrationForm.this, "Kayıt sırasında bir hata oluştu.", "Hata", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Geri butonuna tıklama olayı
        backToLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new LoginForm(parent); // Giriş ekranına dön
            }
        });

        pack();
        setVisible(true);
    }
}