package Devices;


public class Light extends SmartDevice {

    private int brightness; // Işığın parlaklık seviyesi (0-100 arası bir değer)
    private int[] rgbColor = {255, 255, 255}; // RGB rengi (varsayılan olarak beyaz [255, 255, 255])

    public Light(String name, boolean status) {
        super(name, status);
        this.brightness = 100; // Varsayılan parlaklık
    }

    // Parlaklık seviyesi (getter / setter)
    public int getBrightness() {
        return brightness;
    }

    public void setBrightness(int brightness) {
        if (brightness < 0 || brightness > 100) {
            throw new IllegalArgumentException("Parlaklık seviyesi 0 ile 100 arasında olmalıdır.");
        }
        this.brightness = brightness;
    }

    // RGB renk ayarları (getter / setter)
    public void setRGBColor(int red, int green, int blue) {
        if (red < 0 || red > 255 || green < 0 || green > 255 || blue < 0 || blue > 255) {
            throw new IllegalArgumentException("RGB değerleri 0 ile 255 arasında olmalıdır.");
        }
        this.rgbColor[0] = red;
        this.rgbColor[1] = green;
        this.rgbColor[2] = blue;
    }

    // Cihazı aç
    @Override
    public void turnOn() {
        super.turnOn(); // SmartDevice'deki temel işlemleri çağır
        this.setStatus(true); // Işık durumunu aç
    }

    // Cihazı kapat
    @Override
    public void turnOff() {
        super.turnOff(); // SmartDevice'deki temel işlemleri çağır
        this.setStatus(false); // Işık durumunu kapat
    }
}