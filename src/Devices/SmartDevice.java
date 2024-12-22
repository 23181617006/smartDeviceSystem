package Devices;

public abstract class SmartDevice {

    private final String name; // Cihazın adı (readonly)
    private boolean status; // Cihazın açık/kapalı durumu

    public SmartDevice(String name, boolean status) {
        this.name = name;
        this.status = status;
    }

    // Cihaz adı (readonly getter)
    public String getName() {
        return name;
    }

    // Cihaz durumu (getter)
    public boolean getStatus() {
        return status;
    }

    // Cihaz durumu (setter private yapılmış: sadece sınıf içinde değişebilir)
    protected void setStatus(boolean status) {
        this.status = status;
    }

    // Cihazı aç
    public void turnOn() {
        this.status = true;
    }

    // Cihazı kapat
    public void turnOff() {
        this.status = false;
    }
}