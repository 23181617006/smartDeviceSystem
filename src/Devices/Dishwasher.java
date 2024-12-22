package Devices;

import java.util.HashMap;
import java.util.Map;

public class Dishwasher extends SmartDevice {
    private boolean isRunning; // Makine çalışıyor mu?
    private String currentProgram; // Aktif program
    private final Map<String, Integer> programs; // Programlar ve süreleri

    public Dishwasher(String name, boolean status) {
        super(name, status);
        this.isRunning = status;
        this.currentProgram = null;

        // Programları oluştur
        this.programs = new HashMap<>();
        programs.put("Hızlı Yıkama", 30);
        programs.put("Ekonomik Yıkama", 60);
        programs.put("Yoğun Yıkama", 90);
        programs.put("Günlük Yıkama", 45);
        programs.put("Hassas Yıkama", 75);
    }

    // Cihazı aç
    public void turnOn() {
        isRunning = true;
    }

    // Cihazı kapat
    public void turnOff() {
        isRunning = false;
        currentProgram = null; // Program sıfırla
    }

    // Program seç
    public void selectProgram(String programName) {
        if (programs.containsKey(programName)) {
            currentProgram = programName;
        }
    }

    // Çalışma durumu
    public boolean isRunning() {
        return isRunning;
    }

    // Şu anki program
    public String getCurrentProgram() {
        return currentProgram;
    }

    // Programlar (Map olarak döndür)
    public Map<String, Integer> getPrograms() {
        return programs;
    }
}