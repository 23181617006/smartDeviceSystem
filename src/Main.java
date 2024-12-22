import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        // burada uygulamanın çalışması için JFrame Class'ından bir nesne oluşturup aşağıda çalıştırdık.
        //kodumuz loginFormdan başladığı için new loginform açarak parentFrame nesnesini içine attık.

        JFrame parentFrame = new JFrame();
        new LoginForm(parentFrame);

    }
}