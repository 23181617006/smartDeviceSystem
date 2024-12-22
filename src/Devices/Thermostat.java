package Devices;

public class Thermostat extends SmartDevice {

    private double currentTemperature = 20.0; // Varsayılan sıcaklık

    public Thermostat(String name, boolean status) {
        super(name, status);
    }

    public void turnOn() {
        super.turnOn(); // Sadece cihazın açık/kapalı durumunu değiştirir
    }

    public void turnOff() {
        super.turnOff(); // Sadece cihazın açık/kapalı durumunu değiştirir
    }

    public void setTemperature(double temperature) {
        if (temperature < 15.0 || temperature > 25.0) {
            throw new IllegalArgumentException("Geçersiz sıcaklık aralığı! 15 ile 25 arasında bir değer olmalıdır.");
        }
        this.currentTemperature = temperature;
    }

    public double getCurrentTemperature() {
        return currentTemperature;
    }
}