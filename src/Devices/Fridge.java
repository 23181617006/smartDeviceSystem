package Devices;

public class Fridge extends SmartDevice {
    private double upperCompartmentTemperature;   // Üst bölmenin sıcaklığı
    private double lowerCompartmentTemperature;   // Alt bölmenin sıcaklığı

    private static final double MIN_TEMPERATURE = -20.0; // Minimum sıcaklık (-20 °C)
    private static final double MAX_TEMPERATURE = 10.0;  // Maksimum sıcaklık (+10 °C)

    // Constructor
    public Fridge(String name, boolean status) {
        super(name, status);
        this.upperCompartmentTemperature = 0.0; // Varsayılan başlangıç sıcaklığı
        this.lowerCompartmentTemperature = 0.0; // Varsayılan başlangıç sıcaklığı
    }

    // Üst bölmenin sıcaklığı
    public double getUpperCompartmentTemperature() {
        return upperCompartmentTemperature;
    }

    public void setUpperCompartmentTemperature(double value) {
        if (value >= MIN_TEMPERATURE && value <= MAX_TEMPERATURE) {
            this.upperCompartmentTemperature = value;
        } else {
            throw new IllegalArgumentException("Sıcaklık değeri kabul edilen aralıkta değil: " +
                    MIN_TEMPERATURE + " ile " + MAX_TEMPERATURE + " arasında.");
        }
    }

    // Alt bölmenin sıcaklığı
    public double getLowerCompartmentTemperature() {
        return lowerCompartmentTemperature;
    }

    public void setLowerCompartmentTemperature(double value) {
        if (value >= MIN_TEMPERATURE && value <= MAX_TEMPERATURE) {
            this.lowerCompartmentTemperature = value;
        } else {
            throw new IllegalArgumentException("Sıcaklık değeri kabul edilen aralıkta değil: " +
                    MIN_TEMPERATURE + " ile " + MAX_TEMPERATURE + " arasında.");
        }
    }

    // Ortalama sıcaklık
    public double getAverageTemperature() {
        return (upperCompartmentTemperature + lowerCompartmentTemperature) / 2.0;
    }
}