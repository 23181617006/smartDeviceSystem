package Devices;

public class SmartCurtain extends SmartDevice {

    // Perdenin açıklık yüzdesini tutan değişken
    private int openPercentage;

    // Constructor (yapıcı metod)
    public SmartCurtain(String name, boolean status) {
        super(name, status); // Üst sınıfın constructor'ını çağırır.
        this.openPercentage = 0; // Başlangıçta perde tamamen kapalıdır.
    }

    // Perde sistemini açar
    @Override
    public void turnOn() {
        super.turnOn();
    }

    // Perde sistemini kapatır
    @Override
    public void turnOff() {
        super.turnOff();
    }

    // Perde açıklık yüzdesini ayarlar
    public void setOpenPercentage(int percentage) {
        if (percentage >= 0 && percentage <= 100) {
            this.openPercentage = percentage;
        } else {
            throw new IllegalArgumentException("Açıklık yüzdesi 0 ile 100 arasında olmalıdır.");
        }
    }

    // Perdenin mevcut açıklık yüzdesini döndürür
    public int getOpenPercentage() {
        return this.openPercentage;
    }
}