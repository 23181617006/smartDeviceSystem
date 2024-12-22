package Devices;

// Bu sınıf, robot süpürge ile ilgili özel hata durumlarında kullanılabilir
public class CustomRobotVacuumException extends Exception {

    // Özel bir hata mesajı ile Exception sınıfının yapıcı metodu çağrılır
    public CustomRobotVacuumException(String message) {
        super(message); // Exception sınıfının yapıcı metoduna mesaj iletilir
    }
}
