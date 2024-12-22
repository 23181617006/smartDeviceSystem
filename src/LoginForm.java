import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class LoginForm extends JDialog {

    //Loginform classında kullanılan butonlar ve değişkenlerin tanımlanması

    private JTextField tfUsername;
    private JPasswordField tfPassword;
    private JButton loginButton;
    private JButton createAnAccountButton;
    private JPanel signPanel;

    // Kullanıcı bilgilerini içeren dosya

    private static final File USER_FILE = new File("users.txt");

    public LoginForm(JFrame parent) {
        super(parent);
        setTitle("Login Form");
        setContentPane(signPanel);
        setLocationRelativeTo(parent);
        setModal(true);
        setMinimumSize(new Dimension(450, 450));
        setResizable(false);

        // Login butonuna tıklama işlemi

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = tfUsername.getText(); // Kullanıcı adını al
                String password = new String(tfPassword.getPassword()); // Şifreyi al

                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(LoginForm.this, "Tüm alanlar doldurulmalıdır!", "Hata", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    // Kullanıcı doğrulaması için authenticateFromFile çağrısı

                    boolean isAuthenticated = User.authenticateFromFile(username, password, USER_FILE);
                    if (isAuthenticated) {
                        JOptionPane.showMessageDialog(LoginForm.this, "Giriş Başarılı!", "Başarı", JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                        // Başarılı giriş sonrası ana menüye yönlendirme
                        MainMenuForm mainMenuForm = new MainMenuForm(new User(username, password)); // Örnek (Ana Menü Ekranı)
                    } else {
                        JOptionPane.showMessageDialog(LoginForm.this, "Hatalı kullanıcı adı veya şifre!", "Hata", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(LoginForm.this, "Dosya okuma sırasında bir hata oluştu.", "Hata", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Hesap oluşturma butonuna tıklama işlemi
        createAnAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Login ekranını kapat
                new RegistrationForm(parent); // Kayıt ekranını aç
            }
        });

        setVisible(true);
        pack();
    }
}