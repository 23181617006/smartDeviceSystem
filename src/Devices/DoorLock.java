package Devices;

public class DoorLock extends SmartDevice {

    private String password; // Şifre 4 haneli olarak tutulur (örn. "1234")

    // Yapıcı metod
    public DoorLock(String name, boolean status) {
        super(name, status);
    }

    // Durum gösterir
    public boolean isRunning() {return super.getStatus();}

    // Kapıyı açar
    @Override
    public void turnOn() {
        try {
            super.turnOn(); // Üst sınıfın `turnOn` metodu
        } catch (Exception ignored) {
            // Hata formda işlenmek üzere bırakılır, burada işlenmez
        }
    }

    // Kapıyı kapatır
    @Override
    public void turnOff() {
        try {
            super.turnOff(); // Üst sınıfın `turnOff` metodu
        } catch (Exception ignored) {
            // Hata formda işlenmek üzere bırakılır
        }
    }

    // Şifre belirleme (4 haneli validasyon yapılır)
    public boolean setPassword(String input) {
        try {
            if (input != null && input.matches("\\d{4}")) { // Şifre 4 haneli mi kontrol et
                this.password = input;
                return true; // Başarılı
            }
        } catch (Exception ignored) {
            // Hata formda işlenmek üzere bırakılır
        }
        return false; // Hatalı
    }


    // Şifreyi alma (opsiyonel, gizlilik açısından kullanılmayabilir)
    public String getPassword() {
        try {
            return this.password;
        } catch (Exception ignored) {
            // Hata formda işlenmek üzere bırakılır
        }
        return null; // Hata durumunda null döner
    }
}